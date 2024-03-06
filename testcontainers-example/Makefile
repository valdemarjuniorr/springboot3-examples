# execute build and run the application with "local" profile
start:
	mvn spring-boot:run -Dspring-boot.run.profiles=local

local-test:
	mvn spring-boot:test-run

# start the application with native image˚
native-start:
	docker-compose up -d
	mvn -Pnative native:compile
	./target/testcontainers-example
