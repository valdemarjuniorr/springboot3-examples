# execute build and run the application
start:
	./mvnw spring-boot:run

# start the application with native image˚
native-start:
	docker-compose up -d
	./mvnw -Pnative native:compile
	./target/kafka-example
