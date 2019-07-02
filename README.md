# Data Ingestion for Kafka using Netty

Build a HTTP Server using Java Netty that accepts a JSON object and 
saves it into a Kafka queue. Setup netty / kafka on the dev machine 
in order to do that.

An working installation of Java and Maven is required.

Based on: https://github.com/suku19/netty-http-kafka

## Apache Kafka Initialisation

### Step 1: Download the code

```
wget http://ftp.halifax.rwth-aachen.de/apache/kafka/2.3.0/kafka_2.12-2.3.0.tgz
tar -xzf kafka_2.12-2.3.0.tgz 
cd kafka_2.11-1.0.1
```

### Step 2: Start the ZooKeeper server
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

### Step 3: Start the Kafka server:
```
bin/kafka-server-start.sh config/server.properties
```

### Step 3: Create a topic and check

```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic rawdata
bin/kafka-topics.sh --list --zookeeper localhost:2181
```

### Step 4: Send some messages through command prompt (Optional)
```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic rawdata
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic rawdata --from-beginning
```

## Netty initialisation

Netty is a framework, so just build and run the Maven project.

```
mvn compile
mvn exec:java -Dexec.mainClass="com.netty.server.http.NettyWebServerMain"
```

Now run:

```
curl http://localhost:8080?name=EnsoT17s
```

Done.
