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
```
├── build.gradle
├── Documents
│   ├── Git strategy.md
│   ├── Schema.png
│   └── SonarReport.pdf
├── gradlew
├── gradlew.bat
├── HELP.md
├── README.md
├── settings.gradle
└── src
    └── main
       ├── java
       │   └── com
       │       └── demo
       │           └── okrmanagement
       │               ├── config
       │               │   ├── APIConfiguration.java
       │               │   ├── EmailConfig.java
       │               │   ├── GmailAuthInterface.java
       │               │   ├── RetrofitInterface.java
       │               │   ├── RetrofitService.java
       │               │   ├── ServiceConfiguration.java
       │               │   └── SwaggerConfig.java
       │               ├── controller
       │               │   ├── ActivityController.java
       │               │   ├── ChecksController.java
       │               │   ├── CollaboratorController.java
       │               │   ├── LoginController.java
       │               │   ├── ObjectiveChallengeController.java
       │               │   ├── ObjectiveController.java
       │               │   ├── TrackProgressController.java
       │               │   ├── UpcomingTaskController.java
       │               │   └── WeeklyPlanController.java
       │               ├── dto
       │               │   ├── ActivityPojos
       │               │   │   ├── ActivityPojo.java
       │               │   │   └── ActivityResponsePojo.java
       │               │   ├── CheckPojos
       │               │   │   ├── CheckPojo.java
       │               │   │   ├── CheckResponsePojo.java
       │               │   │   └── CheckStatusPojo.java
       │               │   ├── CollaboratorPojos
       │               │   │   ├── CollabPojo.java
       │               │   │   └── InvitationPojo.java
       │               │   ├── KeyResultPojos
       │               │   │   ├── KeyResultPojo.java
       │               │   │   └── KeyResultResponsePojo.java
       │               │   ├── ObjectiveChallengePojos
       │               │   │   ├── ObjectiveChallengeCommentPojo.java
       │               │   │   ├── ObjectiveChallengeCommentReturnPojo.java
       │               │   │   ├── ObjectiveChallengePojo.java
       │               │   │   └── ObjectiveChallengeReturnPojo.java
       │               │   ├── ObjectivePojos
       │               │   │   ├── ObjectiveCardsPojo.java
       │               │   │   ├── ObjectivePojo.java
       │               │   │   └── ObjectiveResponsePojo.java
       │               │   ├── ProgressPojos
       │               │   │   ├── ConfidenceMappingPojo.java
       │               │   │   ├── KeyResultProgressGraphDataPojo.java
       │               │   │   ├── KeyResultProgressHistoryPojo.java
       │               │   │   ├── TrackConfidencePojo.java
       │               │   │   ├── UpdateConfidencePojo.java
       │               │   │   └── WeeklyPlanReportPojo.java
       │               │   ├── UpcomingTaskPojo
       │               │   │   ├── ResponseUpcomingTasksPojo.java
       │               │   │   ├── UpcomingTaskDetailsPojo.java
       │               │   │   └── UpcomingTaskPojo.java
       │               │   ├── UserInfoPojos
       │               │   │   ├── GmailPojo.java
       │               │   │   └── UserPojo.java
       │               │   └── WeeklyPlanPojos
       │               │       ├── CommentDetailsPojo.java
       │               │       ├── ReturnWeeklyPojo.java
       │               │       ├── WeeklyPlanCommentPojo.java
       │               │       ├── WeeklyPlanCommentReplyPojo.java
       │               │       └── WeeklyPlanPojo.java
       │               ├── exception
       │               │   ├── BadRequestException.java
       │               │   ├── DuplicateInvitationException.java
       │               │   ├── DuplicateUpdationException.java
       │               │   ├── InvalidDomainException.java
       │               │   ├── InvalidTokenException.java
       │               │   ├── InvalidUserException.java
       │               │   └── NoDataException.java
       │               ├── model
       │               │   ├── ActivityLog.java
       │               │   ├── Checks.java
       │               │   ├── Confidence.java
       │               │   ├── KeyResultProgressHistory.java
       │               │   ├── KeyResults.java
       │               │   ├── NineleapsUsers.java
       │               │   ├── ObjectiveChallengeComments.java
       │               │   ├── ObjectiveChallenges.java
       │               │   ├── Objectives.java
       │               │   ├── UpcomingTasks.java
       │               │   ├── UserInfo.java
       │               │   ├── UserObjectiveId.java
       │               │   ├── UserObjective.java
       │               │   ├── WeeklyPlanComments.java
       │               │   └── WeeklyPlan.java
       │               ├── OkrManagement.java
       │               ├── repository
       │               │   ├── ActivityLogRepository.java
       │               │   ├── ChecksRepository.java
       │               │   ├── ConfidenceRepository.java
       │               │   ├── KeyResultProgressHistoryRepository.java
       │               │   ├── KeyResultsRepository.java
       │               │   ├── NineleapsUsersRepository.java
       │               │   ├── ObjectiveChallengeCommentsRepository.java
       │               │   ├── ObjectiveChallengesRepository.java
       │               │   ├── ObjectiveRepository.java
       │               │   ├── UpcomingTaskRepository.java
       │               │   ├── UserObjectiveRepository.java
       │               │   ├── UserRepository.java
       │               │   ├── WeeklyPlanCommentRepository.java
       │               │   └── WeeklyPlanRepository.java
       │               ├── service
       │               │   ├── ActivityService.java
       │               │   ├── CheckService.java
       │               │   ├── CollaboratorService.java
       │               │   ├── impl
       │               │   │   ├── ActivityServiceImpl.java
       │               │   │   ├── CheckServiceImpl.java
       │               │   │   ├── CollaboratorServiceImpl.java
       │               │   │   ├── LoginServiceImpl.java
       │               │   │   ├── ObjectiveChallengesServiceImpl.java
       │               │   │   ├── ObjectiveServiceImpl.java
       │               │   │   ├── TrackProgressServiceImpl.java
       │               │   │   ├── UpcomingTaskServiceImpl.java
       │               │   │   └── WeeklyPlanServiceImpl.java
       │               │   ├── LoginService.java
       │               │   ├── ObjectiveChallengeService.java
       │               │   ├── ObjectiveService.java
       │               │   ├── TrackProgressService.java
       │               │   ├── UpcomingTaskService.java
       │               │   └── WeeklyPlanService.java
       │               └── util
       │                   ├── ArchiveObjectiveService.java
       │                   ├── DateProcessor.java
       │                   ├── KeyResultProgressService.java
       │                   ├── Progress.java
       │                   ├── SendMail.java
       │                   └── ValidateRequest.java
       └── resources
           ├── application.properties
           └── templates
               ├── invitation.ftl
               ├── invitationResponse.ftl
               ├── updatedConfidence.ftl
               ├── updateReminder.ftl
               └── welcome.ftl

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
