package ir.tamin.infra.ksp.service.kafka.producer;

//import ir.tamin.infra.ksp.service.kafka.Alert;
import ir.tamin.infra.ksp.service.kafka.avro.Alert;
import ir.tamin.infra.ksp.service.kafka.avro.AlertStatus;
import ir.tamin.infra.ksp.service.kafka.callback.AlertCallback;
import ir.tamin.infra.ksp.service.kafka.partitioner.AlertLevelPartitioner;
import ir.tamin.infra.ksp.service.kafka.serde.AlertKeySerde;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Properties;

public class AlertProducer {

    final static Logger log = LoggerFactory.getLogger(AlertProducer.class);

    public static void main(String[] args) {

        Properties kaProperties = new Properties();
        kaProperties.put("bootstrap.servers", "172.16.14.81:9092");
        kaProperties.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        kaProperties.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer"); // <1>
        kaProperties.put("schema.registry.url", "http://172.16.14.81:8081"); // <2>

        try (Producer<Long, Alert> producer = new KafkaProducer<>(kaProperties)) {
            Alert alert = new Alert(); //<3>
//            alert.setAlertId(12345L);
            alert.setSensorId(12345L);
            alert.setTime(Calendar.getInstance().getTimeInMillis());
            alert.setStatus(AlertStatus.Critical);
//            alert.setAlertLevel("Critical");
            /* Uncomment the following line if alert_v2.avsc is the latest Alert model */
            // alert.setRecoveryDetails("RecoveryDetails");
            log.info(alert.toString());

            ProducerRecord<Long, Alert> producerRecord = new ProducerRecord<>("kinaction_schematest", alert.getSensorId(), alert); // <4>

            producer.send(producerRecord);
        }

    }

//    public static void main(String[] args) {
//
//        Properties kaProperties = new Properties();
//        kaProperties.put("bootstrap.servers",  "172.16.14.81:9092");
//
//        kaProperties.put("key.serializer", AlertKeySerde.class.getName());   //<1>
//        kaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        /** Use {@link org.kafkainaction.partitioner.AlertLevelPartitioner} to determine partition */
//        kaProperties.put("partitioner.class", AlertLevelPartitioner.class.getName());    //<2>
//
//        try (Producer<Alert, String> producer = new KafkaProducer<>(kaProperties)) {
//            Alert alert = new Alert(1, "Stage 1", "CRITICAL", "Stage 1 stopped");
//            ProducerRecord<Alert, String>
//                    producerRecord = new ProducerRecord<>("kinaction_alert", alert, alert.getAlertMessage());   //<3>
//
//            producer.send(producerRecord, new AlertCallback());
//        }
//    }

}
