## walkthrough



Our program allows the user to create an account with a profile. 
In the profile, users can create a playlist of movies that they like, and write reviews for a movie of interest.

*Scenario write review:* 

The **UserInterface** (a UI) gathers user’s review input as a string, 
and passes it to the class **InterfaceAdapter.Controller.NormalCUser** (a controller). 
The controller then packs the review with the corresponding movie name, 
which is delivered to **UseCase.ReviewManager** (a use case) and **Core.Review** (entity) creates a new Core.Review object. 
The use case matches the review and movie name with username, 
then another use case **InterfaceAdapter.WriteFiles.WriteReview** receives this information and creates a file under the **Core.Review folder**(data base). 
**InterfaceAdapter.WriteFiles.WriteReview**, when called by **UseCase.ReviewManager**, can construct an arraylist containing all Core.Review objects in the folder.

