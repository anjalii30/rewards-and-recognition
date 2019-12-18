# Rewards and Recognition tool

Rewards and Recognition tool is a web based application designed specially for making the task of Nineleaps HR team simpler to help them create, monitor, maintain rewards as well as the Nineleaps employess to view who are rewarded.

The main components of this application are the HR team able to create, edit, roll out, discontinue and pubish rewards, the managers able to nominate their subordinates.

## Technologies Used
+ Front End -> ReactJs
+ Back End -> Spring Boot

Further reading provides you with the introduction and explanation of the backend functionalities of the application.

## Built With
+ Maven - Dependency Management
+ JDK - Java™ Platform, Standard Edition Development Kit
+ Spring Boot - Framework to ease the bootstrapping and development of new Spring Applications
+ MySQL - Open-Source Relational Database Management System
+ git - Free and Open-Source distributed version control system
+ Postman - API Development Environment
+ Swagger - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

## Getting Started
There are several ways to run a Spring Boot application on your local machine.

1. Download the zip or clone the Git repository.
2. Unzip the zip file (if you downloaded one)
3. Open Command Prompt and Change directory (cd) to folder containing build.maven
4. Open your IDE (We used IntelliJ Community)
5. File -> Open
6. In the dialog that opens, select the file that contains a Maven project description build.maven. Click OK.
7. In the dialog that opens, click Open as Project. IntelliJ IDEA opens and syncs the project in the IDE.
8. Choose the Spring Boot Application file (search for @SpringBootApplication)
9. Right Click on the file and Run as Java Application

## Prerequisites
1. MySQL should be installed and configured
2. JDK 8 or higher
3. A public URL
4. A Google email ID that does not use 2 step verification

## Files and directories
.
├── Documents
│   └── GIT Branching Strategy
├── mvnw
├── mvnw.cmd
├── pom.xml
├── rar.iml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── rar
│   │   │           ├── controller
│   │   │           │   ├── CriteriaController.java
│   │   │           │   ├── DesignationController.java
│   │   │           │   ├── LoginController.java
│   │   │           │   ├── ManagerController.java
│   │   │           │   ├── NominationController.java
│   │   │           │   ├── ProjectController.java
│   │   │           │   ├── RewardsController.java
│   │   │           │   ├── RewardsCriteriaController.java
│   │   │           │   ├── RolesController.java
│   │   │           │   └── UserController.java
│   │   │           ├── DTO
│   │   │           │   ├── CreateProjectPojo.java
│   │   │           │   ├── DesignationSelected.java
│   │   │           │   ├── EditUserDetails.java
│   │   │           │   ├── EvidencesPojo.java
│   │   │           │   ├── LoginUserDetails.java
│   │   │           │   ├── NominationPojo.java
│   │   │           │   ├── ProjectDetailsUser.java
│   │   │           │   ├── RewardsCriteriaId.java
│   │   │           │   └── UserProjectsPojo.java
│   │   │           ├── enums
│   │   │           │   ├── CategoryEnum.java
│   │   │           │   ├── DesignationEnum.java
│   │   │           │   ├── FrequencyEnum.java
│   │   │           │   └── RoleEnum.java
│   │   │           ├── exception
│   │   │           │   ├── InvalidProjectException.java
│   │   │           │   ├── InvalidTokenException.java
│   │   │           │   ├── InvalidUserException.java
│   │   │           │   └── ResourceNotFoundException.java
│   │   │           ├── model
│   │   │           │   ├── Criteria.java
│   │   │           │   ├── Designation.java
│   │   │           │   ├── Evidences.java
│   │   │           │   ├── Manager.java
│   │   │           │   ├── Nominations.java
│   │   │           │   ├── Projects.java
│   │   │           │   ├── RewardsCriteria.java
│   │   │           │   ├── Rewards.java
│   │   │           │   ├── Roles.java
│   │   │           │   └── UserInfo.java
│   │   │           ├── repository
│   │   │           │   ├── CriteriaRepository.java
│   │   │           │   ├── DesignationRepository.java
│   │   │           │   ├── EvidencesRepository.java
│   │   │           │   ├── ManagerRepository.java
│   │   │           │   ├── NominationsRepository.java
│   │   │           │   ├── ProjectRepository.java
│   │   │           │   ├── RewardsCriteriaRepository.java
│   │   │           │   ├── RewardsRepository.java
│   │   │           │   ├── RolesRepository.java
│   │   │           │   └── UserRepository.java
│   │   │           ├── RewardsAndRecognitionApplication.java
│   │   │           ├── service
│   │   │           │   ├── CriteriaService.java
│   │   │           │   ├── DesignationService.java
│   │   │           │   ├── impl
│   │   │           │   │   ├── CheckValidity.java
│   │   │           │   │   ├── CriteriaServiceImpl.java
│   │   │           │   │   ├── DesignationServiceImpl.java
│   │   │           │   │   ├── GenerateJWT.java
│   │   │           │   │   ├── LoginServiceImpl.java
│   │   │           │   │   ├── ManagerServiceImpl.java
│   │   │           │   │   ├── NominationsServiceImpl.java
│   │   │           │   │   ├── ProjectServiceImpl.java
│   │   │           │   │   ├── RewardsCriteriaServiceImpl.java
│   │   │           │   │   ├── RewardsServiceImpl.java
│   │   │           │   │   ├── RolesServiceImpl.java
│   │   │           │   │   ├── SendEmail.java
│   │   │           │   │   └── UserServiceImpl.java
│   │   │           │   ├── LoginService.java
│   │   │           │   ├── ManagerService.java
│   │   │           │   ├── NominationsService.java
│   │   │           │   ├── ProjectService.java
│   │   │           │   ├── RewardsCriteriaService.java
│   │   │           │   ├── RewardsService.java
│   │   │           │   ├── RolesService.java
│   │   │           │   └── UserService.java
│   │   │           └── utils
│   │   │               ├── AskToNominate.java
│   │   │               ├── ScheduleRewards.java
│   │   │               ├── SpringWebConfig.java
│   │   │               └── Swagger2Config.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── gift.jpg
│   │       ├── image.jpg
│   │       ├── nineleaps.png
│   │       └── templates
│   │           ├── selfWinner.html
│   │           └── Winner.html
│   └── test
│       └── java
│           └── com
│               └── rar
│                   └── RewardsAndRecognitionApplicationTests.java
└── target
    ├── classes
    │   ├── application.properties
    │   ├── com
    │   │   └── rar
    │   │       ├── controller
    │   │       │   ├── CriteriaController.class
    │   │       │   ├── DesignationController.class
    │   │       │   ├── LoginController.class
    │   │       │   ├── ManagerController.class
    │   │       │   ├── NominationController.class
    │   │       │   ├── ProjectController.class
    │   │       │   ├── RewardsController.class
    │   │       │   ├── RewardsCriteriaController.class
    │   │       │   ├── RolesController.class
    │   │       │   └── UserController.class
    │   │       ├── DTO
    │   │       │   ├── CreateProjectPojo.class
    │   │       │   ├── DesignationSelected.class
    │   │       │   ├── EditUserDetails.class
    │   │       │   ├── EvidencesPojo.class
    │   │       │   ├── LoginUserDetails.class
    │   │       │   ├── NominationPojo.class
    │   │       │   ├── ProjectDetailsUser.class
    │   │       │   ├── RewardsCriteriaId.class
    │   │       │   └── UserProjectsPojo.class
    │   │       ├── enums
    │   │       │   ├── CategoryEnum.class
    │   │       │   ├── DesignationEnum.class
    │   │       │   ├── FrequencyEnum.class
    │   │       │   └── RoleEnum.class
    │   │       ├── exception
    │   │       │   ├── InvalidProjectException.class
    │   │       │   ├── InvalidTokenException.class
    │   │       │   ├── InvalidUserException.class
    │   │       │   └── ResourceNotFoundException.class
    │   │       ├── model
    │   │       │   ├── Criteria.class
    │   │       │   ├── Designation.class
    │   │       │   ├── Evidences.class
    │   │       │   ├── Manager.class
    │   │       │   ├── Nominations.class
    │   │       │   ├── Projects.class
    │   │       │   ├── Rewards.class
    │   │       │   ├── RewardsCriteria.class
    │   │       │   ├── Roles.class
    │   │       │   └── UserInfo.class
    │   │       ├── repository
    │   │       │   ├── CriteriaRepository.class
    │   │       │   ├── DesignationRepository.class
    │   │       │   ├── EvidencesRepository.class
    │   │       │   ├── ManagerRepository.class
    │   │       │   ├── NominationsRepository.class
    │   │       │   ├── ProjectRepository.class
    │   │       │   ├── RewardsCriteriaRepository.class
    │   │       │   ├── RewardsRepository.class
    │   │       │   ├── RolesRepository.class
    │   │       │   └── UserRepository.class
    │   │       ├── RewardsAndRecognitionApplication.class
    │   │       ├── service
    │   │       │   ├── CriteriaService.class
    │   │       │   ├── DesignationService.class
    │   │       │   ├── impl
    │   │       │   │   ├── CheckValidity.class
    │   │       │   │   ├── CriteriaServiceImpl.class
    │   │       │   │   ├── DesignationServiceImpl.class
    │   │       │   │   ├── GenerateJWT.class
    │   │       │   │   ├── LoginServiceImpl.class
    │   │       │   │   ├── ManagerServiceImpl.class
    │   │       │   │   ├── NominationsServiceImpl.class
    │   │       │   │   ├── ProjectServiceImpl.class
    │   │       │   │   ├── RewardsCriteriaServiceImpl.class
    │   │       │   │   ├── RewardsServiceImpl.class
    │   │       │   │   ├── RolesServiceImpl.class
    │   │       │   │   ├── SendEmail.class
    │   │       │   │   └── UserServiceImpl.class
    │   │       │   ├── LoginService.class
    │   │       │   ├── ManagerService.class
    │   │       │   ├── NominationsService.class
    │   │       │   ├── ProjectService.class
    │   │       │   ├── RewardsCriteriaService.class
    │   │       │   ├── RewardsService.class
    │   │       │   ├── RolesService.class
    │   │       │   └── UserService.class
    │   │       └── utils
    │   │           ├── AskToNominate.class
    │   │           ├── ScheduleRewards.class
    │   │           ├── SpringWebConfig.class
    │   │           └── Swagger2Config.class
    │   ├── gift.jpg
    │   ├── image.jpg
    │   ├── META-INF
    │   │   └── rar.kotlin_module
    │   ├── nineleaps.png
    │   └── templates
    │       ├── selfWinner.html
    │       └── Winner.html
    ├── generated-sources
    │   └── annotations
    ├── generated-test-sources
    │   └── test-annotations
    ├── sonar
    │   └── report-task.txt
    └── test-classes
        └── com
            └── rar
                └── RewardsAndRecognitionApplicationTests.class


```
## Packages
- `models` — to hold our entities;
- `repositories` — to communicate with the database;
- `services` — to hold our business logic;
- `controllers` — to listen to the client;
- `resources/` - Contains all the static resources, templates and property files;
- `resources/templates` - contains server-side templates which are rendered by Spring;
- `resources/application.properties` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file;
- `build.maven` - contains all the project dependencies;
- `dto` - contains all the pojos for getting and setting the values in the repository.

## API Reference
The Test cases for the application can be found [here]().

The Swagger UI can be referred [here](/swagger-ui.html)

## License
&copy; Nineleaps | 2019 
