package com.comrade;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaAdminClient {
    public static void main(String[] args) {
        Properties kafkaConfig = new Properties();
        kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.21.0.3:9092");
        try (AdminClient adminClient = AdminClient.create(kafkaConfig)) {
           var topics = adminClient.listTopics().namesToListings().get().entrySet().stream().map(Map.Entry::getKey).toList();
            adminClient.listTopics().listings().get().forEach(System.out::println);

            adminClient.listTransactions().all().get().forEach(System.out::println);
            adminClient.describeTopics(List.of("batman","userlog")).values().entrySet().forEach(stringKafkaFutureEntry -> {
                System.out.println(stringKafkaFutureEntry.getKey()+" === "+stringKafkaFutureEntry.getValue());
            });
           System.out.println(topics);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
