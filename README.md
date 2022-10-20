# Capstone Project: Wing\<Span/>

## User Story
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


### Database Layer Tasks

### Data Layer Tasks

### Domain Layer Tasks

### MVC Layer Tasks

### UI (Front End) Layer Tasks

### Security Layer Tasks

### AWS Hosting Tasks

### Further Development

## Schedule

## Class Diagram

## (?) UI Diagram (?)

## (?) Hosting Diagram (?)

## Approach
* Planning is absolutely essential for a project this large.
    * Create a complete list of concrete tasks required to finish
    * Tasks should take no longer than 4 hours. Schedule each task for a specific day.
    * At the end of each day, take stock. Are you ahead of schedule, on schedule, or behind?
        * If ahead, how can you put the extra time to use?
        * If behind, what do you have to adjust to complete the project? Don't just hope that things will improve. Take concrete steps to simplify or remove features.
* Ask questions. Even though you control the specification, your classmates and instructors are invaluable resources.
    * Ask clarifying questions. Don't make assumptions when things aren't clear.
* Test as you go.
