package ir.tamin.infra.ksp.service.kafka.producer;

import ir.tamin.infra.ksp.service.kafka.Alert;
import ir.tamin.infra.ksp.service.kafka.callback.AlertCallback;
import ir.tamin.infra.ksp.service.kafka.partitioner.AlertLevelPartitioner;
import ir.tamin.infra.ksp.service.kafka.serde.AlertKeySerde;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class AlertProducerV2 {

    final static Logger log = LoggerFactory.getLogger(AlertProducerV2.class);

    public void produceAlert(String requestId){
        Properties kaProperties = new Properties();
        kaProperties.put("bootstrap.servers",  "172.16.14.81:9092");

        kaProperties.put("key.serializer", AlertKeySerde.class.getName());   //<1>
        kaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /** Use {@link org.kafkainaction.partitioner.AlertLevelPartitioner} to determine partition */
        kaProperties.put("partitioner.class", AlertLevelPartitioner.class.getName());    //<2>

        try (Producer<Alert, String> producer = new KafkaProducer<>(kaProperties)) {
            Alert alert = new Alert(1L, "Stage 1", "CRITICAL", requestId);
            ProducerRecord<Alert, String>
                    producerRecord = new ProducerRecord<>("kinaction_alert", alert, alert.getAlertMessage());   //<3>

            producer.send(producerRecord, new AlertCallback());
        }
    }



}
