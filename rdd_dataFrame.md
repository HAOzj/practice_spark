# RDD
RDD是数据最底层的抽象,是无结构的数据序列化的储存,具有以下特性
- 由record组成
- 弹性
    - 数据可完全放内存或完全放磁盘,也可部分存放在内存,部分存放在磁盘,并可以自动切换
    - records是分片的(partition),spark在每个分片上运行一个任务.
    一般每个CPU会对应2-4个分片,分片的数量可手动设置.
    <!--
     - RDD出错后可通过血缘自动容错重新计算,而不是在不同节点做数据备份.
     这是RDD的最大优点,使得计算效率更高,数据管理成本更低
     - 可checkpoint(设置检查点,用于容错),可persist或cache(缓存)
    -->
- 支持两种操作:transformation和action.
    - transformation,根据一个现存的数据集来生成新的数据集,比如map操作.    
    所有的transformation都是lazy,也就是只有一个action需要输出结果时才会真正执行.
    spark也支持persist或者cache新的数据集,用于应对新数据集要执行多个action的情况.
    - action,在数据集上执行运算并返回结果,比如reduce操作.
- 不可变
- 分布式

# DataFrame  
DataFrame借鉴了pandas和r的DataFrame,是对RDD结构化的产物,内置了优化的搜索方法.是
1. 结构化数据的分布式储存
2. 类似于关系型数据库的数据表
3. 有schema
4. 基于spark.sql.DataSet

# RDD和DataFrame的转换
- RDD -> DataFrame,通过toDF方法
- DataFrame -> RDD,通过rdd方法

# Python中Spark DF和Pandas DF的相互转化

### pd DF转spark DF:
spark = SparkSession \
        .builder \
        .appName('my_first_app_name') \
        .getOrCreate()
df_spark = spark.createDataFrame(df_pandas, schema=columns)

### spark DF转pd DF
df_pandas = df_spark.toPandas()