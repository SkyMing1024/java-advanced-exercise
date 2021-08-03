**1.（必做）**搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。

基于docker搭建kafka集群：

1. 安装运行zookeeper:

   ```bash
   //拉取镜像
   docker pull wurstmeister/zookeeper
   // 创建并启动容器
   docker run -d --name zookeeper01 -p 2181:2181 -t wurstmeister/zookeeper
   ```

   

2. 安装运行kafka：

   ```bash
   // 拉取镜像
   
   // 创建3个kafka节点，并注册到zookeeper
   // kafka0
   docker run -d --name kafka0 \
   -p 9092:9092 \
   -e KAFKA_BROKER_ID=0 \
   -e KAFKA_ZOOKEEPER_CONNECT=192.168.0.137:2181 \
   -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.0.137:9092 \
   -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
   -t wurstmeister/kafka
   // kafka1
   docker run -d --name kafka1 \
   -p 9093:9093 \
   -e KAFKA_BROKER_ID=1 \
   -e KAFKA_ZOOKEEPER_CONNECT=192.168.0.137:2181 \
   -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.0.137:9093 \
   -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9093 \
   -t wurstmeister/kafka
   // kafka2
   docker run -d --name kafka2 \
   -p 9094:9094 \
   -e KAFKA_BROKER_ID=2 \
   -e KAFKA_ZOOKEEPER_CONNECT=192.168.0.137:2181 \
   -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.0.137:9094 \
   -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9094 \
   -t wurstmeister/kafka
   
   
   ```

3. 

**2.（选做）**安装 kafka-manager 工具，监控 kafka 集群状态。

**3.（挑战☆）**演练本课提及的各种生产者和消费者特性。

**4.（挑战☆☆☆）**Kafka 金融领域实战：在证券或者外汇、数字货币类金融核心交易系统里，对于订单的处理，大概可以分为收单、定序、撮合、清算等步骤。其中我们一般可以用 mq 来实现订单定序，然后将订单发送给撮合模块。

- 收单：请实现一个订单的 rest 接口，能够接收一个订单 Order 对象；
- 定序：将 Order 对象写入到 kafka 集群的 order.usd2cny 队列，要求数据有序并且不丢失；
- 撮合：模拟撮合程序（不需要实现撮合逻辑），从 kafka 获取 order 数据，并打印订单信息，要求可重放, 顺序消费, 消息仅处理一次。

**5.（选做）**自己安装和操作 RabbitMQ，RocketMQ，Pulsar，以及 Camel 和 Spring Integration。

**6.（必做）**思考和设计自定义 MQ 第二个版本或第三个版本，写代码实现其中至少一个功能点，把设计思路和实现代码，提交到 GitHub。

**7.（挑战☆☆☆☆☆）**完成所有其他版本的要求。期限一年。