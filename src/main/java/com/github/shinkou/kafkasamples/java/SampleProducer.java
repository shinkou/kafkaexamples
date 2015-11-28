package com.github.shinkou.kafkaexamples.java;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class SampleProducer
{
	public static void main(String[] args)
	{
		String topic = System.getProperty("topic", "java-test-topic");
		String msgkey = System.getProperty("msgkey", "test");

		Properties props = new Properties();
		props.put
		(
			ProducerConfig.BOOTSTRAP_SERVERS_CONFIG
			, System.getProperty
			(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG
				, "localhost:9092"
			)
		);
		props.put
		(
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG
			, StringSerializer.class.getName()
		);
		props.put
		(
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG
			, StringSerializer.class.getName()
		);

		KafkaProducer<String, String> producer
			= new KafkaProducer<String, String>(props);
		ProducerRecord<String, String> record;

		for(String msg: args)
		{
			record = new ProducerRecord<String, String>(topic, msgkey, msg);
			producer.send(record);
		}

		producer.close();
	}
}
