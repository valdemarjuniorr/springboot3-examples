# execute build and run the application with "local" profile
start:
	mvn spring-boot:run -Dspring-boot.run.profiles=local

native-start:
	docker-compose up -d
	mvn -Pnative native:compile
	./target/spring-data-jdbc-client-example

