package kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerTest {


  private static final String BROKER_LIST = "localhost:9092";
  private static final String TOPIC = "t1";

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers", BROKER_LIST);
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554);
    props.put("request.required.acks", "1");
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    Producer<String, String> producer = null;
    try {
      producer = new KafkaProducer<String, String>(props);
      for (int i = 0; i < 100; i++) {
        String msg = "Message " + i;
        producer.send(new ProducerRecord<String, String>(TOPIC, msg));
        System.out.println("Sent:" + msg);
      }
      producer.flush();
    } catch (Exception e) {
      e.printStackTrace();

    } finally {
      producer.close();
    }

  }

}
