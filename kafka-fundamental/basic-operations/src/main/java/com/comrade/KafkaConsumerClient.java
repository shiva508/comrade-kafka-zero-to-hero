package com.comrade;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerClient {
	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(KafkaConsumerClient.class);
		KafkaConsumer<String, String> consumer = getStringStringKafkaConsumer();

		final Thread mainThread = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            consumer.wakeup();
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }));

		try {
			consumer.subscribe(List.of("batman"));
			while (true) {
				ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
				consumerRecords.forEach(data -> {
					logger.info("KEY:" + data.key() + "    VALUE:" + data.value()+" partition:" + data.partition() + "    offset:" + data.offset());
				});
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			consumer.close();
		}
	}

	private static KafkaConsumer<String, String> getStringStringKafkaConsumer() {
		Properties properties = new Properties();
		String groupId = "batman";
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.21.0.3:9092");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        return new KafkaConsumer<>(properties);
	}
}
