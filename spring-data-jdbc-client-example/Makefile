# execute build and run the application with "local" profile
start:
	./mvnw spring-boot:run -Dspring-boot.run.profiles=local

native-start:
	docker-compose up -d
	./mvnw -Pnative native:compile
	./target/spring-data-jdbc-client-example

