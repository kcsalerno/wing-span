# Project: Wing\<Span/>

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
 
## Outline

### 1. Problem Statement

#### Problem

Currently, there is no wold-wide competitive birding organization. Stepping in to fill the void is Wing\<Span/>. Wing\<Span/> is the first competitive birding app that connects users across the world. Not only does Wing\<Span/> track your sightings, it also allows you to view other user's sightings, and collect badges for your sightings too!

Having a "Life List" is great, but it's also nice to see how you stack up to other birders across the globe! Plus, this provides valuable insights into which birds are likely to be seen in certain locations on various dates.

### 2. Technical Solution

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

## High Level Tasks
* [X] Database Layer Tasks (9 hours)
* [X] API Data Layer Tasks (40 hours)
* [X] API Domain Layer Tasks (23 hours)
* [X] API Model and Controller Layer Tasks (21 hours)
* [X] UI Front End Layer Tasks (22 hours)
* [x] Security Layer Tasks (24 hours)
* [X] AWS Hosting Tasks (24 hours)
* [ ] Further Development Tasks (20 hours)

### Database Layer Tasks (9 hours)
* [X] Design initial database schema (1 hours)
  * [X] Identify the tables that are needed
  * [X] For each table...
    * Define a primary key
    * Define its columns (name, data type, and nullability)
  * [X] Identify the relationships between tables
    * Define any foreign keys
  * [X] Identify any default or unique constraints
* [X] Draw a diagram of the database schema (1 hours)
  * [X] Share with one or more classmates for feedback
  * [X] Update database schema as needed
  * [X] Share updated diagram with one or more classmates
  * [X] Update database schema as needed
  * [X] Share with assigned code reviewer for feedback
* [X] Write the DDL in a file named `auth-schema-data.sql` to create the security database (1 hours)
  * [X] Drop and create the database
  * [X] Create all of the tables
* [X] Write the DML in the file named `auth-schema-data.sql` to populate the database tables (1 hours)
* [X] Write the DDL in a file named `schema.sql` to create the main database (1 hours)
  * [X] Drop and create the database
  * [X] Create all of the tables
* [X] Create data (1 hours)
  * [X] Enter initial data for each table
  * [X] Enter data for the bird, badge, and avatar tables
  * [X] Run the provided SQL script
* [X] Write the DML in a file named `data.sql` to populate the database tables (3 hours)
  * [X] Write insert-from-select statements to move the data from the denormalized table to the normalized tables

### Data Layer Tasks (40 hours)
* [X] Create AppUserJdbcTemplateRepository and AppUserRepository Interface (1 hours)
  * [X] Create appropriate CRUD methods (2 hours)
    * Find a user by id
    * Find all users
    * Create/register user
    * Update user profile (info)
    * Delete/Disable user account
* [X] Create KnownGoodState in \test\java\wing-span\data (1 hours)
* [X] Create tests for AppUserJdbcTemplateRepository (2 hours)
* [X] Create BirdJdbcTemplateRepository and BirdRepository Interface (1 hours)
  * [X] Create appropriate CRUD methods (2 hours)
    * Find a bird by id
    * Find all birds
    * Add a bird
    * Update bird info
    * Delete/Disable a bird
* [X] Create tests for BirdJdbcTemplateRepository (2 hours)
* [x] Create SightingJdbcTemplateRepository and SightingRepository Interface (1 hours)
  * X ] Create appropriate CRUD methods (2 hours)
    * Find a sighting by id
    * Find all sightings
    * Add a sighting
    * Update sighting info
    * Delete/Disable a sighting
* [X] Create tests for SightingJdbcTemplateRepository (2 hours)
* [X] Create TraitJdbcTemplateRepository and TraitRepository Interface (1 hours)
  * [X] Create appropriate CRUD methods (2 hours)
    * Find a trait by id
    * Find all traits
    * Add a trait
    * Update trait info
    * Delete/Disable a trait
* [X] Create tests for TraitJdbcTemplateRepository (2 hours)
* [X] Create BadgeJdbcTemplateRepository and BadgeRepository Interface (1 hours)
  * [X] Create appropriate CRUD methods (2 hours)
    * Find a badge by id
    * Find all badges
    * Add a badge
    * Update badge info
    * Delete/Disable a badge
* [X] Create tests for BadgeJdbcTemplateRepository (2 hours)
* [X] Create AvatarJdbcTemplateRepository and AvatarRepository Interface (1 hours)
  * [X] Create appropriate CRUD methods (2 hours)
    * Find an avatar by id
    * Find all avatars
    * Add an avatar
    * Update avatar info
    * Delete/Disable an avatar
* [X] Create tests for AvatarJdbcTemplateRepository (2 hours)
* [X] Create AppUserMapper (1 hours)
* [X] Create BirdMapper (1 hours)
* [X] Create SightingMapper (1 hours)
* [X] Create TraitMapper (1 hours)
* [X] Create BadgeMapper (1  hours)
* [X] Create AvatarMapper (1 hours)

### Domain Layer Tasks (23 hours)
* [X] domain.SightingService (3 hours)
  * [X] implement CRUD methods (1 hours)
    * `findAll`
    * `findSightingsById`
    * `addSighting`
    * `editSighting`
    * `deleteSighting`
  * [X] Generate tests for domain.SightingService (2 hours)
    * Happy/ Unhappy Path 
* [X] domain.TraitService (3 hours)
  * [X] implement CRUD methods (1 hours)
    * `findTraitByBird`
    * `addTrait`
    * `editTrait`
    * `deleteTrait`
  * [X] Generate tests for domain.SightingService (2 hours)
    * Happy/ Unhappy Path
* [X] domain.BirdService (3 hours)
  * [X] implement CRUD methods (1 hours)
    * `addBird`
    * `editBird` (how can the admin edit a bird?)
    * `deleteBird`
  * [X] Generate tests for domain.SightingService (2 hours)
    * Happy/ Unhappy Path
* [X] domain.BadgeService (3 hours)
  * [X] implement CRUD methods (1 hours)
    * `findAllBadges`
    * `findBadgeById`
  * [X] Generate Tests for domain.BadgeService (2 hours)
    * Happy / Unhappy path
* [X] domain.AvatarService (3 hours)
  * [X] implement CRUD methods (1 hours)
    * `findAllAvatars`
    * `findAvatarById`
    * `addAvatar`
    * `editAvatar`
    * `deleteAvatar`
  * [X] Generate tests for domain.AvatarService (2 hours)
    * Happy / Unhappy path
* [X] domain.Validations (6 hours)
  * [X] Validate sightings (2 hours)
    * Sighting cannot be a duplicate
    * Date cannot be null
  * [X] Validate birds (2 hours)
  * [X] Validate traits (2 hours)
* [X] domain.ResultType (1 hours)
  * `Enum`
* [X] domain.Result (1 hours)
  * `Payload`
  *  `ArrayList<String> messages`

### Model and Controller Layer Tasks (21 hours)
#### In the `controller` package
* [X] ErrorResponse Class (1 hours)
* [X] Create GlobalExceptionHandler (1 hours)
* [X] Create AuthController (1.5 hours)
* [X] Create BirdController (1.5 hours)
* [X] Create SightingController (1.5 hours)
* [X] Create TraitController (1.5 hours)
* [X] Create BadgeController (1.5 hours)
* [X] Create AvatarController (1.5 hours)
#### In the `model` package
* [X] Create AppUser model (1 hours)
* [X] Create Bird model (1 hours)
* [X] Create Sighting model (1 hours)
* [X] Create Trait model (1 hours)
* [X] Create Badge model (1 hours)
* [X] Create Avatar model (1 hours)
#### In the `wing-span` package
* [X] Create App Class (1 hours)
* [X] Create AppConfig Class (1 hours)
#### In the `java` package
* [x] Create the resources package (.5 hours)
* [X] Create application properties (.5 hours)
* [X] Configure enviroment variables (.5 hours)
* [X] Double check all annotations (.5 hours)

### UI (Front End) Layer Tasks (22 hours)
#### Sightings
##### Part 1
* [x] Create a new React project with CRA (create-react-app) (1 hours)
  * [X] Remove the cruft
* [X] Add Bootstrap (or other CSS framework) to the `public/index.html` file (1 hours)
  * [X] Add a link to the Bootstrap CSS using the [CDN from the official docs](https://getbootstrap.com/docs/4.6/getting-started/introduction/#css) 
  * [X] Add the [`container` CSS class](https://getbootstrap.com/docs/4.6/layout/overview/#containers) to the `<div id="root"></div>` element
* [X] Create `Sightings` component (2 hours)
  * [X] Update `App` component to render `sightings`
* [X] Update `Sightings` to render list of sightings (2 hours)
  * [X] Use `fetch` to `GET` a list of sightings from the Wingspan API when the component is first loaded
  * [X] Write JSX to render the sightings array
  * [X] Stub out click event handlers ("Add Sighting", "Edit Sighting", "Delete Sighting") as necessary
##### Part 2
* [X] Create a form to add a sighting (2 hours)
  * [X] Add form JSX
  * [X] Decide between using individual state variables for input elements or a single object
  * [X] Add onChange event handlers to input elements
  * [X] Add onSubmit event handler to form element (be sure to prevent the form from submitting!)
  * [X] Create sighting object
  * [X] Use `fetch` to `POST` the new sighting's information to the wingspan API
  * [X] On success, update the sightings array (don't modify the original array!), or on failure, display any validation errors from the API in the UI
* [X] Support deleting sightings (2 hours)
  * [X] Confirm the deletion with the user/admin
  * [X] Use `fetch` to `DELETE` the sighting from the sighting API
  * [X] On success, update the sighting array (don't modify the original array!)
* [X] Conditionally render sections of the component (2 hours)
  * [X] Add state variable to track the current view
  * [X] Add conditional logic to the JSX to display the appropriate view
##### Part 3
* [X] Support editing sightings (2 hours)
  * [X] Store the "edit sighting ID" in a new state variable
  * [X] Retrieve the sighting to edit
  * [X] Update form state variable(s)
  * [X] Add form JSX
  * [X] Add onChange event handlers to input elements
  * [X] Add onSubmit event handler to form element (be sure to prevent the form from submitting!)
  * [X] Create sighting object
  * [X] Use `fetch` to `PUT` the updated sighting's information to the wingspan API
  * [X] On success, update the sightings array (don't modify the original array!), or on failure, display any validation errors from the API in the UI
* [X] Apply Bootstrap styling (as needed) (3 hours)
  * [X] Update the sightings list
  * [X] Update the add sighting form
  * [X] Update the edit sighting form
  * [X] Update the delete sighting confirmation
##### Part 4
* [X] Implement the required client-side routes (2 hours)
  * [X] Install `react-router-dom`
  * [X] Define the necessary client-side routes
  * [X] Stub out any components that are needed to support the client-side routes
  * [X] Display a "Not Found" message if a route doesn't match one of the defined routes
##### Part 5
* [X] Update the "Sightings" list component (2 hours)
  * [X] Link the "Add sighting" button to to the "Add sighting" route
  * [X] Link the "Edit Sighting" button to redirect the user to the appropriate route
* [x] Update the "Add Sighting" form component (1 hours)
  * [X] After a successful `POST` to the wingspan API, redirect the user to the "sightings" route
* [X] Update the "Edit Sighting" form component (1 hours)
  * [X] Use the `useParams` hook to get the sighting's ID from the route
  * [X] Use `fetch` to `GET` the sighting from the Field Agent API when the component is first loaded
  * [X] After a successful `PUT` to the Field Agent API, redirect the user to the "sightings" route

### Security Layer Tasks (24 Hours)
#### Security - Back End
* [X] Create Schema to add Users and Roles to database (1 hours)
  * [X] Add app_user, app_role, app_user_role and appropriate data to the schema.
* [X] Configure Spring Security (1 hours)
  * [X] Configure the project to utilize the spring-boot-starter-security dependency.
* [X] Create AppConfig (1 hours)
  * [X] Create the AppConfig class and define the PasswordEncoder bean. Define the WebMvcConfigurer bean to configure CORS globally.
* [X] Create AppUser (1 hours)
  * [X] Add the AppUser to the models package, implementing UserDetails and mapping to the created fields.
* [X] Create AppUserRepository (1 hours)
  * [X] Create the interface and repository to add AppUserRepository interface and AppUserJdbcTemplateRepository class.
* [X] Create AppUserService (1 hours)
  * [X] Create the AppUserService which implements org.springframework.security.core.userdetails.UserDetailsService
    * The UserDetailsService interface loads user-specific data.
    * The interface requires only one read-only method, which simplifies support for new data-access strategies.
* [X] Add JWT to project, creating JWT Converter and JWT Filter (1 hours)
  * [X] Continue the security implementation to include the JWT dependencies, and then create in the security package the JwtConverter and JwtRequestFilter classes
* [X] Create Security Config (1 hours)
  * [X] Create the SecurityConfig class connecting the above steps.
* [X] Create AuthController (1 hours)
  * [X] Create the AuthController in the controllers package to implement the /api/authenticate and /api/create_account endpoints.
  * [X] Update the security configuration to allow these endpoints to be accessed without credentials.
* [X] Configure AuthController, SecurityConfig, and AppUser Repo & Service to allow for creating an account (1 hours)
* [X] Configure AuthController, SecurityConfig, and AppUser Repo & Service to allow for editing an account (1 hours)
#### Security - Front End
* [X] Login Component (1 hours)
  * [X] Add a Login component and an accompanying /login route to your React project
  * [X] Prompt the user for their username and password
  * [X] Redirect the user to the "Home" page (i.e. /) after they submit the form
* [X] NavBar Component (1 hours)
  * [X] Add a NavBar component to your React project (if it's not already defined)
  * [X] Include links to the "Home", "Sightings List", "About", and "Contact" pages
  * [X] Within the component, define a user variable and initialize it to null
  * [X] If user is null, then display links to the "Login" and "Register" pages
  * [X] If user is not null, then display their username and a "Logout" button
 * [X] Global State and Props (1.5 hours)
  * [X] Add a global user state property to the App component
  * [X] Define login() and logout() functions that update the user state property
  * [X] Pass an auth object literal containing user, login, and logout to the Login and NavBar components
  * [X] Update the Login and NavBar components to call the login and logout methods (respectively)
* [X] Protecting Routes (1 hours)
  * [X] Use conditional rendering to protect all of the sighting related routes (/sightings, /soightings/add, /sightings/update/:id, and /sightings/delete/:id if defined):
 * [X] Context API (1.5 hours)
  * [X] Leverage the Context API to manage global state
  * [X] Create a context object in its own module (so it can be imported into any module that needs access to the global state)
  * [X] update the App component so that the context can provide its value to any component that needs access to the global state
    * Import AuthContext
    * Wrap Router in AuthContext.Provider
    * Set the AuthContext.Provider component's value property to the auth object
    * Remove auth props from all other components
    * Use the useContext Hook to listening for changes to the global state.
 * [X] Getting a Token (1 hours)
  * [X] Update the Login component to use the secured Solar Farm API to authenticate the user
  * [X] POST the username and password values to the API's /authenticate endpoint
    * On a successful response (200 OK), get the JWT token from the response body and pass it to the auth.login() method
    * Redirect the user to the default route (/)
    * On an unsuccessful response (403 Forbidden) display a "Login failed" message
 * [X] Parsing the Token (jwt-decode) (1 hours)
  * [X] Install the jwt-decode npm package:
    * npm install jwt-decode
  * [X] Use it to decode the token within the App component's login() function (1 hours)
    * You could decode and destructure like this: const { sub: username, roles } = jwt_decode(token);
 * [X] Passing the Token when Making HTTP Requests (1 hours)
  * [X] set the Authorization header on your Fetch calls
    * If you don't add the JWT token to the request, the server will return a response with a 403 Forbidden HTTP status code
    * Use `await` or `then` and handle the errors based on the response's `status`, `error` and `error.message`.
fetch("http://localhost:8080/api/solarpanels", init);
 * [X] Persisting the Login State (1 hours)
  * [X] Update the App component's login() function to persist the token to localStorage
  * [X] Update the App component's logout() function to remove the token from localStorage
* [X] Register Component (2 hours)
  * [X] Add a Register component and an accompanying /register route to your React project
    * The Register component is similar in form and function to the Login component
    * Need to make two Fetch calls when the user submits the form
      * Use Fetch to create the account
      * If you get a 201 (i.e. "Success") then use Fetch to authenticate and get the token
      * After receiving the token from the server, pass the token to the auth.login() function to login the newly created user
 * [X] Refresh the Token (1 hours)
  * [X] Automatically refresh the user's authentication token before it can expire

### AWS Hosting Tasks (24 Hours)
* [X] Research necessary AWS technology (12 hours)
  * [X] Read/view tutorials
  * [X] Create deployment plan
* [X] Connect DB to AWS RDS (3 hours)
* [X] Connect API to AWS Elastic Beanstalk (3 hours)
* [X] Connect React UI to AWS S3 (3 hours)
* [X] Configure Deployment (3 hours)

### Further Development (30 Hours)
* [ ] Create leaderboard page (8 hours)
  * [ ] Create necessary componenets, services, and configure routes
  * [ ] Create HTTP requests to support different queries
* [ ] Create stats page (8 hours)
  * [ ] Create necessary componenets, services, and configure routes
  * [ ] Create HTTP requests to support different queries
* [ ] Implement CRUD methods from back to front for an ADMIN UI that works for Badges, Birds, Traits and Avatars (24 hours)

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
