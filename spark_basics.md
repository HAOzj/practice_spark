# Repartition and Coalesce
这两个功能都是用来帮助分块,
- repartition有两种方式.根据column来partition;指定# of partitions
- the latter不能shuffle，只能把数据移到现有的partitions，所以只能减少# of partitions,不能增加

Num of partitions默认是200，可以通过以下代码查看
```scala
spark.conf.get("spark.sql.shuffle.partitions")
```

> https://datanoon.com/blog/spark_repartition_coalesce/#3-repartition


# reduceByKey
PySpark reduceByKey() transformation is used to merge the values of each key using an associative reduce function on PySpark RDD. It is a wider transformation as it shuffles data across multiple partitions and It operates on pair RDD (key/value pair).

> https://sparkbyexamples.com/pyspark/pyspark-reducebykey-usage-with-examples/

# collect()

```scala
// 返回一个array，可以用for loop
DF.collect() 
```

# Random.nextInt
equiprob取[0, n)之间的整数