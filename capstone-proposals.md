# Capstone Proposal

## 1. Problem Statement

### Problem

> Currently, there is no wold-wide competitive birding organization. Stepping in to fill the void is Wing\<Span/>. Wing\<Span/> is the first competitive birding app that connects users across the world. Not only does Wing\<Span/> track your sightings, it also allows you to view other user's sightings, and collect badges for your sightings too!
> 
> Having a "Life List" is great, but it's also nice to see how you stack up to other birders across the globe! Plus, this provides valuable insights into which birds are likely to be seen in certain locations on various dates.

## 2. Technical Solution

Briefly describe a technical solution to your problem with a couple concrete scenarios.

### Solution

> Create an application for posting bird sightings. Making it easy to track your progress and compare yourself to other users.
> 
> ### Scenario 1
> Jane has been a passive bird watcher for a while now, but she's been looking for a way to track the birds she's seen. She also wonders how her sightings compare to other bird watchers - not just in her area, but the world over. She finds Wing\<Span/>, signs up for an account, and starts posting sightings. She gets her badge for posting 5 sightings and now she's hooked. Within a month, Jane has earned her 20 sightings badge. It's helping her get outdoors more and she's learned a lot about birds in the process.
> 
> ### Scenario 2
> John has been an avid bird watcher all of his life. He has a journal that he keeps with all of his sightings. John has been looking for a more convenient and permanent way to log his sightings. Something that might also offer easier insight into his bird watching stats. John discovers Wing\<Span/> from a friend. He uploads all his sightings from his journal and realizes he's earned a lot of badges. He's excited to find out that he has more badges than the friend that told him about the app in the first place. This motivates John to plan specific trips so he can log more sightings and get more badges.

## 3. Glossary

### Terms

> ### Bird
> An actual bird with traits.
> ### Trait
> A bird trait.
> ### Sighting
> A sighting of a particular bird on a date at a location.
> ### Bird-Watcher (User)
> Anyone who watches birds and records their sightings.
> ### Badge
> An award based on meeting certain sighting goals.
> ### Avatar
> A profile image for the user.
> ### Admin
> Someone who maintains the bird sightings log.

## 4. High Level Requirement

Briefly describe what each user role/authority can do. (These are user stories.)

### Example

> - Create a sightings (MEMBER, ADMIN).
> - Edit a sighting (MEMBER, ADMIN).
> - Delete a sighting attached to their profile (MEMBER).
> - Delete any sighting (ADMIN).
> - Browse sightings (anyone).
> - Create a bird (ADMIN).
> - Edit a bird (ADMIN).
> - Delete a bird (ADMIN).
> - Browse birds (anyone).
> - Earn badges (MEMBER).
> - Edit badges (ADMIN).
> - Delete badges (ADMIN).
> - Browse badges (anyone).
> - Create an avatar (ADMIN).
> - Edit an avatar (ADMIN).
> - Delete an avatar (ADMIN).
> - Browse and use an avatar (MEMBER, ADMIN).

## 5. User Stories/Scenarios

Elaborate use stories.

### Example

> ### Create a Sighting
> 
> Log a sighting to a user's profile.
> 
> Suggested data:
> - bird id (to connect the sighting to the bird) from a list of available birds
> - user id (to connect the sighting to the MEMBER)
> - date (which would need to be in the past or at the current moment)
> 
> **Precondition**: User must be logged in with the MEMBER or ADMIN role.
> 
> **Post-condition**: None
> 
> ### Edit a Sighting
> 
> Can only edit a sighting in the past.
> 
> **Precondition**: User must be logged in with the MEMBER or ADMIN role. A MEMBER can only edit sightings attached to their profile. An ADMIN can edit any and all sightings. Sighting datetime must be in the past.
> 
> **Post-condition**: None
> 
> ### Delete a Sighting
> 
> Can only delete sightings in the past.
> 
> **Precondition**: User must be logged in with the MEMBER of ADMIN role. A MEMBER can only delete sightings attached to their profile. An ADMIN can delete any and all sightings. Sighting datetime must be in the past. 
> 
> **Post-condition**: Data is deleted.
> 
> ### Browse Sightings
> 
> - Text-based: Users see a list of sightings ordered by date. If a user wants to learn more about a bird, they can click on the bird and be taken to the birds specific page with traits. Display results as HTML with action UI to sign up.
> 
> **Precondition**: Anyone can view the list of sightings. They must be a MEMBER or ADMIN to contribute to the list of sightings in any way.
> 
> **Post-condition**: None
> 
> ### Create a Bird
> 
> Add a bird to our list of available birds to sight.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is added to our database. The bird becomes available to view by anyone. The bird can be used in a MEMBER sighting.
> 
> ### Edit a Bird
> 
> Edit any bird in our list of birds.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is updated to our database.
> 
> ### Delete a Bird
> 
> Delete any bird in our list of birds.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is deleted from our database.
> 
> ### Browse Birds
> 
> View a list of all birds and their traits.
> 
> **Precondition**: Anyone can view our list of birds.
> 
> **Post-condition**: None.
> 
> ### Create a Badge
> 
> Add a badge to our available badges for users to earn.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is added to our database. The badge can be earned by a MEMBER if they meet the criteria of sightings.
> 
> ### Edit a Badge
> 
> Edit any badge in our list of badges.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is updated to our database.
> 
> ### Delete a Badge
> 
> Delete any badge in our list of badges.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is deleted from our database.
> 
> ### Browse Badges
> 
> View a list of all badges a MEMBER can earn.
> 
> **Precondition**: Anyone can view our list of birds.
> 
> **Post-condition**: None.
> 
> ### Create a Avatar
> 
> Add an avatar to our list of available avatars for a MEMBER to use for their profile.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is added to our database. The avatar becomes available to use in by MEMBER.
> 
> ### Edit a Avatar
> 
> Edit any avatar in our list of avatars.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is updated to our database.
> 
> ### Delete a Avatar
> 
> Delete any avatar in our list of avatar.
> 
> **Precondition**: User must be logged in with the ADMIN role.
> 
> **Post-condition**: Data is deleted from our database.
> 
> ### Browse and choose and Avatar
> 
> View a list of all avatars available to use by a MEMBER.
> 
> **Precondition**: User must be logged in with the MEMBER or ADMIN role.
> 
> **Post-condition**: None.
