# execute build and run the application with "local" profile
start:
	mvn spring-boot:run -Dspring-boot.run.profiles=local

# start application with native image
native-start	:
	mvn -Pnative native:compile
	./target/spring-ai-example
