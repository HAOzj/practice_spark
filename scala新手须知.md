# 配置环境

参考https://www.scala-lang.org/download/.

# 版本注意
注意jdk要安装1.8,高版本和scala不兼容.

scala安装2.1.1就可以,最新版本对应的包不全.

# IDE
IDE使用intellij idea,在File > Setting > Plugin中下载
- scala
- SBT,scala编译工具  
![如图所示](https://github.com/HAOzj/practice_spark/tree/master/image/idea_plugin.jpg)

# scala项目
创建scala-sbt项目,![如图所示](https://github.com/HAOzj/practice_spark/tree/master/image/scala_sbt.jpg).

# 代码结构
代码从scala文件夹开始.
object才可以运行.

# 文件编译(生成.jar文件)  
import库要从 https://mvnrepository.com 网站上下对应的sbt命令,注意和scala版本匹配  
编译文件时打开idea底部的![sbt shell](https://github.com/HAOzj/practice_spark/tree/master/image/sbt_shell_in_idea.jpg)后, 输入 package,.jar文件就会生成在![图中](https://github.com/HAOzj/practice_spark/tree/master/image/sbt_shell.jpg)从上到下数第二个标红处.

