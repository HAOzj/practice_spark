# 不能序列化
scala中,class extends Serializable和改用可序列化的类可以解决

# stackoverflow
spark-submit时增加参数driver-memory 2G 


# spark-submit时.py文件不能引用其他文件
将.py文件打包,参考python/setup.py的写法,主文件为__main__.py

# udf无法处理Array型的列
自己定义的case class不能作为udf的参数,需要用Seq\[Row\]  
https://stackoverflow.com/questions/45080913/using-spark-udfs-with-struct-sequences