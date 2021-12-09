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

   **GatewayInterface**

   **MovieRanking**

   **ReviewSort**

5. Entity

   **User**:  NormalUser, AdminUser

   **Review**

   **Movie**

For the logic of our program.
Before phase 2, we also followed this clean architecture, but there is not clear data flow between each layer and each class. However, in phase 2, we figured out a clear data flow diagram and got approval from our TA, which is the diagram shown below.

![Clean Architecture](https://github.com/CSC207-UofT/course-project-team-28/blob/Joanna/phase0/CRCCards/CA.png)


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

### Design Pattern
####State machine design pattern:
GUI using state machine design pattern. It is a behavioral design pattern that allows an object to change the behavior when its internal state changes. When the user selects if the user wants to login as normal user or admin user, an object isAdmin is changed and will affect how the next page is shown. One problem during phase 0 and phase 1 was that UI had too many if-else statements, and the state machine design pattern allows the GUI to remain clear without them.

####Singleton design pattern:
Our Program will read data from files and store their entities in the corresponding Manager classes (use case) when the program is about to run, and the entities stored in the Manager may be changed during the process of Program. These all kinds of changes will apply to the only one instance of Manager classes, if somewhere in the program creates the new instance of Manager class, this new Manager class will not have any entities stored inside, which will have bugs. In other words, the change applied on one of the instances is not a real-time change.
In order to solve this program, I use the Singleton design pattern, which allows the program to have only one instance of my Manager classes, and provides a global point of access to it.
This pattern will need to create a new class (which is the InstanceMain class in our program), and store the instance of classes I need inside as static variables, so that if some classes want to interact with other classes, they only need to use the getter method and get the instance I store in the InstanceMain class without creating new instance
For convenience, I also store the instances of controller and data access classes in the InstanceMain since it is not necessary to frequently create their instances.

I did not use the design pattern for our three data access class/ WriteFile class, they seem like they are related, but our implementations are almost different, using some design pattern may make it complicated, so it is not necessary to use the design pattern.

### Progress Report
Question:Is there a better way to design GUI other than swing?

Solution: GUI is still using Swing during phase 2. We have studied JavaFX but an API must be manually installed in IntelliJ, which could be a potential issue since all members and TA have to install it as well.

Swing is not “thread safe”.
Solution: By using the built-in method “pack”, thread is protected. Also, our program is a single-thread program, which makes this question less important.

After putting classes in packages, errors occurred as some classes cannot be called due to the fact that they are not in the same package, but importing them would change their type. For example, Movie type now changed to Core.Movie type. Is there any way to avoid these errors?
Solution: Solved, after importing all the classes needed for each class, the types become acceptable automatically.

To save data from Hashmap and keep its structure, we used properties for WriteMovie, however, when running, a line of “ava.io.FileOutputStream@dc24521Check your review” appeared. Though it does not affect the code running, it is annoying. Is there any way to delete that?
Solution: Solved, property is no longer needed.

When adding coins to review, we can’t update the number of coins on the user's page dynamically.. We can only update the number of coins after a user re-login to their page. The update of coin number is dynamic to files but GUI can’t really show it as soon as a coin is given out.

The changing of language would sometimes cause NullPointerExceptions.
Solution: Surrounded with try catch and provided another way to avoid the exception.

Question: Which layer should the WriteFile classes belong to?

Solution: After We talked to TA, we decided to put the WriteFile classes to the Frameworks & drivers layer, which became data access classes. Then, we created a class called gateway as the middle-man in the interface Adapter layer, the details have been explained in the clean architecture section.


What has worked well:

We have updated all functions to the program, which supports users to give coins to reviews, and users will be rewarded after giving comments to movies.
Interaction with GUI to create/read userfile to register/login
Users can edit stuff on their user profile and GUI is able to update new info dynamically.
We have a fantastic associative-word searching system which allows users to search their desired movie with even ONLY ONE LETTER!
Our GUI has beautiful and well-organized pages with many features. The pages follow each other in a way which makes the features very reasonable to be shown step by step. The buttons and pictures on each page are all beautifully set and these together composed pages very comfortable for users to access. The design strictly followed SOLID principle and accessibility rules. We have also added two languages available, which is also easy to expand.
Our project is clean and minimizes the number of warnings as much as possible. We have tests covering most of the classes except for GUI, which basically tests itself while the program is running. We have JavaDoc on every method and class we have in our project, which makes whoever reads our code much easier to understand them.

###Group work in Phase 2:

**Fan Pan (Joanna)** worked on the overall GUI creation, page design, structure, page switching and writing design documents, modifying databases, and fixed code style and warnings.

**Yuxuan Li** worked on updating a controller class AdminInputProcessor and a new use case class CoinManager, also working on implementing a use case called MovieRanking. In addition, Yuxuan also worked on writing related tests.

**Jacquelyn Wang** updated the MovieManager, ReviewManager, NormalController, NormalCCoin, NormalCUser, NormalCMovie for the altered file structure and updated methods to corporate with newly added GUI. Also, Jacquelyn implemented the ReviewSort class to support display of reviews in the decreasing order of the number of coins, and all the test cases for these classes.

**Jiaxi Li** worked on GUI design, fixing and updating some features in Review and Write Review class, and completed tests for Review and WriteReview Class. Also fixed code style and warnings in these classes.

**Jing Pan (Ella)** worked on three WriteFile Classes, changing the layer of WriteFile Classes from Interface Adapter Layer to Framework & drivers layer.  Reorganized the logic/data flow of the program, making it strictly follow the Clean architecture, used Singleton design Pattern to create a static class called InstanceMain.
Updated the features of the coin system in User Classes and UserManager. Updated the feature of user profile photo and category in user entity.
Refactored all the naming problems in the program, written tests for WriteUser, gateway, instanceMain, UserManager, NormalUser and AdminUser.

**Andy Han** Worked on designing packages, refactoring some of the variable names and file names. Added a new variable, Category, to the movie entity and sorted movie files into folders named with categories. Made changes to Movie and WriteMovie classes to make them work with the new folder setting of movie. Created new class WritePic for GUI to get picture path from file. Created new class ReadGUI for GUI to get path of language folder. Created presenter class, which makes the GUI able to change language. Worked together with Joanna and Jiaxi to create other classes and added other features to GUI, UserPage, SearchResult, MoviePage, Adding Review Page and changing language, for example.

**Haitao Qiu** worked mostly on developing a more advanced search feature, which can suggest and auto-complete the movie name based on the input user entered and the movie names in the database, using Trie data structure which is self-learnt by myself. Wrote the tests for search and moviemanager, and fixed some bugs, code style and warnings.

### Significant Pull Request
Notice: Due to our group's own development workflow, in order not to mess up the main branch, we create a new branch called TestBranch, which is used to test the newest version code. Our pull requests are all sent from our own branch to the TestBranch. Some teammates might work together and merge all of their code into one person's branch, and then send a pull request to the TestBranch. Thus, some of them may share the same link of one pull request.

**Jing Pan**: https://github.com/CSC207-UofT/course-project-team-28/pull/5
This is the earliest pull request in our group in phase 2. In this pull request, I reorganized the logic / data flow of our program, created four new Interfaces, one is for gateway class and the other three are for data access classes. I also changed all the corresponding places in all classes about which class and method they should call due to the new data flow of the program. All the works of phase 2 are all based on this new organized program.

**Jacquelyn Wang**: https://github.com/CSC207-UofT/course-project-team-28/pull/10
In this pull request, I added the ReviewSort class, so that the review can be displayed according to the number of coins. In addition, the information stored in the entity was relayed to the user interface in the form as String, since it was needed by the command line interface. But for GUI, we want to display, for example, each part (i.e. review content, reviewer, number of coins) of a review separately. Thus, the controller needs to relay them in the form of Arrays of Strings, which is changed in this pull request. In addition, I also helped to fix bugs, including the username of the logged-in user is not properly stored (which causes bugs in GUI).

**Yuxuan Li**: https://github.com/CSC207-UofT/course-project-team-28/pull/13
In the pull request, I added the latest version of MovieRanking and CoinManager classes; also, related test files are updated in it. A lot bugs and errors are fixed in that version, and some incorrect names of methods and variables are fixed as well. After that pull request, related functions of displaying movie recommendations are able to work. In addition, the class descriptions and method descriptions are added in it.

**Fan Pan**: https://github.com/CSC207-UofT/course-project-team-28/pull/14
This pull request was created during our last meeting. This pull request was important because each of the group members had their own modification and updates to their own part, and these changes were merged together and checked for any errors. Also, as a version that was very close to the due date, this pull request included style fixing such as Javadoc and warning fixing for GUI.

**Jiaxi Li**: https://github.com/CSC207-UofT/course-project-team-28/pull/14
In this pull request, I added some GUI-related classes and fixed all bugs related to Review and WriteReview class. I also uploaded my test classes. This pull request is important because sometimes tests are all passed in my branches but fail in others. If there is a pull request, I can receive feedback from other members in time. Since many classes are interacted with each other, with the pull request, if I fix one problem, all other problems caused by this problem can be fixed in time.

**Andy Han**: https://github.com/CSC207-UofT/course-project-team-28/pull/4
In this pull request, I finished the first edition of our package. I divided the files into four main folders: Entity, Use Case, InterfaceAdapter and Framework. Some sub-folders were also added. I also fixed all the bugs that were caused by the refractors of the files to different folders. This is done at the beginning of phase 2, which is important because this version is what our group is adding things on in phase 2. Fixing package bugs would be even more complex about more classes and features are added to our program.

**Haitao Qiu**: https://github.com/CSC207-UofT/course-project-team-28/pull/9
In this pull request, I added the first version of search feature, a more advanced search feature, which can suggest and auto-complete the movie name based on the input user entered and the movie names in the database, using Trie data structure, which is a very special feature in the program, considering the principles of universal design, and I self-learned the data structure. Since I push this feature onto my branch first, and it is already pulled by my other teammates, it is mixed in one pull request, and we share the same link. Then I fixed the letter case in a latter commit, so the user does not need to worry about the latter case. This feature is also highly connected with GUI, since it is a key section in a main page, and GUI needs to be implemented based on it.

###Refactoring

We mainly used refactors for renaming variables and methods for classes or simply renaming classes. If we just change it by hand, it could be really complex since some variables or methods are called by other classes. Except for the unnecessary complexity, if we missed out one or more spots left unchanged, some error could occur which affects the whole program. But with refactoring, we won’t need to worry about those side effects of changing names, as a refactor would automatically find all usages and change them for us. Furthermore, moving classes from folder to folder is also a good time to use a refactor, especially when we have changes with our package design. When moving a class, the package of the class would also change, then the original imports of other classes would have to change as well. With refactor, the changes can be done really simple and the overall running of the program would not be affected.
