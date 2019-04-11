# kafka简介
kafka是一个实时的数据处理工具,具有高扩展,高吞吐量,fault-tolerant和贼鸡儿快的特性.

# 消息中间件(Message Broker)
为了解耦消息生产和消息消费,kafka引入中间件,来连接生产者和消费者.
Broker接收生产者生产的所有消息,再根据消费者的订阅把消息出去.
中间件本质上是个持久化储存消息的媒介,作为消息的集散中心.

# 生产者和消费者
生产者生成带有topic的消息,发给中间件.
消费者订阅topic,对topic的信息按照一定的顺序(从最早,或者最近等)做出反应.
每个消费者对应一个offset来记录当前消费的消息位置.


# Parition
为了保证速度和提高吞吐量,中间件引入了partition的概念,也就是按照topic分为多条消息队列,
每个队列对应一个topic.
这样每个消费者对应的消息队列中就只包含自己想要的,并且每个topic可以存储的信息量也提高了.

# Broker集群
为了提高可用性,kafka使用中间件集群.按照[参考2](https://zhuanlan.zhihu.com/p/37405836)的说法每个中间件都保存着全部的消息队列.存疑,实际情况可能是3个中间件保存了两套全部的消息队列,未验证.
每个消息队列都有一个leader和一个或多个replica,
生产者根据topic和key将消息传给leader的消息队列,leader和replica同步.
leader坏了就用replica.这样做也提高了吞吐量,因为消费者可以在replica中进行查询.

# 参考
http://blog.hszofficial.site/experiment/2019/04/06/%E5%B8%B8%E8%A7%81%E7%9A%84%E6%B6%88%E6%81%AF%E4%B8%AD%E9%97%B4%E4%BB%B6/

https://zhuanlan.zhihu.com/p/37405836
