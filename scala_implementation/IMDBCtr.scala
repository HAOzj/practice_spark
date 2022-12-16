import java.time.LocalDate
import java.time.format.DateTimeFormatter

import org.apache.spark.sql.functions.{col, udf}
import org.apache.spark.sql.{DataFrame, SparkSession}

object utils {
  /**
   *
   * @param df            数据dataframe
   * @param spark         spark SQL的入口
   * @param tbl           要写入的hive表
   * @param partitionCond 分区条件
   * @param fields        列名
   * @return
   */
  def WriteDF2hive(df: DataFrame, spark: SparkSession, tbl: String, partitionCond: String, fields: Array[String]) = {
    df.registerTempTable("table1")

    // 覆盖旧分区的数据
    if (df.count() > 0) {
      spark.sql(
        s"alter table ${tbl} drop if exists partition ($partitionCond)"
      )
      spark.sql(
        s"insert into ${tbl} partition($partitionCond) select ${fields.mkString(", ")} from table1")
    }
  }

  // 获得昨天日期的字符串
  def getYesterday(minus: Int = 1, pattern: String = "yyyyMMdd"): String = {
    val dateFormat = DateTimeFormatter.ofPattern(pattern.replaceAll("Y", "y"))
    val today = LocalDate.now()
    val hier = today.plusDays(-minus)
    val yesterday = dateFormat.format(hier)
    yesterday
  }

  val featSep = "\u0004"

  /**
   * 类似于IMDB评分,但impr超过m则不矫正
   * 默认m为有代表性的impr数量
   *
   * @param spark
   * @param df
   * @param imprField 曝光字段名字
   * @param clkField  点击字段名字
   * @param ctrField  生成的ctr字段名
   * @param quantile  m取有clk的分位
   * @return
   */
  def GetSmoothedCxr(
    spark: SparkSession,
    df: DataFrame,
    imprField: String = "impr",
    clkField: String = "click",
    ctrField: String = "ctr",
    quantile: Double = 0.25
  ) = {
    import spark.implicits._
    val dfWithCtr = df.withColumn(ctrField, OriginalCtrUDF(col(imprField), col(clkField)))
    val clkDF = df.where(s"${clkField} > 0").persist()

    /**
     * 如果有ctr > 0,则平滑
     * 否则就仍然为0
     */
    val res = if (clkDF.count() > 0) {
      val c = dfWithCtr.selectExpr(s"avg(${ctrField}) as m").map(_.getAs[Double]("m")).head
      val m = clkDF.stat.approxQuantile(imprField, Array(quantile), 0.2).head
      val wrUDF = udf((impr: Long, click: Long) => {
        val wr = if (impr < m) {
          (click + c * m) / (impr.toDouble + m)
        } else {
          click / impr.toDouble
        }
        wr
      })

      df.withColumn(ctrField, wrUDF(col(imprField), col(clkField)))
    } else {
      dfWithCtr
    }
    clkDF.unpersist()
    res
  }

  val OriginalCtrUDF = udf((impr: Long, click: Long) => {
    if (impr > 0) click.toDouble / impr else 0
  })
}
