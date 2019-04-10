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
为了保证速度,中间件分为多条消息队列,每个队列对应一个topic(一个topic可以通过设置key来对应多条队列).
这样每个消费者对应的消息队列中就只包含自己想要的.

# Broker集群
为了提高可用性,kafka使用中间件集群.每个消息队列都有一个leader和一个或多个replica,
,生产者根据topic和key将消息传给leader的消息队列,leader和replica同步.
leader坏了就用replica.这样做也提高了吞吐量,因为消费者可以在replica中进行查询.

# 参考
https://zhuanlan.zhihu.com/p/37405836