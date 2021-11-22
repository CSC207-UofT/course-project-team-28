## Specification

Create a program to store contents of movies, including names of those movies and links of those movies, and provide a platform for users to leave comments for each movie and manage their own playlists.

For any administrators admitted by designers, they are able to register and log in via a special code. After that, they can upload the content of movies on the platform.

For a normal user, she/he can log in or create a new account on our platform. The user can search for a movie and comment below the movie they prefer, **and they will receive a coin after writing one review**. They can add the movie to their playlist by “liking” the movie. 
Moreover, users have their own user page. they can see their own username, number of coins, contact information on the page. This page also has two subpages: playlist, which shows this user’s own movie collection, and category, which demonstrates this user’s preference by adding different tags. They can edit their contact information and remove the movie from their playlist on this page.

For the page of the Core.Movie, it will show the name and the link to the movie. All the reviews that all users left for this movie will be also shown on this page, **and other users can give “coins” to the reviews that they like**. 

**We have added the new GUI interface in phase 1, below is a list of the currently available features of GUI. Currently we have a main interface at the beginning, with four buttons to choose to register or log in depending on the user type. After successfully logging in, the user has access to their own page, with username, number of coins, contact information and description shown on the profile page, and another two tabbed pane, playlist of movies and category. On the profile page, users can change their contact information, and the new text will be updated dynamically.**

**For phase 1, an additional feature “coin” is developed. Since directly adding the coin feature would have violated the program’s clean architecture, some classes are switched to other levels and some are split to more class/subclass. Naming convention is followed through refractor and several false class/method/field names are changed GUI is in progress based on the command line interface through the Swing package.**
