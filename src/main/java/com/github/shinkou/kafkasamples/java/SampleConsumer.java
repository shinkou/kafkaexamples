package com.github.shinkou.kafkaexamples.java;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class SampleConsumer extends Thread
{
	private final ConsumerConnector m_consumer;
	private final String m_topic;

	public SampleConsumer(String topic)
	{
		m_consumer = kafka.consumer.Consumer.createJavaConsumerConnector
		(
			createConsumerConfig()
		);
		m_topic = topic;
	}

	private static ConsumerConfig createConsumerConfig()
	{
		Properties props = new Properties();
		props.put
		(
			"zookeeper.connect"
			, System.getProperty("zookeeper.connect", "localhost:2181")
		);
		props.put
		(
			"group.id"
			, System.getProperty("group.id", "java-test-group")
		);
		props.put
		(
			"zookeeper.session.timeout.ms"
			, System.getProperty("zookeeper.session.timeout.ms", "400")
		);
		props.put
		(
			"zookeeper.sync.time.ms"
			, System.getProperty("zookeeper.sync.time.ms", "200")
		);
		props.put
		(
			"auto.commit.interval.ms"
			, System.getProperty("auto.commit.interval.ms", "1000")
		);
		return new ConsumerConfig(props);
	}

	public void run()
	{
		Map<String, Integer> topicCntMap = new HashMap<String, Integer>();
		topicCntMap.put(m_topic, 1);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap
			= m_consumer.createMessageStreams(topicCntMap);
		KafkaStream<byte[], byte[]> stream
			= consumerMap.get(m_topic).get(0);
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		while(it.hasNext())
			System.out.println(new String(it.next().message()));
	}

	public static void main(String[] args)
	{
		SampleConsumer consumer = new SampleConsumer
		(
			System.getProperty("topic", "java-test-topic")
		);
		consumer.start();
	}
}
