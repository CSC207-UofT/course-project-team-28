## Progress Report

### Question: 
Is there a better way to design GUI other than swing?
GUI is not “thread safe”, since we did not learn anything about multi-thread programming, is it still a problem?
After putting classes in packages, errors occurred as some classes cannot be called due to the fact that they are not in the same package, but importing them would change their type. For example, Movie type now changed to Core.Movie type. Is there any way to avoid these errors?
To save data from Hashmap and keep its structure, we used properties for WriteMovie, however, when running, a line of “ava.io.FileOutputStream@dc24521Check your review” appeared. Though it does not affect the code running, it is annoying. Is there any way to delete that?

### Things are working well:
We have updated some new functions to the program, which supports users to give coins to reviews, and users will be rewarded after giving comments to movies.
Interaction with GUI to create/read userfile to register/login
Users can edit stuff on their user profile and GUI is able to update new info dynamically.

### Group work in Phase 1:
Joanna worked on the overall GUI creation & design and writing design documents.

Yuxuan Li worked on updating a controller class AdminInputProcessor and implementing a new use case class CoinManager.

Jacquelyn Wang was responsible for splitting the long class NormalInputProcessor in phase 0, and updating the ReviewManager NormalController, NormalCCoin, NormalCUser, NormalCMovie for the altered file structure and the newly added coin feature.

Jiaxi Li worked on GUI design, fix and update some features in Review and Write Review class, and complete tests for Review Class.

Jing Pan (Ella) worked on three WriteFile Classes, changing the layer of WriteFile Classes from use case to gateway and changing the way for program to create the entity instance; creating a class called InstanceMain to store all the instance will be needed when program runs; Changing the corresponding places in all classes that how all classes call other classes when they need; Updating the feature of coin system in User Classes and UserManager.

Andy worked on designing packages and fixed some features on Movie&WriteMovie, designed tests for movie parts, and worked on design documents.
Haitao Qiu is responsible for UI and wrote unittest for moviemanager, dividing the UI into several small classes and adding some extra features to it.


### Plans of Phase 2:
Fan, Andy Han and Jiaxi Li plan to complete the whole GUI design for our program.

Yuxuan Li will be working on implementing new functions supporting AdminUser to delete movies, modify information of movies and delete reviews.

Jacquelyn Wang plans to implement the three rankings, these three lists will show the top movies receiving the most likes, popular reviews with highest coins and users who are giving out the most coins.
In this period, NormalUsers are also allowed to upload movies. 

Haitao will work on this part and continue to develop UI.

Jing Pan (Ella) will continue working on the WriteFile class since some potential violations have been noticed.  Moreover,  may use Template design pattern to reduce the duplicated code and simplify all Three WriteFile classes since there are many codes that are the same.

There are some bugs for adding coins to review, Jiaxi Li will fix it in the Phase 2.
