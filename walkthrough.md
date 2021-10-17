## walkthrough



Our program allows the user to create an account with a profile. 
In the profile, users can create a playlist of movies that they like, and write reviews for a movie of interest.

*Scenario write review:* 

The **UserInterface** (a UI) gathers user’s review input as a string, 
and passes it to the class **NormalInputProcessor** (a controller). 
The controller then packs the review with the corresponding movie name, 
which is delivered to **ReviewManager** (a use case) and **Review** (entity) creates a new Review object. 
The use case matches the review and movie name with username, 
then another use case **WriteReview** receives this information and creates a file under the **Review folder**(data base). 
**WriteReview**, when called by **ReviewManager**, can construct an arraylist containing all Review objects in the folder.

