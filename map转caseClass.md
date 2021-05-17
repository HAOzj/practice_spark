- scala中的AnyRef = java的Object
- args: _* = python中的 args*

```scala
import scala.collection.mutable
import scala.reflect.ClassTag


//将map转为实体的case class
def createCaseClass[T](vals: mutable.HashMap[String, Any])(implicit cmf: ClassTag[T]) = {
  val ctor = cmf.runtimeClass.getConstructors.head
  val args = cmf.runtimeClass.getDeclaredFields.map(f => vals.getOrElse(f.getName, null))
  ctor.newInstance(args.asInstanceOf[Array[AnyRef]]: _*).asInstanceOf[T]
}

case class Person(name: String, age: Int)
val map = mutable.HashMap[String, Any]("name" -> "hzj", "age" -> 5)
createCaseClass[Person](map)
```
