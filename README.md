# swingy-Java
A game in console or GUI gameplay. You choose!

## Resources
  - https://medium.com/@finzyphinzy/demystifying-mvc-understanding-the-model-view-controller-architecture-85c88a558951
  - https://www.geeksforgeeks.org/advance-java/maven-build-automation/
  - https://medium.com/niveus-solutions/maven-simplified-a-beginners-guide-to-build-automation-and-dependency-management-785696a79046
  - https://labex.io/tutorials/java-how-to-implement-robust-input-validation-in-java-418986
  - https://refactoring.guru/design-patterns/builder
  - https://www.geeksforgeeks.org/system-design/builder-design-pattern/

### What are the new thing I'll learn doing this project:
  - Swing framework to build the GUI
  - Model-View-Controller design pattern
  - Maven Build Automation
  - Annotation based user input validation
  - Builder pattern
### What will I also learn if i do the bonus project?
  - Connect and use a Relation Database with Java
  - Switch in the middle of the game between the console mode or the GUI mode



## Model-View-Controller
  ### Model
  The “Model” represents the data and business logic of the application. The Model handles managing application data. It’s the brain of the application. This includes tasks like getting data from databases, working with it, and putting it back. The Model maintains the state of the application. It tracks changes to the data and ensures data consistency and integrity. The Model doesn’t interact with the user interface (UI). But, it provides an interface for the Controller to change the data.

  ### View
  The “View” represents the presentation layer of the application. It renders the user interface (UI) based on the data provided by the Model. It also handles user interactions. The View is essentially what the       end user sees and interacts with when using the application.
  
  ### Controller
  In the Model-View-Controller (MVC) architectural pattern, the “Controller” acts as an intermediary between the Model and the View. It is responsible for handling user input, processing requests, and               coordinating interactions between the Model and the View.

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
