Kafka Examples
==============

How To Compile
--------------

```
$ mvn clean package
```

How To Run
----------

Java consumer example
```
$ java -cp ./target/kafkaexamples-0.1.0-jar-with-dependencies.jar com.github.shinkou.kafkaexamples.java.SampleConsumer
```

Java producer example
```
$ java -cp ./target/kafkaexamples-0.1.0-jar-with-dependencies.jar com.github.shinkou.kafkaexamples.java.SampleProducer MSG1 MSG2 MSG3
```

Scala consumer example
```
$ java -cp ./target/kafkaexamples-0.1.0-jar-with-dependencies.jar com.github.shinkou.kafkaexamples.scala.SampleConsumer
```

Scala producer example
```
$ java -cp ./target/kafkaexamples-0.1.0-jar-with-dependencies.jar com.github.shinkou.kafkaexamples.scala.SampleProducer MSG1 MSG2 MSG3
```
