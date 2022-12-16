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
      val colDtypes = df.dtypes
      val colExprs = colDtypes.map{colDtype =>
        val col = colDtype._1
        val dtype = colDtype._2
        if (dtype != "FloatType") s"cast($col as string) as $col" else s"cast($col as decimal(10, 7)) as $col"
      }
      val _header = cols.map(_._1).mkString("<th>", "</th><th>", "</th>")
      val _row = df.selectExpr(colExprs: _*)
        .map{ row =>
          val vals = colDtypes.map{colDtype =>
            val col = colDtype._1
            val dtype = colDtype._2
            if (dtype != "FloatType") {
              row.getAs[String](col)
            } else {
              row.getAs[java.math.BigDecimal](col).stripTrailingZeros().toString
            }
          }
          vals.mkString("<td>", "</td><td>", "</td>")
        }.collect()

      s"<h2>$tblName</h2><table>"  + (Array[String](_header) ++ _row).mkString("<tr>", "</tr><tr>", "</tr>") + "</table>"
    }.mkString("\n")


    style + starter + tblContent + ender
  }

}
