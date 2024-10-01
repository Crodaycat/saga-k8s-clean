# Execute kafka

cd to infrastructure\docker-compose

docker-compose -f .\common.yml -f .\zookeeper.yml up

docker-compose -f .\common.yml -f .\kafka_cluster.yml up
