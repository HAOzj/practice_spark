```scala
import java.util.Properties

import com.alibaba.fastjson.serializer.SerializeConfig
import org.apache.hadoop.conf.Configuration
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

val props = new Properties()
props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
props.put("bootstrap.servers", host)
val producer = new KafkaProducer[String, String](props)

// 生产者的信息
val record = new ProducerRecord[String, String](topic, partition, key, value)
val metadata = producer.send(record)

// 记录offset
offset = metadata.get().offset
partition = metadata.get().partition()

producer.close()
```
