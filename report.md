## Progress Report

### Specification Summary
To summarize the specifications we wrote, our program provides a platform for normal users to create their own movie playlist by giving likes to their favourite movies and post their reviews for the movies of interest. Besides, they can also modify their playlists, check the number of likes and see reviews of the movie after searching it. Unlike normal users, as another type of user, administrators can upload movies.

### CRC Model Summary
According to our CRC model, we have 16 classes in total, each class is assigned with a function according to the clean architecture. Entity includes User, Normal User, Admin User, Movie, Review, which is the innermost part of the code, that creates new objects when other outer classes calls. Use cases are Movie Manager, Review Manager, User Manager, Writeflie, Write Review, Write Movie, Write User, and IgnoreFirstLineBufferedWriter. The managers pack the inputs and interact with write classes to create/write/read the file. There are two controllers, AdminInputProcessor and NormalInuptProcessor. Each is responsible for receiving and processing input from the UI. The UserInterface is the command line interface which receives input.

### Walk-through Summary
In the program, Controllers (AdminInputProcessor and NormalInputProcessor) will receive necessary inputs typed by users from the Command Line Interface (UserInterface in this case), and pass them to Use Cases (all the xxxManager). After that, entities will receive all the information needed and those Use Cases (all the Writexxx) will record the changes.

The walk-through we wrote contains a scenario for users to write reviews. In order to write reviews for each movie, users should register an account first, and do the movie search in the program; after that, the user is able to write reviews and see others’ reviews for the specific movie. The reviews created by users will be recorded into files. The whole process of writing reviews follows the order mentioned above.

### Skeleton Program Summary
The code should compile and run as expected (described in scenario walk-through),
and we do have two unit tests for NormalUser and both passed.

In the movie part, some warnings were shown due to the fact that IntelliJ detects path.toString is redundant. However, if changed as suggested (remove .toString), errors would appear. Also, we have some used codes which are preparations for phase one that have been commented out, which might also cause some warning.

In UserInterface, when the user chooses from different options, we use IF statement but IntelliJ has a warning “if can be replaced with switch”. Also, there is duplicate code which we would like to add a new helper class in phase 1.

Some special techniques or special usage of Property Class. A complicated way was used to ignore the comments that are automatically added to the property files while property.store() is used. We are wondering if there are better ways? Also, for the path, we can only get the absolute path to the module folder but not anything inside it. We need to type the path inside the module folder ourselves to be able to access the files. We are wondering if there is any way to access folders with different paths by the same codes.

### Some Problems
We are struggling with some special techniques and special usage of Property Class. Also, a complicated way was used to ignore the comments that are automatically added to the property files while property.store() is used. We are wondering if there are better ways. In addition, for the path, we can only get the absolute path to the module folder but not anything inside it. We need to type the path inside the module folder ourselves to be able to access the files. We are wondering if there is any way to access folders with different paths by the same codes.

When we wrote UserInterface, we encountered some corner cases. There are some questions we had: What is the scanner receiving when the user just pushes enter? How to use Try...Catch statement more efficiently, for example, when the method I call only returns true or false, the try-catch block is no longer useful, but there are still some weird cases (like when the user just pushes enter as input) that catches exceptions.We might need to break the large block of UI into some small blocks of code using some helper methods.

Both AdminInputProcessor and NormalInputProcessor use the same private helper method, which is a duplicating use of code. We have not come up with an appropriate solution to it.

### Some Good Designs
Our structure of code works well because our design of code perfectly follows the Clean Architecture Model, each layer has its own clear functions, which makes our code tidy and clean.
In addition, the program has considered a lot of corner conditions and added some exception handling features, so when the program runs it would be less likely to have errors which cause the program to break.

### Contribution Summary
In this project, Jing Pan completed the User, AdminUser, NormalUser UserManager, WriteFile and WriteUser. Jacquelyn Wang worked on the ReviewManager, and also completed NormalInputProcessor with Yuxuan Li, and Yuxuan also completed AdminInputProcessor and the unittest of NormalUser Class. Fan Pan and Haitao Qiu worked together for UserInterface. Haitao Qiu also completed MovieManager class. Jiaxi Li finished Review and WriteReview classes. Hao Han worked for classes Movie, WriteMovie and the helper class called IgnoreFirstLineBufferedWriter.

### Future Plans
We hope to add another function, “coins”, for our programs. In our plan, normal users can earn coins after registration and give coins to their favourite reviewers. When the amount of coins sent by normal users reaches a certain amount, they can get tickets, which could be used to unlock functions, like changing their username and increasing the limit number of playlists.

We also want to equip AdminUser with the methods of deleting movies and deleting reviews. In the future, normal users can also upload movies if these movies have not been uploaded yet.

Moreover, three rankings will be displayed on the platform. The first one is the ranking of likes of videos, which can help people find inspiration for their next movie. Second one is the ranking of reviews according to the number of coins they receive. We hope it could make more people see the high-quality reviews. The last one ranks the users by the number of coins they gave out, we hope users will be encouraged to give out more coins by this ranking.
