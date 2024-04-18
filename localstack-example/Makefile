# create the container with localstack
compose-up:
	docker-compose -f docker-compose.yml up --build -d

# destroy the containers
compose-down:
	docker-compose -f docker-compose.yml down

# execute build and run the application with "local" profile
start:
	./mvnw spring-boot:run -Dspring-boot.run.profiles=local

# execute build and run the application with "aws" profile
start-aws:
	./mvnw spring-boot:run -Dspring-boot.run.profiles=aws

