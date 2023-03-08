# create prometheus container
compose-up:
	docker-compose -f docker-compose.yml up --build -d

# destroy the container
compose-down:
	docker-compose -f docker-compose.yml down

# execute build and run the application
run:
	mvnw spring-boot:run

make start: compose-up run