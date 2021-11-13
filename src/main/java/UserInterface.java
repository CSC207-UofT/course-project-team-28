import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A user interface which interacts with users to prompt to get input from them.
 */

public class UserInterface {
    //TODO
    private static final InstanceMain IM = new InstanceMain();
    private static String userName = "";

    public static void main(String[] args) {
        // User chooses to register/login as normal or admin user
        System.out.println("Please enter your choice from Register, Login, Admin register and Admin login:");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        String[] option= new String[]{"Register", "Login", "Admin register", "Admin login"};
        List<String> options = new ArrayList<>(Arrays.asList(option));
        while (!options.contains(choice)){
            System.out.println("Please reenter your choice from Register, Login, Admin register and Admin login:");
            choice = scanner.nextLine();
        }


        // if user chooses to register
        if (choice.equals("Register")){
            String username;
            String password;
            System.out.println("Please enter your username (should only contains numbers and letter):");
            username = scanner.nextLine();
            System.out.println("Please enter your password (should only contains numbers and letter):");
            password = scanner.nextLine();


            // if the username/password is invalid, prompt the user to enter again
            while (!(IM.nucontroller.register(username, password))){
                System.out.println("Something is wrong with your username or password, please try again");
                System.out.println("Please enter your username (should only contains numbers and letter):");
                username = scanner.nextLine();
                System.out.println("Please enter your password (should only contains numbers and letter):");
                password = scanner.nextLine();
            }
            //TODO
            userName = username;
            IM.wu.createFile(username, password, "NormalUser");
            if(IM.nucontroller.login(username, password)) {
                System.out.println("Account successfully created, you are automatically logged in.");
            }


            //Proceed to search/profile functions
            System.out.println("Enter 'Search' to search a movie or 'Profile' to go to the profile page.");
            String choose = scanner.nextLine();
            String[] option_1= new String[]{"Search", "Profile"};
            List<String> options_1 = new ArrayList<>(Arrays.asList(option_1));
            while (!options_1.contains(choose)) {
                System.out.println("Please reenter 'Search' to search a movie or 'Profile' to go to the profile page:");
                choose = scanner.nextLine();
            }
            if (choose.equals("Profile")){
                System.out.println(IM.nucontroller.profile_page(username));
                System.out.println("Enter 'edit profile' or 'undo-like the movie' to remove movie from your playlist");
                String choise = scanner.nextLine();

                String[] option_2= new String[]{"edit profile", "undo-like the movie"};
                List<String> options_2 = new ArrayList<>(Arrays.asList(option_2));
                while (!options_2.contains(choise)) {
                    System.out.println("Please reenter 'edit profile' or 'undo-like the movie' to remove movie from your playlist:");
                    choise = scanner.nextLine();
                }

                if (choise.equals("edit profile")){
                    System.out.println("Please enter your phone number");
                    String contactinfo = scanner.nextLine();

                    //TODO
                    IM.nucontroller.edit_profile(contactinfo, "contactInfo");
                    IM.wu.editProfileReadAndWrite(contactinfo, userName, "contactInfo");

                    System.out.println("your new profile is: " + IM.nucontroller.profile_page(username));
                } else if (choise.equals("undo-like the movie")){
                    if (IM.nucontroller.empty_playlist()){
                        System.out.println("You have an empty playlist.");
                        System.out.println("program exits");
                        System.exit(0);
                    }
                    else {
                        try {
                            System.out.println("type the movie name you would like to remove from your playlist");
                            String mvname = scanner.nextLine();
                            while (!(IM.nucontroller.undo_like(mvname))){
                                System.out.println("movie is not in your playlist, please enter a movie that is in your playlist: ");
                                mvname = scanner.nextLine();
                            }

                            //TODO
                            IM.nucontroller.undo_like(mvname);
                            IM.wu.undoLikeReadAndWrite(mvname, userName);
                            IM.wm.addLikeToFile(mvname, "decrease");

                            System.out.println("your new profile is " + IM.nucontroller.profile_page(username));
                            System.out.println("Movie successfully removed.");
                            System.out.println("program exits.");
                        } catch (Exception e) {
                            System.out.println("Something is wrong.");
                            System.out.println("program exits.");
                        }
                    }
                } else {
                    System.out.println("wrong input");
                    System.exit(1);
                }

             // if user choose to search
            } else if (choose.equals("Search")){
                System.out.println("Enter the movie name you'd like to find");
                String moviename = scanner.nextLine();
                while (!(IM.nucontroller.if_movie_exist(moviename))) {
                    System.out.println("Movie does not exits, please re-enter the movie name: ");
                    moviename = scanner.nextLine();
                }
                System.out.println(IM.nucontroller.movie_profile(moviename));
                System.out.println("Enter 'Write a review', 'Like the movie' or 'Exit program'");
                String c = scanner.nextLine();

                String[] option_3= new String[]{"Write a review", "Like the movie", "Exit program"};
                List<String> options_3 = new ArrayList<>(Arrays.asList(option_3));
                while (!options_3.contains(c)) {
                    System.out.println("Please reenter 'Write a review', 'Like the movie' or 'Exit program':");
                    c = scanner.nextLine();
                }


                if (c.equals("Write a review")){
                    System.out.println("Enter your review of the movie");
                    String review_content = scanner.nextLine();

                    //TODO
                    //nucontroller.write_review(moviename, review_content);
                    IM.wr.createFile(userName, moviename, review_content);
                    IM.wm.addReviewToFile(userName, moviename, review_content);

                    System.out.println("Check your review: " + IM.nucontroller.movie_profile(moviename));
                    System.out.println("program exits.");
                } else if (c.equals("Like the movie")){

                    //TODO
                    IM.nucontroller.like_movie(moviename);
                    IM.wu.givelikeReadAndWrite(moviename, userName);
                    IM.wm.addLikeToFile(moviename, "Increase");

                    System.out.println("Check your likes: " + IM.nucontroller.movie_profile(moviename));
                    System.out.println("program exits.");
                } else if (c.equals("Exit program")){
                    System.out.println("Program exits.");
                    System.exit(0);
                } else {
                    System.out.println("wrong input");
                    System.out.println("Program exits.");
                    System.exit(1);
                }
            } else {
                System.out.println("wrong input");
                System.out.println("Program exits.");
                System.exit(1);
            }
        // if user chooses to login as normal user
        }else if (choice.equals("Login")){
            String username;
            String password;
            System.out.println("Please enter your username (should only contains numbers and letter):");
            username = scanner.nextLine();
            System.out.println("Please enter your password (should only contains numbers and letter):");
            password = scanner.nextLine();
            while (!(IM.nucontroller.login(username, password))){
                System.out.println("Username or password incorrect, please try again.");
                System.out.println("Please enter your username (should only contains numbers and letter):");
                username = scanner.nextLine();
                System.out.println("Please enter your password (should only contains numbers and letter):");
                password = scanner.nextLine();
            }
            userName = username;
            System.out.println("Login successful.");

            // Proceed to search or profile functions
            System.out.println("Enter 'Search' to search a movie or 'Profile' to go to the profile page.");
            String choose = scanner.nextLine();
            String[] option_1= new String[]{"Search", "Profile"};
            List<String> options_1 = new ArrayList<>(Arrays.asList(option_1));
            while (!options_1.contains(choose)) {
                System.out.println("Please reenter 'Search' to search a movie or 'Profile' to go to the profile page:");
                choose = scanner.nextLine();
            }
            if (choose.equals("Profile")){
                System.out.println(IM.nucontroller.profile_page(username));
                System.out.println("Enter 'edit profile' or 'undo-like the movie' to remove movie from your playlist");
                String choise = scanner.nextLine();

                String[] option_2= new String[]{"edit profile", "undo-like the movie"};
                List<String> options_2 = new ArrayList<>(Arrays.asList(option_2));
                while (!options_2.contains(choise)) {
                    System.out.println("Please reenter 'edit profile' or 'undo-like the movie' to remove movie from your playlist:");
                    choise = scanner.nextLine();
                }

                if (choise.equals("edit profile")){
                    System.out.println("Please enter your phone number");
                    String contactinfo = scanner.nextLine();

                    //TODO
                    IM.nucontroller.edit_profile(contactinfo, "contactInfo");
                    IM.wu.editProfileReadAndWrite(contactinfo, userName, "contactInfo");
                    System.out.println("your new profile is: " + IM.nucontroller.profile_page(username));
                } else if (choise.equals("undo-like the movie")){
                    if (IM.nucontroller.empty_playlist()){
                        System.out.println("You have an empty playlist.");
                    } else {
                        try {
                            System.out.println("type the movie name you would like to remove from your playlist");
                            String mvname = scanner.nextLine();
                            while (!(IM.nucontroller.undo_like(mvname))){
                                System.out.println("movie is not in your playlist, please enter a movie that is in your playlist: ");
                                mvname = scanner.nextLine();
                            }

                            //TODO
                            IM.nucontroller.undo_like(mvname);
                            IM.wu.undoLikeReadAndWrite(mvname, userName);
                            IM.wm.addLikeToFile(mvname, "decrease");

                            System.out.println("your new profile is " + IM.nucontroller.profile_page(username));
                            System.out.println("Movie successfully removed.");
                            System.out.println("program exits.");
                        } catch (Exception e) {
                            System.out.println("Something is wrong");
                            System.out.println("program exits.");
                        }
                    }
                } else {
                    System.out.println("wrong input");
                    System.out.println("program exits.");
                    System.exit(1);
                }
            } else if (choose.equals("Search")){
                try {
                    System.out.println("Enter the movie name you'd like to find");
                    String moviename = scanner.nextLine();
                    while (!(IM.nucontroller.if_movie_exist(moviename))) {
                        System.out.println("Movie does not exits, please re-enter the movie name: ");
                        moviename = scanner.nextLine();
                    }
                    System.out.println(IM.nucontroller.movie_profile(moviename));
                    System.out.println("Enter 'Write a review', 'Like the movie' or 'Exit program'");
                    String c = scanner.nextLine();

                    String[] option_3= new String[]{"Write a review", "Like the movie", "Exit program"};
                    List<String> options_3 = new ArrayList<>(Arrays.asList(option_3));
                    while (!options_3.contains(c)) {
                        System.out.println("Please reenter 'Write a review', 'Like the movie' or 'Exit program':");
                        c = scanner.nextLine();
                    }

                    if (c.equals("Write a review")) {
                        System.out.println("Enter your review of the movie");
                        String review_content = scanner.nextLine();

                        //TODO
                        //nucontroller.write_review(moviename, review_content);
                        IM.wr.createFile(userName, moviename, review_content);
                        IM.wm.addReviewToFile(userName, moviename, review_content);

                        System.out.println("Check your review: " + IM.nucontroller.movie_profile(moviename));
                        System.out.println("program exits.");
                    } else if (c.equals("Like the movie")) {

                        //TODO
                        IM.nucontroller.like_movie(moviename);
                        IM.wu.givelikeReadAndWrite(moviename, userName);
                        IM.wm.addLikeToFile(moviename, "Increase");

                        System.out.println("Check your likes: " + IM.nucontroller.movie_profile(moviename));
                        System.out.println("program exits.");
                    } else if (c.equals("Exit program")) {
                        System.out.println("program exits.");
                        System.exit(0);
                    } else {
                        System.out.println("wrong input");
                        System.out.println("program exits.");
                        System.exit(1);
                    }
                } catch (Exception e) {
                    System.out.println("Movie not found");
                }
            }

        // If user chooses to register as admin, a fixed administration code is needed.
        }else if (choice.equals("Admin register")){
            String username;
            String password;
            String code;
            System.out.println("Please enter your username (should only contains numbers and letter):");
            username = scanner.nextLine();
            System.out.println("Please enter your password (should only contains numbers and letter):");
            password = scanner.nextLine();
            System.out.println("Please enter your administration code:");
            code = scanner.nextLine();
            while (!(IM.aucontroller.register(username, password, code))){
                System.out.println("Somthing wrong with your username/password/code, please try again.");
                System.out.println("Please enter your username (should only contains numbers and letter):");
                username = scanner.nextLine();
                System.out.println("Please enter your password (should only contains numbers and letter):");
                password = scanner.nextLine();
                System.out.println("Please enter your administration code:");
                code = scanner.nextLine();
            }
            userName = username;
            IM.wu.createFile(username, password, "AdminUser");
            if(IM.aucontroller.login(username, password, code)) {
                System.out.println("Admin account successfully created, you are automatically logged in.");
            }


            // now admin can upload movie or delet movie
            System.out.println("Enter 'Upload movie'.");
            String choose = scanner.nextLine();

            String[] option_4= new String[]{"Upload movie"};
            List<String> options_4 = new ArrayList<>(Arrays.asList(option_4));
            while (!options_4.contains(choose)) {
                System.out.println("Please reenter 'Upload movie':");
                choose = scanner.nextLine();
            }

            if (choose.equals("Upload movie")){
                System.out.println("Please enter the name of the movie");
                String movie_name = scanner.nextLine();
                System.out.println("Please enter the link of the movie");
                String movie_link = scanner.nextLine();
                while (!(IM.aucontroller.upload_movie(movie_name, movie_link))) {
                    System.out.println("invalid inputs, please try again: ");
                    System.out.println("Please enter the name of the movie");
                    movie_name = scanner.nextLine();
                    System.out.println("Please enter the link of the movie");
                    movie_link = scanner.nextLine();
                }
                //TODO
                //aucontroller.upload_movie(movie_name, movie_link);
                IM.wm.createFile(movie_name, movie_link, "");
                System.out.println("Movie added");
                System.out.println("program exits.");

            //} else if (choose.equals("Delete movie")){
            //    System.out.println("Please enter the name of the movie");
            //    String movie_name = scanner.nextLine();
            //    aucontroller.delete_movie(movie_name);
            } else {
                System.out.println("wrong input");
                System.out.println("program exits.");
                System.exit(1);
            }


        //if user chooses to login as admin user.
        }else if (choice.equals("Admin login")){
            String username;
            String password;
            String code;
            System.out.println("Please enter your username (should only contains numbers and letter):");
            username = scanner.nextLine();
            System.out.println("Please enter your password (should only contains numbers and letter):");
            password = scanner.nextLine();
            System.out.println("Please enter your administration code:");
            code = scanner.nextLine();
            while (!(IM.aucontroller.login(username, password, code))) {
                System.out.println("Wrong input of username/password/code, please try again.");
                System.out.println("Please enter your username (should only contains numbers and letter):");
                username = scanner.nextLine();
                System.out.println("Please enter your password (should only contains numbers and letter):");
                password = scanner.nextLine();
                System.out.println("Please enter your administration code:");
                code = scanner.nextLine();
            }
            System.out.println("Login successful.");

            System.out.println("Enter 'Upload movie'.");
            String choose = scanner.nextLine();

            String[] option_4= new String[]{"Upload movie"};
            List<String> options_4 = new ArrayList<>(Arrays.asList(option_4));
            while (!options_4.contains(choose)) {
                System.out.println("Please reenter 'Upload movie':");
                choose = scanner.nextLine();
            }

            if (choose.equals("Upload movie")){
                System.out.println("Please enter the name of the movie");
                String movie_name = scanner.nextLine();
                System.out.println("Please enter the link of the movie");
                String movie_link = scanner.nextLine();
                while (!(IM.aucontroller.upload_movie(movie_name, movie_link))) {
                    System.out.println("invalid inputs, please try again.");
                    System.out.println("Please enter the name of the movie");
                    movie_name = scanner.nextLine();
                    System.out.println("Please enter the link of the movie");
                    movie_link = scanner.nextLine();
                }
                //TODO
                //aucontroller.upload_movie(movie_name, movie_link);
                IM.wm.createFile(movie_name, movie_link, "");
                System.out.println("Movie added");
                System.out.println("program exits.");
            //} else if (choose.equals("Delete movie")){
            //    System.out.println("Please enter the name of the movie");
            //    String movie_name = scanner.nextLine();
            //    aucontroller.delete_movie(movie_name);
            } else {
                System.out.println("wrong input");
                System.out.println("program exits.");
                System.exit(1);
            }

        }else {
            System.out.println("wrong input");
            System.out.println("program exits.");
            System.exit(1);
        }
    }
}





