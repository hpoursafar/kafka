package ir.tamin.infra.ksp.service.kafka;


import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;


public class WordCount {
    public static void main(String args[]){
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,"streams-wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"172.16.14.81:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());

        final StreamsBuilder builder = new StreamsBuilder();
        KStream<String,String> source =builder.stream("streams-plaintext-input");
        source.flatMapValues(value -> Arrays.asList(value.toLowerCase(Locale.getDefault()).split("\\W+")))
                .groupBy((key,value) -> value)
                .count(Materialized.<String,Long,KeyValueStore<Bytes,byte[]>>as("counts-store"))
                .toStream()
                .to("streams-wordcount-output",Produced.with(Serdes.String(), Serdes.Long()));
        final Topology topology = builder.build();
        System.out.println(topology.describe());

        final KafkaStreams streams = new KafkaStreams(topology,props);
        final CountDownLatch latch= new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook"){
            @Override
            public void run(){
                streams.close();
                latch.countDown();
            }
        });
        try{
            streams.start();
            latch.await();
        }catch (Throwable e){
            System.exit(1);
        }
        System.exit(0);
//        StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, String> textLines = builder.stream("streams-plaintext-input");
//        KTable<String, Long> wordCounts = textLines
//                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
//                .groupBy((key, word) -> word)
//                .count();
//
//        wordCounts.toStream().to("streams-wordcount-output", Produced.with(Serdes.String(), Serdes.Long()));
//
//        KafkaStreams streams = new KafkaStreams(builder.build(), props);
//        streams.start();



    }

}
