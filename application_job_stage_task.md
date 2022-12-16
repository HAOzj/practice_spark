# application
我们向spark submit一个application, 也就是sparkContext的一个实例.  

spark中的数据都是抽象为rdd的.rdd的算子分为action和transformation,前者立即执行,而后者需要action来触发.

# job 
每个application中会包含一个或多个action算子,比如saveAsTextFile,count,show.  
每个action对应一个job.  

### lineage graph
rdd之间的依赖关系会存在lineage graph中,以便于在分区数据丢失时,可以重新执行transformation来恢复数据.

# stage
对于每个job,DAGScheduler往前回溯依赖图lineage graph的过程中,遇到一个shuffle算子,shuffle也就是需要为数据重新分区,比如reduceByKey算子,就形成一个stage. 
stage之间按照顺序运行.  

### 宽窄依赖
需要注意的是,不是所有的transformation算子都会造成shuffle.当父rdd只对应一个子rdd时,比如map,flatMap,filter,这些transformation只存在窄依赖,不需要shuffle.所以stage内部存在窄依赖算子,而stage之间是宽依赖算子.  
此外,在利用lineage graph恢复分区数据时,窄依赖计算更快,因为只需要重新计算丢失分区对应的父rdd.比如下图F中黄色格子代表的rdd丢失时,是需要重新计算上游很少的分区.

![](../image/fault_tolerance.png)

# task
对于每个stage,每个partition都会对应一个task.一个stage分task的数量就是并行度.  
在action算子对应的stage中,是ResultTask,其他stage中是ShuffleMapTask.


