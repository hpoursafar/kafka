package ir.tamin.infra.ksp.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.Arrays;
import java.util.Properties;

public class SimpleConsumer {

    public static void main(String[] args) throws  Exception{
        String topicName = "streams-plaintext-input";
        Properties props = new Properties();
        props.put("bootstrap.servers","172.16.14.81:9092");
        props.put("group.id","test");
        props.put("enable.auto.commit","true");
        props.put("auto.commit.interval.ms",1000);
        props.put("session.timeout.ms",30000);
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer consumer = new KafkaConsumer <String,String> (props);
        consumer.subscribe(Arrays.asList(topicName));
        System.out.println("Subscribe to topic" + topicName);
        int i=0;
        while (true){
            ConsumerRecords<String,String> records = consumer.poll(100);
            for (ConsumerRecord<String,String> record : records)
                System.out.printf("offset = %d , key = %s ,value = %s\n" ,record.offset(),record.key(),record.value() );
        }
    }
}
