# Text based social media

## Description 
The goal of this project is to create a text-based social media API that allows users to register, login, post text, comment on posts, follow other users, and view different types of posts and comments. The API will be used by a front-end application, such as a single-page app. The API is expected to have up to 500 active users and will have two types of users: Free and Premium. During registration, users can select their plan type, and the username will be their email address.


## Requirements

### 1. User Login

● Implement a login system to authenticate users (the users can be pre-existing in the database with a small sql script).
### 2. Posts and Comments

● Users can post small text with a limit of 1000 characters.

● Users can comment at most 10 times per post.

● All responses will be flat under the original post (no replies or thread chaining).

● Store the posts and comments in the database.
### 3. Following and followers
● Store follower relationships: Maintain a list of followers for each user.

● Add and remove followers (by their email).
### 4. Views
● View all original posts by the people they follow, ordered by reverse
chronological order.

● View their own posts, including the latest 100 comments, sorted by reverse chronological order.

● View the latest comments on all posts they have posted or any posts by the people they follow.

● Retrieve followers and following: View the list of followers for a user (their username is enough).

## Tech Stack

### Back-End:

1) Programming Language: Java
2) Framework: Spring Boot
3) Authentication: Spring Security


### Database:

1) RDBMS: PostgreSQL
2) ORM: Hibernate

### Build Tools:

1) Build Tool: Maven

### Hosting/Deployment:

1) Server: Apache Tomcat
### Frameworks
1) Spring Web
2) Starter Data JPA

## Code Explanation

### 1) application.properties: 
Used for setting up a database connection (with PostgreSQL) and configuring JPA properties.
### 2) SecurityConfig
Configures authorization rules, allowing unrestricted access to /register and /login endpoints and requiring authentication for all other requests. Sets the session management policy to stateless, which is typical for JWT-based authentication as it doesn't require server-side session storage. The authenticationProvider method creates a DaoAuthenticationProvider bean. This provider uses the UserDetailsService to load user details and the PasswordEncoder for password comparison. The authenticationManager method provides an AuthenticationManager bean, which is a core interface in Spring Security's authentication architecture.
### 3) UserInfoUserDetails
UserInfoUserDetails implements UserDetails, making it a custom user entity for Spring Security. This class is used to encapsulate the user information which is later used by Spring Security during the authentication and authorization process. UserInfoUserDetails is used by Spring Security during the authentication process to load user-specific data. 
### 3) UserInfoUserDetailsService
The UserInfoUserDetailsService class is an implementation of Spring Security's UserDetailsService interface. It serves as a bridge between the application's user data (as represented by the Account entity and AccountRepository) and Spring Security's authentication mechanism.
### 4) Controllers
The application has five controllers. The first controller is about registering a new user. The second controller is about to login the user. The third controller is the PostController. This controller implements the following requirements:

⦁	 View all original posts by the people they follow, ordered by reverse
chronological order. This implemented by the call ("/feed").

⦁	View their own posts, including the latest 100 comments, sorted by reverse chronological order. This implemented by the call ("/own").

⦁	View the latest comments on all posts they have posted or any posts by the people they follow. This implemented by the call ("/relevant-posts").
⦁	The user can post a new post. This implemented by the call ("/posts/new").
The fourth controller is about commenting on posts. The fifth controller is about the users can follow other users or to view the followers.
### 5) Entities
Four entities corresponds to four tables in the database. The account  entity corresponds to users table, the Post entity corresponds to posts table, the Comment entity to comments table and Follow entity to follows table.
### 6) Repositories
#### AccountRepository
Purpose: Manages user accounts.

Key Functionalities: Retrieve an account by email and password.
Find an account using just the email.
#### CommentRepository
Purpose: Handles operations related to comments.

Key Functionalities:
Fetch latest comments for multiple posts.
Retrieve comments by post ID and user email.
Get latest comments for a single post with pagination.
#### FollowRepository
Purpose: Manages following relationships between users.

Key Functionalities:
List followers for a user.
Find follow relationships by follower or following email.
Check existence of a follow relationship.
Retrieve a follow relationship by follower and following email.
#### PostRepository
Purpose: Manages user posts.

Key Functionalities:
List all posts by a user.
Fetch posts from followed users.
Get all posts by a user in descending order of creation.
#### ViewFollowingPostsRepository
Purpose: Facilitates viewing posts from followed users.

Key Functionalities:
Find posts by users followed by the current user.
### Services
1. AccountService
Purpose: Manages user account operations.
Key Functionalities:
Encrypt and save user account information.
Retrieve an account by email and password.
2. CommentService
Purpose: Handles comment-related operations.
Key Functionalities:
Create a new comment while enforcing a maximum comment limit per post.
3. FollowService
Purpose: Manages following relationships between users.
Key Functionalities:
Follow a new user while checking for existing follow relationships.
Retrieve a list of followers for a specific user.
Unfollow a user with validation for existing follow relationships.
4. JwtService (Component)
Purpose: Handles JWT (JSON Web Token) operations for authentication.
Key Functionalities:
Extract username and expiration date from a token.
Validate tokens against user details.
Generate new tokens for users.
5. PostService
Purpose: Manages operations related to user posts.
Key Functionalities:
Retrieve, create, and delete posts.
Get posts by followed users.
Fetch own posts along with their comments.
Retrieve relevant posts with comments, including both own posts and those of followed users.

## Usage

### CommentController
Endpoint: /comments

Methods:
POST /comments: Create a new comment.

Request: JSON payload with comment details.

Response: JSON of created comment or bad request on error.
### FollowController
Endpoints:
POST /follow: Follow a user.

Request: JSON with follower and following user details.

Response: JSON of follow relationship or bad request on error.

GET /find_followers: Get followers of a user.

Parameters: userEmail (as query param).

Response: List of followers (FollowerDTO) in JSON.

POST /unfollow: Unfollow a user.

Request: JSON with follower and following user details.

Response: OK status or bad request on error.

### LoginController
Endpoint: /login

Methods:

POST /login: Authenticate user and get a token.

Request: JSON with email and password.

Response: JWT token or unauthorized error message.

### PostController
POST /posts/new: Create a new post.

Request: JSON with post details.

Response: JSON of created post.

GET /feed: Get feed for the current user.

Parameters: userEmail (as query param).

Response: List of posts in JSON.

GET /own: Get own posts and their 100 latest comments.

Parameters: userEmail (as query param).

Response: List of posts with comments in JSON.

GET /relevant-comments: Get posts with the latest comments.

Parameters: userEmail (as query param).

Response: List of posts with comments in JSON.
### RegisterController
Methods:

POST /register: Register a new user.

Request: JSON with account details.

Response: Confirmation message in JSON.
