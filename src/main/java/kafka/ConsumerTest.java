package kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerTest {

  private static final String BROKER_LIST = "localhost:9092";
  private static final String TOPIC = "t1";

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers", BROKER_LIST);
    props.put("group.id", "test-consumer-group");

//    // zk连接超时
//    props.put("zookeeper.session.timeout.ms", "4000");
//    props.put("zookeeper.sync.time.ms", "200");
//    props.put("auto.commit.interval.ms", "1000");
//    props.put("auto.offset.reset", "smallest");

    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("auto.offset.reset", "earliest");
    props.put("session.timeout.ms", "30000");

    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);
    kafkaConsumer.subscribe(Arrays.asList(TOPIC));
    while (true) {
      ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
      for (ConsumerRecord<String, String> record : records) {
        System.out.println("Partition: " + record.partition() + " Offset: " + record.offset()
            + " Value: " + record.value() + " ThreadID: " + Thread.currentThread().getId());
      }
    }

  }

}
