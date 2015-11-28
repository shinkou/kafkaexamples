package com.github.shinkou.kafkaexamples.scala

import java.util.Properties

import kafka.consumer.{Consumer, ConsumerConfig, KafkaStream}

import scala.collection.JavaConversions._

object SampleConsumer extends App {
  val group = System.getProperty("group", "scala-test-group")
  val topic = System.getProperty("topic", "scala-test-topic")
  val zookeeper = System.getProperty("zookeeper", "localhost:2181")

  val props = new Properties
  props.put("zookeeper.connect", zookeeper)
  props.put("group.id", group)
  props.put("auto.offset.reset", "largest")
  props.put("zookeeper.session.timeout.ms", "5000")
  props.put("zookeeper.sync.time.ms", "200")
  props.put("auto.commit.interval.ms", "1000")

  val consumer = Consumer.create(new ConsumerConfig(props))
  val consumerMap = consumer.createMessageStreams(Map(topic -> 1))
  val stream = consumerMap.get(topic).get(0)
  val it = stream.iterator
  while(it.hasNext) {
    println(new String(it.next.message))
  }

  consumer.shutdown
}
// vim: sw=2 ts=2 tw=0 et:
