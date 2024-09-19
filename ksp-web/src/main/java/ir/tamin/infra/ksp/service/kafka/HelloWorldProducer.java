package ir.tamin.infra.ksp.service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Instant;
import java.util.Properties;

public class HelloWorldProducer {
    public static void main(String[] args) {
        Properties kaProperties =
                new Properties();
        kaProperties.put("bootstrap.servers",
                "172.16.14.81:9092,172.16.14.81:9093,172.16.14.81:9094");
        kaProperties.put("key.serializer",
//                "org.apache.kafka.common.serialization.LongSerializer");
                "org.apache.kafka.common.serialization.StringSerializer");
        kaProperties.put("value.serializer",
//                "io.confluent.kafka.serializers.KafkaAvroSerializer"); //<1>
         "org.apache.kafka.common.serialization.StringSerializer");
//        kaProperties.put("schema.registry.url", "http://172.16.14.81:8081");  //<2>
        try (Producer<String, String> producer =
                     new KafkaProducer<String, String>(kaProperties)){
//        try (Producer<Long, Alert> producer = new KafkaProducer<>(kaProperties)) {
//            Alert alert = new Alert(12345, Instant.now().toString(), "Critical","msg");  //<3>
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("kinaction_helloworld", null, "hello world again!");
//            ProducerRecord<Long, Alert> producerRecord =
//                    new ProducerRecord<>("kinaction_schematest",
//                            12345L,
//                            alert); //<4>
        producer.send(producerRecord);
    }
}
}

