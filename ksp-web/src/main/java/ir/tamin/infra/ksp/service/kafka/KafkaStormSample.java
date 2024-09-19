package ir.tamin.infra.ksp.service.kafka;

//import org.apache.storm.Config;
//import org.apache.storm.LocalCluster;
//import org.apache.storm.kafka.*;
//import org.apache.storm.spout.SchemeAsMultiScheme;
//import org.apache.storm.topology.TopologyBuilder;

import java.util.UUID;

public class KafkaStormSample {
//    public static void main(String[] args) throws Exception{
//        Config config = new Config();
//        config.setDebug(true);
//        config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
//        String zkConnString = "172.16.14.81:9092";
//        String topic = "streams-plaintext-input";
//        BrokerHosts hosts = new ZkHosts(zkConnString);
//
//        SpoutConfig kafkaSpoutConfig = new SpoutConfig (hosts, topic, "/" + topic,
//                UUID.randomUUID().toString());
//        kafkaSpoutConfig.bufferSizeBytes = 1024 * 1024 * 4;
//        kafkaSpoutConfig.fetchSizeBytes = 1024 * 1024 * 4;
////        kafkaSpoutConfig.forceFromStart = true;
//        kafkaSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
//
//        TopologyBuilder builder = new TopologyBuilder();
//        builder.setSpout("kafka-spout", new KafkaSpout(kafkaSpoutConfig));
//        builder.setBolt("word-spitter", new SplitBolt()).shuffleGrouping("kafka-spout");
//        builder.setBolt("word-counter", new CountBolt()).shuffleGrouping("word-spitter");
//
//        LocalCluster cluster = new LocalCluster();
//        cluster.submitTopology("KafkaStormSample", config, builder.createTopology());
//
//        Thread.sleep(10000);
//
//        cluster.shutdown();
//    }
}
