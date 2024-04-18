# execute build and run the application with "local" profile
start:
	./mvnw spring-boot:run -Dspring-boot.run.profiles=local

# start application with native image
native-start:
	./mvnw -Pnative native:compile
	./target/spring-ai-example
