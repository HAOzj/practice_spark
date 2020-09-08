# Map
```scala
// 空哈希表，键为字符串，值为整型
var A:Map[Char,Int] = Map()
A.put("red", "#FF0000")   # 赋值
A.contains("red")  # True
A("red")  # "#FF0000"

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



