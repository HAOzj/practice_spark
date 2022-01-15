# 不能序列化
scala中,class extends Serializable和改用可序列化的类可以解决

# stackoverflow
spark-submit时扩大内存,比如设置参数driver-memory 2G 

# print
print时用string interpolation时(s"  "),要注意某些特殊字符,比如%
```
val s = "a%a"
s"${s.replace("%", "%%")}"
```
.因为"%"是特殊字符

# DataFrame的列是array
```
import scala.collection.mutable

getAs[mutable.WrappedArray[T]]
```

# spark-submit时.py文件不能引用其他文件
将.py文件打包,参考python/setup.py的写法,主文件为__main__.py

# udf无法处理Array型的列
自己定义的case class不能作为udf的参数,需要用Seq\[Row\]  
https://stackoverflow.com/questions/45080913/using-spark-udfs-with-struct-sequences
