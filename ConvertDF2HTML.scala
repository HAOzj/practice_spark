import org.apache.spark.sql.{DataFrame, SparkSession}

object HTMLFormatter {

  /**
   * 讲DataFrame做HTML格式化
   * @param spark
   * @param tblNames
   * @param dfs
   * @return
   */
  def ConvertDF2htmlTbl(spark: SparkSession, tblNames: Array[String], dfs: Array[DataFrame]) = {
    import spark.implicits._

    val style =
      s"""
         |<head>
         |<style>
         |table {
         |  font-family: arial, sans-serif;
         |  border-collapse: collapse;
         |  width: 100%;
         |}
         |
         |td, th {
         |  border: 1px solid #dddddd;
         |  text-align: left;
         |  padding: 8px;
         |}
         |
         |tr:nth-child(even) {
         |  background-color: #dddddd;
         |}
         |</style>
         |</head>
         |""".stripMargin
    val starter = s"<body>"
    val ender = "</body>"
    val tblContent = (tblNames zip dfs).map{ t2 =>
      val df = t2._2
      val tblName = t2._1
      val cols = df.columns
      val _header = cols.mkString("<th>", "</th><th>", "</th>")
      val _row = df.selectExpr(cols.map(col => s"cast($col as string) as $col"): _*).map{ row =>
        val vals = cols.map{ col =>
          row.getAs[String](col)}
        vals.mkString("<td>", "</td><td>", "</td>")
      }.collect()

      s"<h2>$tblName</h2><table>"  + (Array[String](_header) ++ _row).mkString("<tr>", "</tr><tr>", "</tr>") + "</table>"
    }.mkString("\n")


    style + starter + tblContent + ender
  }

}
