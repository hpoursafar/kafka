package ir.tamin.infra.ksp.service.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AuditProducer {
    private static final Logger log = LoggerFactory.getLogger(AuditProducer.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Properties kaProperties = new Properties();    //<1>
        kaProperties.put("bootstrap.servers", "172.16.14.81:9092,172.16.14.81:9093,172.16.14.81:9094");
        kaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kaProperties.put("acks", "all");   //<2>
        kaProperties.put("retries", "3");    //<3>
        kaProperties.put("max.in.flight.requests.per.connection", "1");

        try (Producer<String, String> producer = new KafkaProducer<>(kaProperties)) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("kinaction_audit", null,
                    "audit event");

            RecordMetadata result = producer.send(producerRecord).get();
            log.info("kinaction_info offset = {}, topic = {}, timestamp = {}", result.offset(), result.topic(), result.timestamp());

        }
    }
}
