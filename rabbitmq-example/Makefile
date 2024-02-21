# create the container with rabbitMQ
compose-up:
	docker-compose -f docker-compose.local.yml up --build -d

# destroy the containers
compose-down:
	docker-compose -f docker-compose.local.yml down

# execute build and run the application with "local" profile
run:
	mvnw spring-boot:run -Dspring-boot.run.profiles=local

start: compose-up run
