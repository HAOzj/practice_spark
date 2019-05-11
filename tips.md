# df的agg
- DataFrame的agg操作很花费时间
- agg进行的是key-value操作,所以对每个key只进行最后的那个操作

# Jedis包的redis集群设密码
https://blog.csdn.net/zgz15515397650/article/details/84939987

# 关闭spark程序
- client模式,直接在console终止程序就可以
- cluster模式,console输入 yarn application -kill APPLICATION_NUM来终止

# python从mysql载入数据
3M行数据从mysql载入
- 用pandas读取DF后转为spark.DataFrame用时约168秒
- 用SQLContext或SparkSession.read.format("jdbc").option(**options).load直接读取用时约7秒

options中的dbtable如果是sql语句一定要最后建表,比如(select * from A where cid=0) tmp

# DF的foreachPartition
参数为Iterator\[Row\]类型

# DF写入redis
Jedis方法很慢,要用spark-redis
具体参看object_main
