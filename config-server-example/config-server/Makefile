docker-compose:
	docker-compose up -d
# execute build and run the application
start:
	docker-compose
	./mvnw spring-boot:run

# start application with native image
native-start:
	docker-compose
	./mvnw clean -Pnative native:compile
	./target/config-server