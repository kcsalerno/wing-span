# Capstone Project: Wing\<Span/>

## User Stories
* As new user:
   * There is a welcome page where I can read all birds, their traits, and sightings without logging in.
   * There will be a navigation bar with:
      * Home
      * Birds
      * Sightings
      * Profile/Register
   * I need to log in in order to add sightings.
   * This will prompt a form where the following information is needed:
      * First Name - Required
      * Last Name - Required
      * Username - Unique, Required
      * Password - Required
      * Email - Unique, Required
   * If all the information is entered correctly, I will be brought back to the home page logged in.
      * If any of the information is entered incorrectly, an error message will be displayed and the user will be reprompted to fill out the form.
   * Once logged in and authenticated: 
      * The login button will switch to a logout button and a view profile button will be appear next to it.
         * A user can add an avatar to their profile from a list of provided birds photos.
      * I will have the ability to read all birds and their traits.
         * There will be a list of all birds and their traits listed below each bird.
      * I will have the ability to create, edit and delete sightings attached to my profile.
         * There will be an Add/Edit form the requies a:
            * Bird
            * Date
         * I will not be able to edit or delete other user's sightings.
   * If I am an application administator (back-end):
      * I will be able to create, edit, and delete birds to keep our application correct and up to date.
         * There will be an Add/Edit form that requires a: 
            * Common Name
            * Scentific Name
            * Traits
         * If a bird is deleted, so will the sighting.
      * I will be able to create, edit, and delete traits to keep our application correct and up to date.
         * There will be an Add/Edit form that requires a:
            * Name
         * If a trait is deleted, it will be deleted off the bird.
      * I will be able to create, edit, and delete all sightings to keep our application correct and up to date.
         * There will be an Add/Edit form the requies a:
            * Username
            * Bird
            * Date
         * If a sighting is deleted, it will be deleted from the user.
   * I will be able to logout and returned to the home page when I do.
 
## Capstone Proposal

### 1. Problem Statement

#### Problem

Currently, there is no wold-wide competitive birding organization. Stepping in to fill the void is Wing\<Span/>. Wing\<Span/> is the first competitive birding app that connects users across the world. Not only does Wing\<Span/> track your sightings, it also allows you to view other user's sightings, and collect badges for your sightings too!

Having a "Life List" is great, but it's also nice to see how you stack up to other birders across the globe! Plus, this provides valuable insights into which birds are likely to be seen in certain locations on various dates.

### 2. Technical Solution

Briefly describe a technical solution to your problem with a couple concrete scenarios.

#### Solution

Create an application for posting bird sightings. Making it easy to track your progress and compare yourself to other users.

#### Scenario 1
Jane has been a passive bird watcher for a while now, but she's been looking for a way to track the birds she's seen. She also wonders how her sightings compare to other bird watchers - not just in her area, but the world over. She finds Wing\<Span/>, signs up for an account, and starts posting sightings. She gets her badge for posting 5 sightings and now she's hooked. Within a month, Jane has earned her 20 sightings badge. It's helping her get outdoors more and she's learned a lot about birds in the process.

#### Scenario 2
John has been an avid bird watcher all of his life. He has a journal that he keeps with all of his sightings. John has been looking for a more convenient and permanent way to log his sightings. Something that might also offer easier insight into his bird watching stats. John discovers Wing\<Span/> from a friend. He uploads all his sightings from his journal and realizes he's earned a lot of badges. He's excited to find out that he has more badges than the friend that told him about the app in the first place. This motivates John to plan specific trips so he can log more sightings and get more badges.

### 3. Glossary

#### Bird
* An actual bird with traits.

#### Trait
* A bird trait.

#### Sighting
* A sighting of a particular bird on a date at a location.

#### Bird-Watcher (User)
* Anyone who watches birds and records their sightings.

#### Badge
* An award based on meeting certain sighting goals.

#### Avatar
* A profile image for the user.

#### Admin
* Someone who maintains the bird sightings log.

### 4. High Level Requirement

Briefly describe what each user role/authority can do. (These are user stories.)

* Create a sightings (MEMBER, ADMIN).
* Edit a sighting (MEMBER, ADMIN).
* Delete a sighting attached to their profile (MEMBER).
* Delete any sighting (ADMIN).
* Browse sightings (anyone).
* Create a bird (ADMIN).
* Edit a bird (ADMIN).
* Delete a bird (ADMIN).
* Browse birds (anyone).
* Earn badges (MEMBER).
* Edit badges (ADMIN).
* Delete badges (ADMIN).
* Browse badges (anyone).
* Create an avatar (ADMIN).
* Edit an avatar (ADMIN).
* Delete an avatar (ADMIN).
* Browse and use an avatar (MEMBER, ADMIN).

### 5. User Stories/Scenarios

Elaborate user stories.

#### Create a Sighting
 
Log a sighting to a user's profile.

Suggested data:
* bird id (to connect the sighting to the bird) from a list of available birds
* user id (to connect the sighting to the MEMBER)
* date (which would need to be in the past or at the current moment)
 
**Precondition**: User must be logged in with the MEMBER or ADMIN role.
 
**Post-condition**: None
 
#### Edit a Sighting
 
Can only edit a sighting in the past.
 
**Precondition**: User must be logged in with the MEMBER or ADMIN role. A MEMBER can only edit sightings attached to their profile. An ADMIN can edit any and all sightings. Sighting datetime must be in the past.
 
**Post-condition**: None
 
#### Delete a Sighting
 
Can only delete sightings in the past.
 
**Precondition**: User must be logged in with the MEMBER of ADMIN role. A MEMBER can only delete sightings attached to their profile. An ADMIN can delete any and all sightings. Sighting datetime must be in the past. 
 
**Post-condition**: Data is deleted.
 
#### Browse Sightings
 
Text-based: Users see a list of sightings ordered by date. If a user wants to learn more about a bird, they can click on the bird and be taken to the birds specific page with traits. Display results as HTML with action UI to sign up.
 
**Precondition**: Anyone can view the list of sightings. They must be a MEMBER or ADMIN to contribute to the list of sightings in any way.
 
**Post-condition**: None
 
#### Create a Bird
 
Add a bird to our list of available birds to sight.
 
**Precondition**: User must be logged in with the ADMIN role.
 
**Post-condition**: Data is added to our database. The bird becomes available to view by anyone. The bird can be used in a MEMBER sighting.
 
#### Edit a Bird
 
Edit any bird in our list of birds.
 
**Precondition**: User must be logged in with the ADMIN role.
 
**Post-condition**: Data is updated to our database.
 
#### Delete a Bird
 
Delete any bird in our list of birds.
 
**Precondition**: User must be logged in with the ADMIN role.
 
**Post-condition**: Data is deleted from our database.
 
#### Browse Birds
 
View a list of all birds and their traits.
 
**Precondition**: Anyone can view our list of birds.
 
**Post-condition**: None.
 
#### Create a Badge
 
Add a badge to our available badges for users to earn.
 
**Precondition**: User must be logged in with the ADMIN role.
 
**Post-condition**: Data is added to our database. The badge can be earned by a MEMBER if they meet the criteria of sightings.
 
#### Edit a Badge
 
Edit any badge in our list of badges.
 
**Precondition**: User must be logged in with the ADMIN role.
 
**Post-condition**: Data is updated to our database.
 
#### Delete a Badge
 
Delete any badge in our list of badges.
 
**Precondition**: User must be logged in with the ADMIN role.
 
**Post-condition**: Data is deleted from our database.
 
#### Browse Badges
 
View a list of all badges a MEMBER can earn.
 
**Precondition**: Anyone can view our list of birds.
 
**Post-condition**: None.
 
#### Create a Avatar
 
Add an avatar to our list of available avatars for a MEMBER to use for their profile.
 
**Precondition**: User must be logged in with the ADMIN role.
 
**Post-condition**: Data is added to our database. The avatar becomes available to use in by MEMBER.
 
#### Edit a Avatar
 
Edit any avatar in our list of avatars.

**Precondition**: User must be logged in with the ADMIN role.
 
**Post-condition**: Data is updated to our database.
 
#### Delete a Avatar
 
Delete any avatar in our list of avatar.
 
**Precondition**: User must be logged in with the ADMIN role.
 
**Post-condition**: Data is deleted from our database.
 
#### Browse and choose and Avatar
 
View a list of all avatars available to use by a MEMBER.
 
**Precondition**: User must be logged in with the MEMBER or ADMIN role.
 
**Post-condition**: None. 

## High Level Requirements
*  A schedule of concrete tasks (at most 4 hours per task) that represent all work required to finish your project along with task statuses
* Diagrams: database schema, class, layer, flow
* Wireframes: roughly sketch your UI and how one view transitions to another. You can also use design tools to create wireframes.
* A short presentation, 4 to 6 slides, describing who you are, how you found programming, and your project
* Complete project source code free of compiler errors
* A schema creation script along with any DML for data needed to run the application (security roles, default data, etc)
* If it isn't straight-forward, instructions to set up and run your application
* A complete test suite with all tests passing

## Technical Requirements
* Manage 4-7 database tables (entities) that are independent concepts. A simple bridge table doesn't count.
* Relational database for data management
* Spring Boot, MVC (@RestController), JdbcTemplate, Testing
* An HTML and CSS UI built with React
* Sensible layering and pattern choices
* At least one UI secured by role
* A full test suite that covers the domain and data layers.
* Learning Goal: AWS Hosting

## High Level Tasks
* [ ] Database Layer Tasks
* [ ] API Data Layer Tasks
* [ ] API Domain Layer Tasks
* [ ] API Model and Controller Layer Tasks
* [ ] UI Front End Layer Tasks
* [ ] Security Layer Tasks
* [ ] AWS Hosting Tasks
* [ ] Further Development Tasks

### Database Layer Tasks
* [ ] Design initial database schema (#.# hours)
  * [ ] Identify the tables that are needed
  * [ ] For each table...
    * Define a primary key
    * Define its columns (name, data type, and nullability)
  * [ ] Identify the relationships between tables
    * Define any foreign keys
  * [ ] Identify any default or unique constraints
* [ ] Draw a diagram of the database schema (#.# hours)
  * [ ] Share with one or more classmates for feedback
  * [ ] Update database schema as needed
  * [ ] Share updated diagram with one or more classmates
  * [ ] Update database schema as needed
  * [ ] Share with assigned code reviewer for feedback
* [ ] Write the DDL in a file named `auth-schema-data.sql` to create the security database (#.# hours)
  * [ ] Drop and create the database
  * [ ] Create all of the tables
* [ ] Write the DML in the file named `auth-schema-data.sql` to populate the database tables (#.# hours)
* [ ] Write the DDL in a file named `schema.sql` to create the main database (#.# hours)
  * [ ] Drop and create the database
  * [ ] Create all of the tables
* [ ] Create data (#.# hours)
  * [ ] Enter initial data for each table
  * [ ] Enter data for the bird, badge, and avatar tables
  * [ ] Run the provided SQL script
* [ ] Write the DML in a file named `data.sql` to populate the database tables (#.# hours)
  * [ ] Write insert-from-select statements to move the data from the denormalized table to the normalized tables

### Data Layer Tasks
* [ ] Create AppUserJdbcTemplateRepository and AppUserRepository Interface
  * [ ] Create appropriate CRUD methods
    * Find a user by id
    * Find all users
    * Create/register user
    * Update user profile (info)
    * Delete/Disable user account
* [ ] Create KnownGoodState in \test\java\wing-span\data
* [ ] Create tests for AppUserJdbcTemplateRepository
* [ ] Create BirdJdbcTemplateRepository and BirdRepository Interface
  * [ ] Create appropriate CRUD methods
    * Find a bird by id
    * Find all birds
    * Add a bird
    * Update bird info
    * Delete/Disable a bird
* [ ] Create tests for BirdJdbcTemplateRepository
* [ ] Create SightingJdbcTemplateRepository and SightingRepository Interface
  * [ ] Create appropriate CRUD methods
    * Find a sighting by id
    * Find all sightings
    * Add a sighting
    * Update sighting info
    * Delete/Disable a sighting
* [ ] Create tests for SightingJdbcTemplateRepository
* [ ] Create TraitJdbcTemplateRepository and TraitRepository Interface
  * [ ] Create appropriate CRUD methods
    * Find a trait by id
    * Find all traits
    * Add a trait
    * Update trait info
    * Delete/Disable a trait
* [ ] Create tests for TraitJdbcTemplateRepository
* [ ] Create BadgeJdbcTemplateRepository and BadgeRepository Interface
  * [ ] Create appropriate CRUD methods
    * Find a badge by id
    * Find all badges
    * Add a badge
    * Update badge info
    * Delete/Disable a badge
* [ ] Create tests for BadgeJdbcTemplateRepository
* [ ] Create AvatarJdbcTemplateRepository and AvatarRepository Interface
  * [ ] Create appropriate CRUD methods
    * Find an avatar by id
    * Find all avatars
    * Add an avatar
    * Update avatar info
    * Delete/Disable an avatar
* [ ] Create tests for AvatarJdbcTemplateRepository 
* [ ] Create AppUserMapper
* [ ] Create BirdMapper
* [ ] Create SightingMapper
* [ ] Create TraitMapper
* [ ] Create BadgeMapper
* [ ] Create AvatarMapper

### Domain Layer Tasks
* [ ] domain.SightingService
  * [ ] implement CRUD methods
    * `findAll`
    * `findSightingsById`
    * `addSighting`
    * `editSighting`
    * `deleteSighting`
  * [ ] Generate tests for domain.SightingService
    * Happy/ Unhappy Path 
* [ ] domain.TraitService
  * [ ] implement CRUD methods
    * `findTraitByBird`
    * `addTrait`
    * `editTrait`
    * `deleteTrait`
  * [ ] Generate tests for domain.SightingService
    * Happy/ Unhappy Path
* [ ] domain.BirdService
  * [ ] implement CRUD methods
    * `addBird`
    * `editBird` (how can the admin edit a bird?)
    * `deleteBird`
  * [ ] Generate tests for domain.SightingService
    * Happy/ Unhappy Path
* [ ] domain.BadgeService
    * `findAllBadges`
    * `findBadgeById`
  * [ ] Generate Tests for domain.BadgeService
    * Happy / Unhappy path
* [ ] domain.AvatarService
  * [ ] implement CRUD methods
    * `findAllAvatars`
    * `findAvatarById`
    * `addAvatar`
    * `editAvatar`
    * `deleteAvatar`
  * [ ] Generate tests for domain.AvatarService
    * Happy / Unhappy path
* [ ] domain.Validations
  * [ ] Validate sightings
    * Sighting cannot be a duplicate
    * Date cannot be null
  * [ ] Validate birds
  * [ ] Validate traits
* [ ] domain.ResultType
  * `Enum`
* [ ] domain.Result
  * `Payload`
  *  `ArrayList<String> messages`

### Model and Controller Layer Tasks
#### In the `controller` package
* [ ] ErrorResponse Class
* [ ] Create GlobalExceptionHandler
* [ ] Create AuthController
* [ ] Create BirdController
* [ ] Create SightingController
* [ ] Create TraitController
* [ ] Create BadgeController
* [ ] Create AvatarController
#### In the `model` package
* [ ] Create AppUser model
* [ ] Create Bird model
* [ ] Create Sighting model
* [ ] Create Trait model
* [ ] Create Badge model
* [ ] Create Avatar model
#### In the `wing-span` package
* [ ] Create App Class
* [ ] Create AppConfig Class
#### In the `java` package
* [ ] Create the resources package
* [ ] Create application properties
* [ ] Configure enviroment variables
* [ ] Double check all annotations

### UI (Front End) Layer Tasks

### Security Layer Tasks
* [ ] Create Schema to add Users and Roles to database
  * [ ] Add app_user, app_role, app_user_role and appropriate data to the schema.
* [ ] Configure Spring Security
  * [ ] Configure the project to utilize the spring-boot-starter-security dependency.
* [ ] Create AppConfig
  * [ ] Create the AppConfig class and define the PasswordEncoder bean. Define the WebMvcConfigurer bean to configure CORS globally.
* [ ] Create AppUser
  * [ ] Add the AppUser to the models package, implementing UserDetails and mapping to the created fields.
* [ ] Create AppUserRepository
  * [ ] Create the interface and repository to add AppUserRepository interface and AppUserJdbcTemplateRepository class.
* [ ] Create AppUserService
  * [ ] Create the AppUserService which implements org.springframework.security.core.userdetails.UserDetailsService
    * The UserDetailsService interface loads user-specific data.
    * The interface requires only one read-only method, which simplifies support for new data-access strategies.
* [ ] Add JWT to project, creating JWT Converter and JWT Filter
  * [ ] Continue the security implementation to include the JWT dependencies, and then create in the security package the JwtConverter and JwtRequestFilter classes
* [ ] Create Security Config
  * [ ] Create the SecurityConfig class connecting the above steps.
* [ ] Create AuthController
  * [ ] Create the AuthController in the controllers package to implement the /api/authenticate and /api/create_account endpoints.
  * [ ] Update the security configuration to allow these endpoints to be accessed without credentials.
* [ ] Configure AuthController, SecurityConfig, and AppUser Repo & Service to allow for creating an account
* [ ] Configure AuthController, SecurityConfig, and AppUser Repo & Service to allow for editing an account

### AWS Hosting Tasks

### Further Development
* [ ] Create leaderboard page
  * [ ] Create necessary componenets, services, and configure routes
  * [ ] Create HTTP requests to support different queries
* [ ] Create stats page
  * [ ] Create necessary componenets, services, and configure routes
  * [ ] Create HTTP requests to support different queries
* [ ] Implement CRUD methods from back to front for an ADMIN UI that works for Badges, Birds, and Avatars
## Schedule

## Class Diagram
```
wing-span
|
├───wing-span-db
|       schema.sql
|       initial-data.sql
|       auth-schema-data.sql
|
├───wing-span-ui
|    ├───public
|    |       favicon.ico (optional)
|    |       index.html
|    | 
│    └───src
|         |   App.js
|         |   index.js
|         |
|         ├───components
|         |        Error.js
|         |        About.js
|         |        Contact.js
|         |        Login.js
|         |        Not Found.js
|         |        Register.js
|         |        Delete.js
|         |        Navigation.js
|         |        Form.js (Can we conditionally render the form based on type?)
|         |        List.js (Can we conditionally render the list based on type?)
|         |        
|         ├───contexts
|         |        AuthContext.js
|         |   
|         └───services
|                  auth.js
|                  birds.js 
|                  sightings.js 
|                  traits.js 
|         
└───wing-span-api
    |       pom.xml
    |
    ├───http
    |       request.http 
    |   
    └───src
          ├───main
          │   ├───java
          │   │    └───wing-span
          │   │           │     App.java
          │   │           │     AppConfig.java
          │   │           │
          │   │           ├───data
          │   │           │   |   AppUserJdbcTemplateRepository.java
          │   │           │   |   AppUserRepository.java
          │   │           │   |   BirdJdbcTemplateRepository.java
          │   │           │   |   BirdRepository.java
          │   │           │   |   SightingJdbcTemplateRepository.java
          │   │           │   |   SightingRepository.java
          │   │           │   |   TraitJdbcTemplateRepository.java
          │   │           │   |   TraitRepository.java
          │   │           │   |   BadgeJdbcTemplateRepository.java
          │   │           │   |   BadgeRepository.java
          │   │           │   |   AvatarJdbcTemplateRepository.java
          │   │           │   |   AvatarRepository.java
          |   |           |   |
          │   │           │   └───mappers    
          |   |           |           AppUserMapper.java
          |   |           |           BirdMapper.java
          │   │           │
          │   │           ├───domain
          │   │           │       Result.java
          │   │           │       ResultType.java
          │   │           │       Validations.java
          │   │           │       BirdService.java
          │   │           │       SightingService.java
          │   │           │       TraitService.java
          │   │           │       BadgeService.java
          │   │           │       AvatarService.java
          │   │           │
          │   │           ├───models
          │   │           │       AppUser.java
          │   │           │       Bird.java
          │   │           │       Sighting.java
          │   │           │       Trait.java
          │   │           │       Badge.java
          │   │           │       Avatar.java
          │   │           │
          │   │           ├───controllers
          │   │           │       ErrorResponse.java
          │   │           │       GlobalExceptionHandler.java
          │   │           │       AuthController.java
          │   │           │       BirdController.java
          │   │           │       SightingController.java
          │   │           │       TraitController.java
          │   │           │       BadgeController.java
          │   │           │       AvatarController.java
          │   │           │
          |   |           └───security
          │   │                   AppUserService,java
          │   │                   JwtConverter.java
          │   │                   JwtRequestFilter.java
          │   │                   SecurityConfig.java
          │   │
          │   └───resources
          |               application.properties
          └───test
              ├───java
              |   └───wing-span
              |           ├───controllers
              |           |       BirdControllerTest.java
              │           │       SightingControllerTest.java
              │           │       TraitControllerTest.java
              │           │       BadgeControllerTest.java
              │           │       AvatarControllerTest.java
              |           |
              |           ├───data
              |           │       KnownGoodState
              |           |       BirdJdbcTemplateRepositoryTest.java
              |           |       SightingJdbcTemplateRepositoryTest.java
              |           |       TraitJdbcTemplateRepositoryTest.java
              |           |       BadgeJdbcTemplateRepositoryTest.java
              |           |       AvatarJdbcTemplateRepositoryTest.java
              |           |
              |           └───domain
              |                   BirdServiceTest.java
              │                   SightingServiceTest.java
              │                   TraitServiceTest.java
              │                   BadgeServiceTest.java
              │                   AvatarServiceTest.java
              └───resources
                          application.properties
```

## Approach
* Planning is absolutely essential for a project this large.
    * Create a complete list of concrete tasks required to finish
    * Tasks should take no longer than 4 hours. Schedule each task for a specific day.
    * At the end of each day, take stock. Are you ahead of schedule, on schedule, or behind?
        * If ahead, how can you put the extra time to use?
        * If behind, what do you have to adjust to complete the project? Don't just hope that things will improve. Take concrete steps to simplify or remove features.
* Ask questions. Even though you control the specification, your classmates and instructors are invaluable resources.
    * Ask clarifying questions. Don't make assumptions when things aren't clear.
* Work back to front - either by method or by layer.
* Test as you go.
* Get the main CRUD features working back to front, then add in Security and extra features/pages.
