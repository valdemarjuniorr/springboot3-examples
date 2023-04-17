# create the container with localstack
compose-up:
	docker-compose -f docker-compose.local.yml up --build -d

# destroy the containers
compose-down:
	docker-compose -f docker-compose.local.yml down

# execute build and run the application with "local" profile
run:
	mvn spring-boot:run -Dspring-boot.run.profiles=local

make start: compose-up run