# ZendeskCodingChallenge

ZENDESK CODING CHALLENGE 2021 - ADITYA KULKARNI

I have built a Spring MVC application with Thymeleaf for the coding challenge.

There are two ways to run the application:

	1) By running the .jar file 
		i) The jar file can be found under ZccAditya/target/ZccAditya-0.0.1-SNAPSHOT.jar
		ii) Use the command: java -jar ZccAditya/target/ZccAditya-0.0.1-SNAPSHOT.jar 

	2) Using an IDE for development/debugging purposes
		i) Use an IDE such as STS/Eclipse/IntelliJ
		ii) Import the project
		iii) Run as Spring Boot App

Note: By default, the application will run on port 8080. To change the port, simply go to the application.properties file and update the value of "server.port" to whichever port you want the application to run on. 

Project Structure, Dependencies and Versions:

	The project is built using Spring Boot.
	Maven is used for dependency management. All the dependencies can be found in the pom.xml file
	Thymeleaf is used as the java template engine for the user interface. It's dependency is in the pom.xml file as well.
	Java version 11 has been used to develop the application.
	The src/main folder consists of the source code for all the packages and classes.
	The src/test folder consists of the class that runs the JUnit test cases.


Usage Instructions:

After deploying the application, go to the following url: 

	http://localhost:8080/tickets/info/

This page will consist of the list of tickets.
25 tickets will be displayed on the screen along with a button "Next" at the bottom of the table.
Clicking this button will redirect the user to the next page where the next 25 tickets will be present and so on.
Clicking on the "View" button will redirect the user to a page that displays all ticket details to the user.
This "Ticket Details" screen will also have a "home" button that will redirect the user to the home page.

Also, the following URL can be used to check the health of the service :

	http://localhost:8080/actuator/health
