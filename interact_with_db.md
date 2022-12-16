# python从mysql载入数据
3M行数据从mysql载入
- 用pandas读取DF后转为spark.DataFrame用时约168秒
- 用SQLContext或SparkSession.read.format("jdbc").option(**options).load直接读取用时约7秒

options中的dbtable如果是sql语句一定要最后建表,比如(select * from A where cid=0) tmp


# DF写入redis
Jedis方法很慢,要用spark-redis
具体参看object_main

# Jedis包的redis集群设密码
https://blog.csdn.net/zgz15515397650/article/details/84939987
