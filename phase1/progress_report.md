## Progress Report

### Major design decisions
1. Added of coin feature
The user can receive a coin when she/he writes a review, and can give coin to the reviews she/he likes. We hope this coin mechanism can increase the quality and quantity of reviews in our application. Ideally the number of coins that a review receives increases with the quality of the review, which may incentivize the users to write reviews of higher quality. In addition, we hope the users will write more reviews in order to earn coins. 

2. Added GUI
We designed some GUI views to complete our scenarios. This makes our program one step closer to the complete program we had anticipated at the beginning of the project. For example, the switching between user profile page, playlist, and category is presented nicely to users with the swing implementation.
We also have some plans for the future. We hope there will be a search interface shown after clicking the button on the profile page, where users could search the movie name. After searching the correct movie name, there will be a page for the movie, showing the movie name, the likes received and all reviews for this movie. Users could give coins to the reviews they have interest in.

3. Separate long class into several smaller classes
The class NormalInputProcessor in phase 0 is splitted to 4 classes. With InterfaceAdapter.Controller.NormalController being the parent class, and InterfaceAdapter.Controller.NormalCCoin, InterfaceAdapter.Controller.NormalCMovie, and InterfaceAdapter.Controller.NormalCUser as the child class. The change was made to comply with the single responsibility principle of SOLID. And now each of the three different subclasses is responsible for smaller group of methods that are more closely related to each other comparing to the single long NormalInputProcessor class in phase 0.

4. Move InterfaceAdapter.WriteFiles.WriteFile class to the Interface Adapter layer and make them be the gateway classes.

5. Create a new class, InterfaceAdapter.Controller.InstanceMain, on Interface Adapter layer
InterfaceAdapter.Controller.InstanceMain declares all the instances of the controller, gateway and use case class that will be needed when the program runs, so that UI and GUI can use the instance in the InterfaceAdapter.Controller.InstanceMain class without declaring the new instance, it prevents some unknown bugs.

### Clean architecture
To follow clean architecture, a new class InterfaceAdapter.Controller.InstanceMain is added in the Interface Adapter layer, and the outermost layer GUI can only interact with it.
1. As the lowest level, our Frameworks and Drivers consist of two main parts, UI and GUI for Phase 1. (In Phase 2, UI will be replaced with GUI completely.) 

   **GUI**: View, Shared view (as a child cass of View), GUIEditContactInfo, GUIProfile, GUIMain, GUIUserLogin, GUIUserRegister
   
   **UI**: UserInterface, UI_login, UI_register, UI_admin_login, UI_admin_register, admin_helper, user_helper
2. The second lowest level, Interface Adapters include Controller and Gateway-related classes. 

   **Controller**: InterfaceAdapter.Controller.NormalController, InterfaceAdapter.Controller.NormalCCoin, InterfaceAdapter.Controller.NormalCUser, InterfaceAdapter.Controller.NormalCMovie, InterfaceAdapter.Controller.AdminInputProcessor
   
   **Gateway**: InterfaceAdapter.WriteFiles.WriteFile, InterfaceAdapter.WriteFiles.WriteUser, InterfaceAdapter.WriteFiles.WriteReview, WriteMoive
   
   **Interface Adapter**: InterfaceAdapter.Controller.InstanceMain
3. At a higher level, Use Case consists of our Manager classes. Following the Dependency Rule, Use Case knows nothing about UI and GUI.

   **Manager**: UseCase.UserManager, UseCase.MovieManager, UseCase.CoinManager, UseCase.ReviewManager
4. The highest level is Entity, which can be manipulated by Use Case.

   **Entity**: Core.User.User, Core.Review, Core.Movie
   
Following the Dependency Rule, source code dependencies point only toward higher-level policies (from Frameworks & Drivers to Entity). And the name of functions, classes and variables declared in lower level must not be mentioned by the code in higher level.




### SOLID

#### Single responsibility:  
During phase 0 there were only 2 controller classes which were responsible for all the actions done by user/admin. In phase 1, these 2 classes were separated into different controller classes, and each of them had only one responsibility. In GUI, one class is only responsible for displaying one frame, which follows the single responsibility. The single responsibility makes the code have a better structure.

#### Open-closed principle:
It might be violated. 
GUI follows the open-closed principle. For example, if a new frame is to be added, we only need a new class. If we want to add a new button that generates the next window, these methods could be set in the super class View, and there is no need to modify other existing classes.

#### Liskov substitution: 
Core.User.User is the parent class of Core.User.NormalUser and Admin Core.User.User. All features in Core.User.User are passed to its child class, and we can replace Core.User.User with either Core.User.NormalUser or Core.User.AdminUser and the program should not disrupt the behavior of our program. Also, we have a InterfaceAdapter.WriteFiles.WriteFile class which is the parent class of InterfaceAdapter.WriteFiles.WriteMovie, InterfaceAdapter.WriteFiles.WriteUser and InterfaceAdapter.WriteFiles.WriteReview. These three classes also inherited all features from InterfaceAdapter.WriteFiles.WriteFile. Although they are in charge of different aspects of the project, they perform their functions in similar ways. Except for that, a class called InterfaceAdapter.Controller.NormalController acts as the parent class of other controllers for movie, coin, user and review. These controllers each have their own type of object that they are in charge of but they all have similar features that their parent class, InterfaceAdapter.Controller.NormalController, passed to them.

#### Interface segregation:  
All of our interfaces are small and specific now, so Interface segregation principle holds. We used to have a large UI and controllers in phase 0 which might have violated the principle, so we divide them into more small and specific classes now in phase 1 using some design patterns. 

#### Dependency inversion: 
For the three Write file classes, they do not only create files, but also call Manager class to create objects, which seems a little bit violate the Single Responsibility of SOLID principle. However, it is called by other classes and the functionaries are essential. 
Also, we need to pass data with the Write file class and if we change it, there will be much more redundant steps and largely increase runtime.
Generally, dependency inversion works in our program since the high level modules do not depend on low level modules. Thus, the structure of our program is clean and tidy, which makes our program easy to test and maintain.


### Packaging
The project is packaged by layers. Four layers: Core, Use Cases, Interface Adapter and Framework are used to name the four packages that contain the body of our codes. For each layer, lower level packages are contained and classes at the same layer are separated by their functionaries. Core contains three entities: Core.User.User (which is also a package containing Core.User.NormalUser and Core.User.AdminUser), Core.Movie and Core.Review. UseCases contain Manager classes. Manager package contains four managers for movie, user, review and coin. The next layer package is InterfaceAdapter, which contains controllers package for movie, review, user and coin, inputprocessor and instancemain; and also InterfaceAdapter.WriteFiles.WriteFile package contains InterfaceAdapter.WriteFiles.WriteMovie, InterfaceAdapter.WriteFiles.WriteUser, InterfaceAdapter.WriteFiles.WriteReview and a InterfaceAdapter.WriteFiles.WriteFile class. The outermost layer is the Framework, which contains UI and GUI, two packages. However, some errors still occur due to the fact that we are unable to import and use every class across packages correctly.


### Question: 
Is there a better way to design GUI other than swing?
GUI is not “thread safe”, since we did not learn anything about multi-thread programming, is it still a problem?
After putting classes in packages, errors occurred as some classes cannot be called due to the fact that they are not in the same package, but importing them would change their type. For example, Core.Movie type now changed to Core.Core.Movie type. Is there any way to avoid these errors?
To save data from Hashmap and keep its structure, we used properties for InterfaceAdapter.WriteFiles.WriteMovie, however, when running, a line of “ava.io.FileOutputStream@dc24521Check your review” appeared. Though it does not affect the code running, it is annoying. Is there any way to delete that?

### Things are working well:
We have updated some new functions to the program, which supports users to give coins to reviews, and users will be rewarded after giving comments to movies.
Interaction with GUI to create/read userfile to register/login
Users can edit stuff on their user profile and GUI is able to update new info dynamically.

### Group work in Phase 1:
Joanna worked on the overall GUI creation & design and writing design documents.

Yuxuan Li worked on updating a controller class InterfaceAdapter.Controller.AdminInputProcessor and implementing a new use case class UseCase.CoinManager.

Jacquelyn Wang was responsible for splitting the long class NormalInputProcessor in phase 0, and updating the UseCase.ReviewManager InterfaceAdapter.Controller.NormalController, InterfaceAdapter.Controller.NormalCCoin, InterfaceAdapter.Controller.NormalCUser, InterfaceAdapter.Controller.NormalCMovie for the altered file structure and the newly added coin feature.

Jiaxi Li worked on GUI design, fix and update some features in Core.Review and Write Core.Review class, and complete tests for Core.Review Class.

Jing Pan (Ella) worked on three InterfaceAdapter.WriteFiles.WriteFile Classes, changing the layer of InterfaceAdapter.WriteFiles.WriteFile Classes from use case to gateway and changing the way for program to create the entity instance; creating a class called InterfaceAdapter.Controller.InstanceMain to store all the instance will be needed when program runs; Changing the corresponding places in all classes that how all classes call other classes when they need; Updating the feature of coin system in Core.User.User Classes and UseCase.UserManager.

Andy worked on designing packages and fixed some features on Core.Movie&InterfaceAdapter.WriteFiles.WriteMovie, designed tests for movie parts, and worked on design documents.
Haitao Qiu is responsible for UI and wrote unittest for moviemanager, dividing the UI into several small classes and adding some extra features to it.


### Plans of Phase 2:
Fan, Andy Han and Jiaxi Li plan to complete the whole GUI design for our program.

Yuxuan Li will be working on implementing new functions supporting Core.User.AdminUser to delete movies, modify information of movies and delete reviews.

Jacquelyn Wang plans to implement the three rankings, these three lists will show the top movies receiving the most likes, popular reviews with highest coins and users who are giving out the most coins.
In this period, NormalUsers are also allowed to upload movies. 

Haitao will work on this part and continue to develop UI.

Jing Pan (Ella) will continue working on the InterfaceAdapter.WriteFiles.WriteFile class since some potential violations have been noticed.  Moreover,  may use Template design pattern to reduce the duplicated code and simplify all Three InterfaceAdapter.WriteFiles.WriteFile classes since there are many codes that are the same.

There are some bugs for adding coins to review, Jiaxi Li will fix it in the Phase 2.
