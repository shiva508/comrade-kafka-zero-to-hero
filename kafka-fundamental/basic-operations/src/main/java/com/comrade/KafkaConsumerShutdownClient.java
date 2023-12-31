package com.comrade;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerShutdownClient {
	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(KafkaConsumerShutdownClient.class);
		Properties properties = new Properties();
		String groupid="batman";
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.21.0.3:9092");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupid);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		KafkaConsumer<String, String> consumer=new KafkaConsumer<>(properties);
		consumer.subscribe(Arrays.asList("batman"));
		while (true) {
			ConsumerRecords<String, String> consumerRecords=consumer.poll(Duration.ofMillis(100));
			consumerRecords.forEach(data->{
				System.out.println("key ="+data.key()+", value="+data.value());
				logger.info("key ={}, value= {}",data.key(),data.value());
				logger.info("KEY:"+data.key()+"    VALUE:"+data.value());
				logger.info("partition:"+data.partition()+"    offset:"+data.offset());
			});
		}
	}
}
