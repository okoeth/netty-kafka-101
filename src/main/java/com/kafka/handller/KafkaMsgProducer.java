package com.kafka.handller;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.util.PropertyReader;

public class KafkaMsgProducer {

	public static void sendToKafkaQueue(String topicName, byte[] msg) throws Exception {

		// create instance for properties to access producer configs
		Properties props = new Properties();

		// Assign localhost id
		props.put("bootstrap.servers", PropertyReader.propertyReader().getProperty("bootstrap.servers"));

		// Set acknowledgements for producer requests.
		props.put("acks", PropertyReader.propertyReader().getProperty("acks"));

		// If the request fails, the producer can automatically retry,
		props.put("retries", PropertyReader.propertyReader().getProperty("retries"));

		// Specify buffer size in config
		props.put("batch.size", PropertyReader.propertyReader().getProperty("batch.size"));

		// Reduce the no of requests less than 0
		props.put("linger.ms", PropertyReader.propertyReader().getProperty("linger.ms"));

		// The buffer.memory controls the total amount of memory available to
		// the producer for buffering.
		props.put("buffer.memory", PropertyReader.propertyReader().getProperty("buffer.memory"));

		props.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

		Thread.currentThread().setContextClassLoader(null);
		Producer<String, byte[]> producer = new KafkaProducer<String, byte[]>(props);

		producer.send(new ProducerRecord<String, byte[]>(topicName, msg));
		System.out.println("Message sent successfully");
		producer.close();
	}
}
