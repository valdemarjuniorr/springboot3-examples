# execute build and run the application
start:
	mvn spring-boot:run

# start the application with native image˚
native-start:
	docker-compose up -d
	mvn -Pnative native:compile
	./target/kafka-example
