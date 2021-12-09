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
1. Frameworks and Drives

   **GUI**: 

   Add Review, ChooseAvatar, GUIAdminUser, GUIChooseLanguage, GUIEditContactInfo, GUIPlaylist, GUIProfile, GUIUserLogin, GUIUserRegister, MoviePage, RendererHelper, ReviewPage, SearchResult, Shared view (as a child cass of View), View,

   **Data access**: 
   
   WriteMovie, WriteReview, WriteUser, WritePic, ReadGUI

2. Interface Adapters

   **Controller**: 

   -NormalUserController: NormalController, NormalCCoin, NormalCUser, NormalCMovie, AdminInputProcessor

   **DataAccess Interface**: 

   WriteMovieInterface, WriteReviewInterface, WriteUserInterface
   
   **TextPresenter**

   **Gateway**
   
   **InstanceMain**
   
3. Use Case

   **Manager**: 

   UserManager, MovieManager, CoinManager, ReviewManager

   GatewayInterface

   MovieRanking

4. Entity

   **User**:  NormalUser, AdminUser

   **Review**

   **Movie**

For the logic of our program.
Before phase 2, we also followed this clean architecture, but there is not clear data flow between each layer and each class. However, in phase 2, we figured out a clear data flow diagram and got approval from our TA, which is the diagram shown below.

Following the Dependency Rule, source code dependencies point only toward higher-level policies (from Frameworks & Drivers to Entity).

According to this diagram, I add a gateway class as a middle man class, it processes I/O of entities and file data. In order to stick with Clean architecture, I create two new interfaces to gateway and data access class, one is called GatewayInterface (Use Case Layer), the other one is called DataAccessInterface (Interface Adapter Layer),  so that according to the dependency inversion principle / dependency injection, the program in some way, can points from “inside” to “outside” without violating the Clean architecture.

Specifically, when a user wants to create or change the entity, The GUI will send the request of the user to the controller, controller classes will call corresponding Manager classes (Use case) to change their entity. Then, the Manager classes will call the gateway class through the gateway interface to use the corresponding data access class to edit or create files.

In the other direction, The program will read data files and call a gateway to use corresponding Manager classes to create entities and store them in Manager classes automatically when the program is about to run. 

### SOLID

#### Single responsibility:
During phase 0 there were only 2 controller classes which were responsible for all the actions done by user/admin. In phase 1, these 2 classes were separated into different controller classes, and each of them had only one responsibility.
In GUI, one class is only responsible for displaying one frame, which follows the single responsibility. Also, a presenter layer is added which is responsible for sending visual information to GUI, and this was originally done by GUI during phase 1, and we have separated this responsibility in phase 2. The single responsibility makes the code have a better structure, and better follow the clean architecture structure.

#### Open-closed principle:
Entities (classes, methods, etc.) are open for extension, but closed for modification, their behaviors can be extended without modifying the source code.
GUI follows the open-closed principle. For example, if a new frame is to be added, we only need a new class. If we want to add a new button that generates the next window, these methods could be set in the super class View, and there is no need to modify other existing classes.
At the beginning of phase 2,since we needed to reorganize the logic of our program, we modified most of the classes in our program, which violated the Open-closed principle. However, after the change of program data flow is complete, during the process of development of new features, we follow the Open-closed principle.

#### Liskov substitution:
User is the parent class of NormalUser and Admin User. All features in User are passed to its child class, and we can replace User with either NormalUser or AdminUser and the program should not disrupt the behavior of our program.
In addition, a class called NormalController acts as the parent class of other controllers for movie, coin, user and review. These controllers each have their own type of object that they are in charge of but they all have similar features that their parent class, NormalController. Therefore, every subclass can be used instead of NormalController at places where NormalController is called.
In GUI, View is the super class of all other classes. It can be replaced with other subclasses, which are mostly responsible for generating one page, and the behavior of our program is not going to be disrupted. Also, SharedView is a superclass of GUIUserLogin and GUIUserRegister, these classes also follow the Liskov substitution principle.

#### Interface segregation:
All of our interfaces are small and specific now, so Interface segregation principle holds. We used to have a large UI and controllers in phase 0 which might have violated the principle, so we divide them into more small and specific classes now in phase 1 using some design patterns.
In phase 2, the extensions to existing classes and newly added classes all follow the interface segregation principle. For instance, we added two new use case classes, ReviewSort and MovieRanking, to rank reviews and movies accordingly, instead of adding these features into the existing controller classes.

#### Dependency inversion:
Generally, dependency inversion works in our program since the high level modules do not depend on low level modules. As mentioned above in clean architecture, we created interfaces, including GatewayInterface and WriteFileInterfase, in order not to stick with the Dependency inversion. Thus, the structure of our program is clean and tidy, which makes our program easy to test and maintain.
For details about the data flow of our program, please refer to the Clean architecture section of the design document.

#### Packaging
We choose to package our classes according to the Clean Architecture layers, as it helped us to better follow Clean Architecture.

Phase 1:

The project is packaged by layers. Four layers: Core, Use Cases, Interface Adapter and Framework are used to name the four packages that contain the body of our codes. For each layer, lower level packages are contained and classes at the same layer are separated by their functionaries. Core contains three entities: User (which is also a package containing NormalUser and AdminUser), Movie and Review. UseCases contain Manager classes. Manager package contains four managers for movie, user, review and coin. The next layer package is InterfaceAdapter, which contains controllers package for movie, review, user and coin, inputprocessor and instancemain; and also WriteFile package contains WriteMovie, WriteUser, WriteReview and a WriteFile class. The outermost layer is the Framework, which contains UI and GUI, two packages. However, some errors still occur due to the fact that we are unable to import and use every class across packages correctly.

Phase2:

The four layers from phase 1 remained the same while some sub-packages became more reasonable. First, Entity layers had no change from the last design. Use cases now have all the managers in it and also the Gateway Interface, which calls WiteFile classes to create or edit files. Interface Adapter now has all the Controllers, Interface-Adapter layer Interfaces and also the Presenters. The most significant change to Interface Adapter is the addition of a new Gateway class, which links Framework and Use Case. Framework now has three different sub-packages: GUI, UI and DataAccess, where DataAccess contains the WriteFile classes. 

###