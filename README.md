# Kairos Pricing API

## Hot to run
```console
docker-compose up
```

## Architectural Decisions

### 1. Hexagonal Architecture
- **Reason**: To isolate the core logic of the application from external influences and frameworks, making the system more maintainable and adaptable to change.
- **Impact**: Enables easy adaptation to different frameworks and databases, if required in the future, without significant changes to business logic.
- **Example**: H2JpaPriceRepository implements PriceRepository. If we wanted to change the database we are working with, we only have to create another repository that implements Price Repository.  

### 2. API-First Design
- **Reason**: To prioritize the usability and functionality of the API from the consumer's perspective and ensure that the API contract is agreed upon before development starts.
- **Impact**: As we auto-generate code based on api definition, this approach help us to reduce potential human errors.
- **Location**: Documentation is [here](https://github.com/Raikuro/kairos-assesment/blob/main/src/main/resources/api.yaml). If you launch the app, you will find api documentation [here](http://localhost:8080/swagger-ui/index.html)

### 3. UTC Timezone Handling in H2 Database
- **Reason**: To ensure consistency across different geographic locations and prevent timezone-related issues in date and time calculations.

### 4. Controller Advice
- **Reason**: To centralize exception handling in the application, thereby improving code reusability and separation of concerns. This approach ensures that all exceptions thrown by the application are caught and handled in a consistent manner.
- **Impact**: Enhances the robustness of the API by providing meaningful error responses to the client, thereby improving the user experience and making it easier for developers to diagnose issues. It also keeps the controller methods cleaner and focused solely on their primary responsibilities.

### 5. MapStruct
- **Reason**: To automate the mapping process between the database entities and the API response models, thereby reducing the manual coding required for these conversions.
- **Impact**: Improves the speed and reliability of the development process. This approach significantly reduces boilerplate code and potential human errors in manually mapping fields, leading to cleaner and more maintainable code.

### 6. Logging
- **Reason**: To provide a way to output debug information, trace application flow, and record exceptions or unusual circumstances. Logging is essential for monitoring the application's behavior in development, testing, and production environments.

### 7. Dockerfile
- **Reason**: To define the environment for the application, ensuring that it runs consistently across different development, testing, and production settings.
- **Impact**: Simplifies deployment and scaling by encapsulating the application and its dependencies in a container, which can be run on any system that supports Docker, thus eliminating the "it works on my machine" problem.

### 8. Docker Compose
- **Reason**: To define and run multi-container Docker applications. In this project, Docker Compose is used to run up the microservice, and it allow to add new containers if they were required to run the app.
- **Impact**: Enhances local development by allowing developers to easily spin up the entire stack with a single command, ensuring that all services are networked correctly with minimal setup.

### 9. CI/CD Pipeline
- **Reason**: To automate the processes of building, testing, and deploying the application, ensuring that changes are smoothly integrated and deployed without manual steps.
- **Impact**: Increases the reliability of the release process by automatically running tests. This helps to catch issues early and reduces the risk of downtime or bugs making it into production.


