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
PySpark reduceByKey() transformation is used to merge the values of each key using an associative reduce function on PySpark RDD.   
It is a wider transformation as it shuffles data across multiple partitions and It operates on pair RDD (key/value pair).
args:
    func, 聚合函数
    numPartitions
    partitionFunc

> https://sparkbyexamples.com/pyspark/pyspark-reducebykey-usage-with-examples/

# collect()

```scala
// 返回一个array，可以用for loop
DF.collect() 
```

# spark broadcast 
各个slave端都需要同一个数据,并且只有读取操作
例如: 一个object对象,一个map或者bloomFilter等

### broadcast类型变量和传输一个可序列化的变量的区别 ?

1. broadcast类型变量可以保证只在executor的内存中存在一份
2. 将要传输的变量不需要实现Serializable接口
3. 可以高效地传输较大的数据集

> https://www.jianshu.com/p/c5c71bdcccc0

# groupByKey vs ReduceByKey
It's advised to use ReduceByKey over groupByKey, especially when dealing with large datasets. The reason lies in the "shuffle" or "data exchange" between nodes.  
To be precise, before reducing the former transfers data from all nodes, which requires serialization and deserialization and therefore consumes way more computing resources, memory and time. The latter reduces locally before the data is sent to the nodes which perform the final "reduce" operation. 

