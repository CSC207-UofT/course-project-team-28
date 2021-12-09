## Design document


### Specification

Create a program to store contents of movies, including names and links of those movies. We also provide a platform for users to write comments for each movie and manage their own playlists.

At the beginning of the program, the user can choose languages depending on their preference. This language-flexible function is achieved by using the text presenter. By using the presenter, we can change the language only in the presenter instead of changing every text in our GUI-related classes.

After choosing the language, there is a main page consisting of four buttons, login and register for both AdminUser and NormalUser. The background of this page is our logo, with a gif inserted.

For any administrators admitted by designers, they are able to register and login via a special code. After that, they can upload movies on the platform.

For a normal user, she/he can log in or create a new account on our platform. On their own user page, on the left side, they can see their own information, and they can also edit their contact information and description on this page, which could be updated dynamically. Moreover, they can check their own playlist. On the right side, they can search movies and see the recommendations of movies.

The user can search for a movie. If they type in the wrong movie name, the platform will remind them of typing again. In addition, the associative-word search is used in our search function. For example, if the user types in “a”, the associative-word search is applied and all movies whose names contain “a” will appear on the search result page. Then, the user can click one movie, then the window is switched to the page of this movie. This page shows the name, link, number of likes and category of movies. On this page, users are able to add the movie to their list by liking it. Besides, users will receive a coin after writing one review and they can also check comments from other users for this movie. Then, they can click a certain review if they have interest, then give coins to this review’s writer. The number of coins can be updated dynamically.

We have completed the complete GUI interface in phase 2, all features for GUI are added.


### Major design decisions

phase 1:
1. Added of coin feature
   The user can receive a coin when she/he writes a review, and can give coin to the reviews she/he likes. We hope this coin mechanism can increase the quality and quantity of reviews in our application. Ideally the number of coins that a review receives increases with the quality of the review, which may incentivize the users to write reviews of higher quality. In addition, we hope the users will write more reviews in order to earn coins.

2. Added GUI
   We designed some GUI views to complete our scenarios. This makes our program one step closer to the complete program we had anticipated at the beginning of the project. For example, the switching between user profile page, playlist, and category is presented nicely to users with the swing implementation. We also have some plans for the future. We hope there will be a search interface shown after clicking the button on the profile page, where users could search the movie name. After searching the correct movie name, there will be a page for the movie, showing the movie name, the likes received and all reviews for this movie. Users could give coins to the reviews they have interest in.

3. Separate long class into several smaller classes
   The class NormalInputProcessor in phase 0 is splitted to 4 classes. With NormalController being the parent class, and NormalCCoin, NormalCMovie, and NormalCUser as the child class. The change was made to comply with the single responsibility principle of SOLID. And now each of the three different subclasses is responsible for a smaller group of methods that are more closely related to each other compared to the single long NormalInputProcessor class in phase 0.

4. Moved WriteFile class to the Interface Adapter layer and create the gateway classes.

5. Create a new class, InstanceMain, on Interface Adapter layer
   InstanceMain declares all the instances of the controller, gateway and use case class that will be needed when the program runs, so that UI and GUI can use the instance in the InstanceMain class without declaring the new instance, it prevents some unknown bugs.

phase2:

1. Added movie ranking
   Our platform now provides movie recommendations according to movie ranking, which appears on the main page after users login. In the ranking, movies are listed according to the number of likes they received; the first movie appearing on the list receives the largest number of likes.

2. Updated GUI
   Our GUI in phase 1 only contains the login page and the user profile page. In phase 2, we continued to add more features to GUI such that it can demonstrate most parts of our program to the user. For instance, we added the movie profile page, search bar, This makes our app more graphical. In addition, it is more convenient to use than the command line interface. Previously, the users needed to type the instructions word by word (and instructions with typos are not recognized by our program), now in GUI, they only need to press the button. We hope the implementation of our GUI will attract more users to our app.
profile picture, contact information, description and playlist to users’ profile page.


3. Movie category and Associative-word Search
   We decided to organize the movies into categories, such as Action, Horror, Comedy, etc.. Using categories, the users can find the movie that they are interested in based on the category (general type) of movie. We hope this will help the users to find the movie they like more easily.

4. Associative-word search
   We decided to introduce an advanced search feature to help the user get the result they want more conveniently. With the updated search, after typing in some strings and click search, the platform will provide users with some movies whose movie names are related to the typed-in strings. Similar to the movie category, we hope the users can access more movies that they like.

5. Presenters
   In order to support multiple languages, presenters are designed for GUI so that the displayed text can be changed easily. Currently we support two languages, which are English and Chinese.

6. Storage of Reviews
   In the previous version, we stored two sets of data for each review, where one set stores each review individually, and the other set stores reviews that are organized by the movie that they were written for. This implementation is costly. Besides taking up more memory space, this increases runtime as each review entity has to be initiated from the database twice or each review has to be stored in two separate databases. In phase 2, our program can now store only one set of data and still keep all of its original functions.

7. Move the WriteFile classes to Framework & Drivers layer
   In order to stick with Clean Architecture, in phase 2, we moved the WriteFile classes to the Framework & Drivers layer after knowing that it should be put in that layer. We also adjusted the Use Case and Controller classes to accommodate for this change. 


### Clean architecture

To follow clean architecture, a new class InstanceMain is added in the Interface Adapter layer, and the outermost layer GUI can only interact with it.
1. As the lowest level, our Frameworks and Drivers consist of two main parts, UI and GUI for Phase 1. (In Phase 2, UI will be replaced with GUI completely.)

   **GUI**: Add Review, ChooseAvatar, GUIAdminUser, GUIChooseLanguage, GUIEditContactInfo, GUIPlaylist, GUIProfile, GUIUserLogin, GUIUserRegister, MoviePage, RendererHelper, ReviewPage, SearchResult, Shared view (as a child cass of View), View,

   **Data access**: WriteMovie, WriteReview, WriteUser, WritePic, ReadGUI

2. The second lowest level, Interface Adapters include Controller and Gateway-related classes.

   **Controller**: NormalController, NormalCCoin, NormalCUser, NormalCMovie, AdminInputProcessor

   **Gateway**: Framework.DataAccess.WriteFile, Framework.DataAccess.WriteUser, Framework.DataAccess.WriteReview, WriteMoive

   **Interface Adapter**: InstanceMain
3. At a higher level, Use Case consists of our Manager classes. Following the Dependency Rule, Use Case knows nothing about UI and GUI.

   **Manager**: UseCase.UserManager, UseCase.MovieManager, UseCase.CoinManager, UseCase.ReviewManager
4. The highest level is Entity, which can be manipulated by Use Case.

   **Entity**: Entity.User.User, Entity.Review, Entity.Movie

Following the Dependency Rule, source code dependencies point only toward higher-level policies (from Frameworks & Drivers to Entity). And the name of functions, classes and variables declared in lower level must not be mentioned by the code in higher level.


To follow clean architecture, a new class InstanceMain is added in the Interface Adapter layer, and the outermost layer GUI can only interact with it.
Frameworks and Drivers
GUI: Add Review, ChooseAvatar, GUIAdminUser, GUIChooseLanguage, GUIEditContactInfo, GUIPlaylist, GUIProfile, GUIUserLogin, GUIUserRegister, MoviePage, RendererHelper, ReviewPage, SearchResult, Shared view (as a child cass of View), View,
DataAccess: WriteMovie, WriteReview, WriteUser, WritePic, ReadGUI
Interface Adapters
Controller:
- NormalUserController: NormalController, NormalCCoin, NormalCUser, NormalCMovie
- AdminInputProcessor
  DataAccess Interface: WriteMovieInterface, WriteReviewInterface, WriteUserInterface
  TextPresenter
  Gateway
  InstanceMain
  Use Case
  Manager: UserManager, MovieManager, CoinManager, ReviewManager
  GatewayInterface
  MovieRanking
  Entity
  User: NormalUser, AdminUser
  Review
  Movie
