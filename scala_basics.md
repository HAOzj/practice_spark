# Map
```scala
// 空哈希表，键为字符串，值为整型
var A:Map[Char,Int] = Map()
A.put("red", "#FF0000")   # 赋值
A.contains("red")  # True
A("red")  # "#FF0000"
A.getOrElse("red", "FF0000")  # 类似于python的get

// Map 键值对演示
val colors = Map("red" -> "#FF0000", "azure" -> "#F0FFFF")
```


# mkString

Array或String.mkString(相当于py中的list(str)) -> String

Args: start, seq, end

# match case

```scala
var key_val = ""
keyName match {
    case "title" => key_val = mediaDetailList(i).title
    case "vid" => key_val = mediaDetailList(i).vid
    case "vid_sourceId" => key_val = mediaDetailList(i).vid + "_" + mediaDetailList(i).sourceId
}
```

# Unit
用于定义不返回数据的函数 
```scala
def main(args: Array[String]) : Unit = { 
} 
```

# spark broadcast 
各个slave端都需要同一个数据,并且只有读取操作
例如: 一个object对象,一个map或者bloomFilter等

### broadcast类型变量和传输一个可序列化的变量的区别 ?

1. broadcast类型变量可以保证只在executor的内存中存在一份
2. 将要传输的变量不需要实现Serializable接口
3. 可以高效地传输较大的数据集

> https://www.jianshu.com/p/c5c71bdcccc0


# udf
udf中input param如果是String, withColumn时的input param需要是col(fieldName), whose value is of type String;  
可以通过 withColumn(fieldName, lit("a"))

# iterate over iterables

```scala
for ((k, v) <- map) {
}

val names = Vector("Bob", "Fred", "Joe", "Julia", "Kim")
for (name <- names) println(name)
```

# List
```
val a = List(1, 2)
val b = List(3, 4)

// append
3 :+ a  

// merge
a ++ b
List.concat(a, b)
```

# Float

```
val a = 1f
```

