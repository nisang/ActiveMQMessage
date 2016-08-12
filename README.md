
## <center>ActiveMQMessage

> * 项目介绍
>> * message-producer 生产者
>> * message-consumer 消费者

> * 开发环境
>> * Eclipse Neon Release（4.6.0）
>> * jdk1.8.0_92
>> * apache-maven-3.3.9
>> * Spring Boot 1.4.0.RELEASE


> * 使用环境 CoreOS (Docker version 1.10.3)
>> * Images
>>> * `docker pull webcenter/activemq:5.13.2`

>>> * `docker pull java:8-jre`


> * Docker ActiveMQ消息服务器
>> `docker run -itd --name=activemq -p 8161:8161 -p 61616:61616 -v /usr/share/zoneinfo/Asia/Shanghai:/etc/localtime:ro webcenter/activemq:5.13.2`

>> `login url:http://localhost:8161`

>> `user：admin passwd：admin`

>* Docker 项目
>> * 在项目根目录运行 `mvn clean install -Dmaven.test.skip=true -Dfile.encoding=UTF-8`

>> * 进入docker目录,build

>>> `docker build -t activemq:consumer .`

>>> `docker build -t activemq:producer .`

> Docker run
>> `docker run -d  -p 9098:9098 activemq:consumer`

>> `docker run -d  -p 9099:9099 activemq:producer`

