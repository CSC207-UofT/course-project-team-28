## walkthrough



Our program allows the user to create an account with a profile. 
In the profile, users can create a playlist of movies that they like, and write reviews for a movie of interest.

*Scenario write review:* 

The **UserInterface** (a UI) gathers user’s review input as a string, 
and passes it to the class **NormalCUser** (a controller). 
The controller then packs the review with the corresponding movie name, 
which is delivered to **UseCase.ReviewManager** (a use case) and **Entity.Review** (entity) creates a new Entity.Review object. 
The use case matches the review and movie name with username, 
then another use case **Framework.DataAccess.WriteReview** receives this information and creates a file under the **Entity.Review folder**(data base). 
**Framework.DataAccess.WriteReview**, when called by **UseCase.ReviewManager**, can construct an arraylist containing all Entity.Review objects in the folder.

