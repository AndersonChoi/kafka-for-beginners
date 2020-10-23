# 카프카 입문 코스

> Apache Kafka는 대용량 실시간 로그처리에 특화된 분산 메시징 시스템으로, 대용량/대규모의 스트리밍 메시지 데이터를 빠르게 처리하도록 개발되었습니다.
>  이번 강의에서는 카프카 프로듀서/컨슈머 등 기본 개념을 익히고, 설치부터 기본적인 애플리케이션 개발 실습을 통해 빅데이터 파이프라인에서 카프카가 어떤 핵심적인 역할을 수행하는 지 알아보는 시간을 갖습니다

## 개발 환경 구성

- [aws](https://aws.amazon.com/ko/) 계정 생성
- [intellij Community Edition](https://www.jetbrains.com/ko-kr/idea/download) 설치

## 사전 학습 자료

카프카 기본 개념
- [카프카 기초](http://bit.ly/kafka-playlist)

AWS EC2 기본
- [AWS 기본강의](https://opentutorials.org/course/2717/11268)

## 실습 커맨드

```
## EC2 카프카 설치, 실행
1 chmod 400 test-kafka.pem
2 ssh -i kafka-test.pem ec2-user@{aws ec2 public ip}
3 sudo yum install -y java-1.8.0-openjdk-devel.x86_64
4 wget http://mirror.navercorp.com/apache/kafka/2.5.0/kafka_2.13-2.5.0.tgz
5 tar -xvf kafka_2.13-2.5.0.tgz
6 export KAFKA_HEAP_OPTS="-Xmx400m -Xms400m"
7 vi config/server.properties
8 listeners=PLAINTEXT://:9092
9 advertised.listeners=PLAINTEXT://{aws ec2 public ip}:9092
10 bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
11 bin/kafka-server-start.sh -daemon config/server.properties
12 tail -f logs/*

## Ubuntu 환경에서 Java 설치
1 sudo apt install openjdk-8-jdk

## /etc/hosts 수정(윈도우는 C:\Windows\System32\drivers\etc\hosts)
1 {aws ec2 public ip} my-kafka

## 로컬 환경 아파치 카프카 바이너리 다운로드 및 CLI 실행
1 curl http://mirror.navercorp.com/apache/kafka/2.5.0/kafka_2.13-2.5.0.tgz --output kafka.tgz 
2 tar -xvf kafka.tgz
3 cd kafka_2.13-2.5.0/bin
4 ./kafka-topics.sh --create --bootstrap-server my-kafka:9092 --replication-factor 1 --partitions 3 --topic test
5 ./kafka-console-producer.sh --bootstrap-server my-kafka:9092 --topic test
6 ./kafka-console-consumer.sh --bootstrap-server my-kafka:9092 --topic test --from-beginning
7 ./kafka-console-consumer.sh --bootstrap-server my-kafka:9092 --topic test -group testgroup --from-beginning
8 ./kafka-consumer-groups.sh --bootstrap-server my-kafka:9092 --list
9 ./kafka-consumer-groups.sh --bootstrap-server my-kafka:9092 --group testgroup --describe
10 ./kafka-consumer-groups.sh --bootstrap-server my-kafka:9092 --group testgroup --topic test --reset-offsets --to-earliest --execute
11 ./kafka-consumer-groups.sh --bootstrap-server my-kafka:9092 --group testgroup --topic test:1 --reset-offsets --to-offset 10 --execute

## 카프카 프로듀서 실습 및 실행
1 ./kafka-console-consumer.sh --bootstrap-server my-kafka:9092 --topic test --property print.key=true --property key.separator="-"

## 파일 적재 파이프라인
1 ./kafka-verifiable-producer.sh --bootstrap-server my-kafka:9092 --max-messages 100 --topic test
```