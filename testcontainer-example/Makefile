# execute build and run the application with "local" profile
start:
	mvn spring-boot:run -Dspring-boot.run.profiles=local

native-image:
	mvn spring-boot:build-image
	mvn -DskipTests -Pnative native:compile

local-test:
	mvn spring-boot:test-run
