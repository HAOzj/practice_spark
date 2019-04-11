# Spark streaming
基于Spark Core的实时(流式)计算框架.
从时间维度上切分数据成batch,把batch中RDD交给Spark Engine进行离线处理,再合起来生成DStream.
Spark streaming中的数据抽象为DStream,是带有时间标识的RDD集合.
同理,Spark Sql中的数据抽象为DataFrame,是带有行信息的RDD.

# Spark Core
离线处理文件的引擎,基本数据单元为RDD.
RDD是数据的抽象,具有以下特性
- 不可变
- 抽象
- 分布式
- 有依赖关系

# HDFS
分布式文件储存系统,将数据切分后 分布式/standalone 得存储到文件中.单个文件的大小一般为128M.
类似于RDD是Spark中数据的抽象,HDFS中FileSystem是文件系统的抽象,
FileSystem将数据文件映射到虚拟目录.
比如/hdfs/data/hello.txt为虚拟目录,对应磁盘上的位置我们不关心,只要用FileSystem这个对象来操作数据就好.