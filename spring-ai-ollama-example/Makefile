# create the ollama container
compose-up:
	docker-compose up --build -d

# download the llama2 model
dw-model:
	docker exec -it ollama bash -c 'ollama pull llama2'

# execute build and run the application with "local" profile
run:
	./mvnw spring-boot:run -Dspring-boot.run.profiles=local

# start the container and run the application
start: compose-up dw-model run

start-native: compose-up dw-model
	./mvnw -Pnative native:compile
	./target/spring-ai-example
