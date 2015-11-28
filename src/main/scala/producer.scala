package com.github.shinkou.kafkaexamples.scala

import java.util.Properties

import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

object SampleProducer extends App {
  val msgkey = System.getProperty("msgkey", "test")
  val topic = System.getProperty("topic", "scala-test-topic")
  val brokerlist = System.getProperty("brokerlist", "localhost:9092")

  val props = new Properties
  props.put("metadata.broker.list", brokerlist)
  props.put("serializer.class", "kafka.serializer.StringEncoder")
  props.put("producer.type", "sync")

  val producer = new Producer[String, String](new ProducerConfig(props))

  for(msg <- args) {
    var data = new KeyedMessage[String, String] (topic, msgkey, msg)
    producer.send(data)
  }

  producer.close
}
// vim: sw=2 ts=2 tw=0 et:
