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


# _
wildcard


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
List的元素不可重新赋值
```
val a = List(1, 2)
val b = List(3, 4)

// append
a :+ 3  

// prepend
3 +: a

// merge
a ++ b
List.concat(a, b)
```

# Float

```
val a = 1f
```

# Array 
Array的元素可以重新赋值
```
val a = Array(1, 2)

a(0) = 2 // Array[Int] = Array(2, 2)

// append
a :+ 3

```


# sort
sort, sortBy, sortWith

