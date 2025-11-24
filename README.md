# swingy-Java
A small RPG game in console or GUI gameplay. You choose!

## Resources
  - https://medium.com/@finzyphinzy/demystifying-mvc-understanding-the-model-view-controller-architecture-85c88a558951
  - https://www.geeksforgeeks.org/advance-java/maven-build-automation/
  - https://medium.com/niveus-solutions/maven-simplified-a-beginners-guide-to-build-automation-and-dependency-management-785696a79046
  - https://labex.io/tutorials/java-how-to-implement-robust-input-validation-in-java-418986
  - https://refactoring.guru/design-patterns/builder
  - https://www.geeksforgeeks.org/system-design/builder-design-pattern/

### What are the new thing I'll learn doing this project:
  - Swing framework to build the GUI view
  - Model-View-Controller design pattern
  - Maven Build Automation
  - Annotation based user input validation (Hibernate Validation)
  - Builder pattern
### What will I also learn if i do the bonus project?
  - Connect and use a Relation Database with Java

## Model-View-Controller
  ### Model
  The “Model” represents the data and business logic of the application. The Model handles managing application data. It’s the brain of the application. This includes tasks like getting data from databases, working with it, and putting it back. The Model maintains the state of the application. It tracks changes to the data and ensures data consistency and integrity. The Model doesn’t interact with the user interface (UI). But, it provides an interface for the Controller to change the data.

  ### View
  The “View” represents the presentation layer of the application. It renders the user interface (UI) based on the data provided by the Model. It also handles user interactions. The View is essentially what the       end user sees and interacts with when using the application.
  
  ### Controller
  In the Model-View-Controller (MVC) architectural pattern, the “Controller” acts as an intermediary between the Model and the View. It is responsible for handling user input, processing requests, and coordinating interactions between the Model and the View.

## Maven Build Automation
   This is commonly used to:
  - Manage dependencies
  - Automate the build process
  - Ensure code quality and run tests
  - Package and deploy the application
````
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

````


## Annotation Based User Input Validation
  Input validation is a critical security mechanism in Java programming that ensures data integrity and prevents potential security vulnerabilities. It involves checking and sanitizing user inputs before processing them in an application.
  In this project I'll use the Hibernate Validator.

## Builder Pattern
The Builder Design Pattern is a creational design pattern that provides a step-by-step approach to constructing complex objects. It separates the construction process from the object’s representation, enabling the same method to create different variations of an object.

Encapsulates object construction logic in a separate Builder class.
  - Allows flexible and controlled object creation.
  - Supports different variations of a product using the same process.
  - Improves readability and maintainability by avoiding long constructors with many parameters.


## Maven Tips

 To generate a pom.xml file:
 - mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart 
 	-DarchetypeVersion=1.5 -DinteractiveMode=false
- https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

## Step-by-Step Development:

	- Database = Docker (script to install it and mount the data) + Maven Dependency to get the .jar for JDBC + JDBC (Driver to connect to the DB)
	- MVC = Model (Hero, Villain, Artifact) + View (Console, GUI) + Controller (The bridge between Model & View)
	- GUI = Swing
	- Validation = Jakarta using Hibernate Validation

## Observations

### Why Swing instead of JavaFX

	At first, I tried to develop the project using the JavaFX module but while trying to set it up I realized that I couldn't implement it in a proper manner.

	- JavaFX can be open only one time per JVM
	- It has launch lifecyle limits

## Setup
	- Create a file named .env with the environment variables needed:
		- SWINGY_DB_USER
		- SWINGY_DB_PASSWORD
		- SWINGY_DB_NAME
		You can define these as you want.
	- Install Docker && Maven
		- yum install docker
		- yum install maven

## Project Development

### Model-View-Controller Pattern

- Not 100% implemented. I handled the input (validation, the hero class match with the predefined classes, etc) in the view itself because the handling wasn't the same in console view and gui view.

### Builder Pattern

- Implemented a class named GUIBuilder to process the build of views such as Menu, PopUps, Listing, Hero Creation, ...

### Data persistance
	- Data is stored in a PostgreSQL Database using the JDBC (Java Database Connection)
	- If the Hero is defeated in game his data will be erased from the Database

### Hibernate Validator
	- Whenever you create a hero, the data will be validated using the Hibernate Validator
	- Whenever the data from the database is loaded, the data will be validated to avoid in-database modifications

### GUI Caching
	- Using CardLayout as a form of view caching

## How does the game works?

### Map
	- The map depends of the hero level
	- The amount of villains generated by game depends of the map size -> 20% of the cells will have villains
	- If you fall in a Villain cell you must fight (this differs from the original swingy subject)
### Battle
	- Battles are automatic and if you win it you've a 50% chance of getting an artifact
	- If you've an artifact already attached you can choose between replace it or keep it
	- The chanches of the artifact type dropped are the same (33%) and your chance of the artifact being uncommon is 30%

## Extra Note

- This documentation has the thoughts before the development and after the development

## Conclusion

This project has been one of the most valuable learning experiences I’ve had so far. Building a small RPG game from the ground up helped me understand how different parts of an application work together—from using MVC and creating a GUI with Swing, to managing builds with Maven, validating data with Hibernate Validator, and storing everything in a PostgreSQL database with JDBC. Not everything went exactly as planned, but adapting the design and finding solutions along the way taught me even more. Overall, Swingy helped me improve both my technical skills and the way I approach software development as a whole.

Throughout the development, I ended up restructuring the project multiple times as I learned more about design choices and what worked best. This constant refining sparked a real interest in software architecture for me. Understanding how to organize a project, how different layers communicate, and how structure affects maintainability became something I genuinely enjoy exploring now—and this project played a big role in that.