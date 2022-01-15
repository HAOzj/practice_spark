# reduce vs fold  

reduce是从左到右的迭代,第一个值为初始值;  
fold也是从左到右的迭代,不过带有初始值


reduce相当于
```
var res = l.head
l.slice(1, l.size).foreach{ele => res = op(res, ele)}
res
```

# Repartition and Coalesce
这两个功能都是用来帮助分块,
- repartition有两种方式.根据column来partition;指定# of partitions
- the latter不能shuffle，只能把数据移到现有的partitions，所以只能减少# of partitions,不能增加

Num of partitions默认是200，可以通过以下代码查看
```scala
spark.conf.get("spark.sql.shuffle.partitions")
```

> https://datanoon.com/blog/spark_repartition_coalesce/#3-repartition


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

# 数据倾斜
并行处理的数据集中,某一部分的数据显著多于其它部分,从而使得该部分的处理速度成为整个数据集处理的瓶颈.
数据倾斜还会引起OOM等问题.    
数据倾斜一般发生在shuffle阶段. 

# shuffle
一些算子需要数据按照key来重新分配,这个叫做shuffle.  
引起shuffle的算子包括distinct、groupByKey、reduceByKey、aggregateByKey、join、repartition


# groupByKey vs ReduceByKey
优先使用后者,因为两者的shuffle方式不同.  
groupByKey在reduce前,要shuffle所有数据;而reduceByKey是本地reduce,然后再把reduce结果reduce

It's advised to use ReduceByKey over groupByKey, especially when dealing with large datasets. The reason lies in the "shuffle" or "data exchange" between nodes.  
To be precise, before reducing the former transfers data from all nodes, which requires serialization and deserialization and therefore consumes way more computing resources, memory and time. The latter reduces locally before the data is sent to the nodes which perform the final "reduce" operation. 

## reduceByKey
PySpark reduceByKey() transformation is used to merge the values of each key using an associative reduce function on PySpark RDD.   
It is a wider transformation as it shuffles data across multiple partitions and It operates on pair RDD (key/value pair).
args:
    func, 聚合函数
    numPartitions
    partitionFunc

> https://sparkbyexamples.com/pyspark/pyspark-reducebykey-usage-with-examples/


# join效率  
两个表join时,往往需要shuffle,当数据倾斜时会引发问题.为了优化效率和避免错误.有以下建议  

## 大表和小表join
### map join  
小表可以改造成map后BC(广播),大表通过map来获取小表信息. 

适用场景: 大小表相差3倍以上.小表广播后小于2g. 
原理: map join不会shuffle数据

## 大表和大表join

### 分离后随机前缀和扩容法  
把分布不均匀的key的数据取出. 一张表的key加上随机前缀,另一张表扩充全部前缀,两者join,然后和剩下的数据union
比如名字表A和购买记录表B中hzj分布不均匀,则可以 
1. A表分成hzj外的A1和 key为hzj的A2;名字表中hzj外的B1和 key为hzj的B2
1. A2表的hzj随机变成 1hzj, 2hzj, ..., 20hzj 生成C表
2. B2表中hzj扩充成 1hzj, 2hzj, ..., 20hzj二十行 生成D表
3. (C join D).union(A1 join B1) 

适用场景: 大表某些key分布不均匀

## 随机前缀和扩容法。
全部的key都执行随机前缀和扩容  

适用场景: 大表很多key分布不均匀
要求: 内存要求比较高
