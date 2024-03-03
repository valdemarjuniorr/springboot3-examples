# create prometheus container
compose-up:
	docker-compose -f docker-compose.yml up --build -d

# destroy the container
compose-down:
	docker-compose -f docker-compose.yml down

# execute build and run the application
run:
	mvn spring-boot:run

start: compose-up run

start-native:
	mvn -Pnative native:compile
	./target/observability-example
