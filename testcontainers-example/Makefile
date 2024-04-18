# execute build and run the application with "local" profile
start:
	./mvnw spring-boot:run -Dspring-boot.run.profiles=local

local-test:
	./mvnw spring-boot:test-run

# start the application with native image˚
native-start:
	docker-compose up -d
	./mvnw -Pnative native:compile
	./target/testcontainers-example
