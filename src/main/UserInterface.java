import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class UserInterface {

    public static void main(String[] args) throws IOException {
        System.out.println("Please enter your choice from Register, Login, Admin register and Admin login:");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        String[] option= new String[]{"Register", "Login", "Admin register", "Admin login"};
        List<String> options = new ArrayList<>(Arrays.asList(option));
        while (!options.contains(choice)){
            System.out.println("Please reenter your choice from Register, Login, Admin register and Admin login:");
            choice = scanner.nextLine();
        }
        if (choice.equals("Register")){
            NormalInputProcessor controller = new NormalInputProcessor();
            String username;
            String password;
            System.out.println("Please enter your username:");
            username = scanner.nextLine();
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
            while (!(controller.register(username, password))){
                System.out.println("Something is wrong with your username or password, please try again");
                System.out.println("Please enter your username:");
                username = scanner.nextLine();
                System.out.println("Please enter your password:");
                password = scanner.nextLine();
            }
            System.out.println("Account successfully created, you are automatically logged in.");

            System.out.println("Enter 'Search' to search a movie or 'Profile' to go to the profile page.");
            String choose = scanner.nextLine();
            String[] option_1= new String[]{"Search", "Profile"};
            List<String> options_1 = new ArrayList<>(Arrays.asList(option_1));
            while (!options_1.contains(choose)) {
                System.out.println("Please reenter 'Search' to search a movie or 'Profile' to go to the profile page:");
                choose = scanner.nextLine();
            }
            if (choose.equals("Profile")){
                System.out.println(controller.profile_page(username));
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
                    controller.edit_profile(contactinfo);
                } else if (choise.equals("undo-like the movie")){
                    System.out.println("type the movie name you would like to remove from your playlist");
                    String mvname = scanner.nextLine();
                    controller.undo_like(mvname);
                } else {
                    System.out.println("wrong input");
                    System.exit(1);
                }
            } else if (choose.equals("Search")){
                System.out.println("Enter the movie name you'd like to find");
                String moviename = scanner.nextLine();
                System.out.println(controller.movie_profile(moviename));
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
                    controller.write_review(moviename, review_content);
                } else if (c.equals("Like the movie")){
                    controller.like_movie(moviename);
                } else if (c.equals("Exit program")){
                    System.exit(0);
                } else {
                    System.out.println("wrong input");
                    System.exit(1);
                }
            } else {
                System.out.println("wrong input");
                System.exit(1);
            }
        }else if (choice.equals("Login")){
            NormalInputProcessor controller = new NormalInputProcessor();
            String username;
            String password;
            System.out.println("Please enter your username:");
            username = scanner.nextLine();
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
            while (!(controller.login(username, password))){
                System.out.println("Username or password incorrect, please try again.");
                System.out.println("Please enter your username:");
                username = scanner.nextLine();
                System.out.println("Please enter your password:");
                password = scanner.nextLine();
            }
            System.out.println("Login successful.");

            System.out.println("Enter 'Search' to search a movie or 'Profile' to go to the profile page.");
            String choose = scanner.nextLine();
            String[] option_1= new String[]{"Search", "Profile"};
            List<String> options_1 = new ArrayList<>(Arrays.asList(option_1));
            while (!options_1.contains(choose)) {
                System.out.println("Please reenter 'Search' to search a movie or 'Profile' to go to the profile page:");
                choose = scanner.nextLine();
            }
            if (choose.equals("Profile")){
                System.out.println(controller.profile_page(username));
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
                    controller.edit_profile(contactinfo);
                } else if (choise.equals("undo-like the movie")){
                    System.out.println("type the movie name you would like to remove from your playlist");
                    String mvname = scanner.nextLine();
                    controller.undo_like(mvname);
                } else {
                    System.out.println("wrong input");
                    System.exit(1);
                }
            } else if (choose.equals("Search")){
                System.out.println("Enter the movie name you'd like to find");
                String moviename = scanner.nextLine();
                System.out.println(controller.movie_profile(moviename));
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
                    controller.write_review(moviename, review_content);
                } else if (c.equals("Like the movie")){
                    controller.like_movie(moviename);
                } else if (c.equals("Exit program")){
                    System.exit(0);
                } else {
                    System.out.println("wrong input");
                    System.exit(1);
                }
            } else {
                System.out.println("wrong input");
                System.exit(1);
            }


        }else if (choice.equals("Admin register")){
            AdminInputProcessor controller = new AdminInputProcessor();
            String username;
            String password;
            String code;
            System.out.println("Please enter your username:");
            username = scanner.nextLine();
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
            System.out.println("Please enter your administration code:");
            code = scanner.nextLine();
            while (!(controller.login(username, password, code))){
                System.out.println("Somthing wrong with your username/password/code, please try again.");
                System.out.println("Please enter your username:");
                username = scanner.nextLine();
                System.out.println("Please enter your password:");
                password = scanner.nextLine();
                System.out.println("Please enter your administration code:");
                code = scanner.nextLine();
            }
            System.out.println("Admin account successfully created, you are automatically logged in.");

            System.out.println("Enter 'Upload movie' or 'Delete movie'.");
            String choose = scanner.nextLine();

            String[] option_4= new String[]{"Upload movie", "Delete movie"};
            List<String> options_4 = new ArrayList<>(Arrays.asList(option_4));
            while (!options_4.contains(choose)) {
                System.out.println("Please reenter 'Upload movie' or 'Delete movie':");
                choose = scanner.nextLine();
            }

            if (choose.equals("Upload movie")){
                System.out.println("Please enter the name of the movie");
                String movie_name = scanner.nextLine();
                System.out.println("Please enter the link of the movie");
                String movie_link = scanner.nextLine();
                controller.upload_movie(movie_name, movie_link);
            } else if (choose.equals("Delete movie")){
                System.out.println("Please enter the name of the movie");
                String movie_name = scanner.nextLine();
                controller.delete_movie(movie_name);
            } else {
                System.out.println("wrong input");
                System.exit(1);
            }


        }else if (choice.equals("Admin login")){
            AdminInputProcessor controller = new AdminInputProcessor();
            String username;
            String password;
            String code;
            System.out.println("Please enter your username:");
            username = scanner.nextLine();
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
            System.out.println("Please enter your administration code:");
            code = scanner.nextLine();
            while (!(controller.login(username, password, code))) {
                System.out.println("Wrong input of username/password/code, please try again.");
                System.out.println("Please enter your username:");
                username = scanner.nextLine();
                System.out.println("Please enter your password:");
                password = scanner.nextLine();
                System.out.println("Please enter your administration code:");
                code = scanner.nextLine();
            }
            System.out.println("Login successful.");

            System.out.println("Enter 'Upload movie' or 'Delete movie'.");
            String choose = scanner.nextLine();

            String[] option_4= new String[]{"Upload movie", "Delete movie"};
            List<String> options_4 = new ArrayList<>(Arrays.asList(option_4));
            while (!options_4.contains(choose)) {
                System.out.println("Please reenter 'Upload movie' or 'Delete movie':");
                choose = scanner.nextLine();
            }

            if (choose.equals("Upload movie")){
                System.out.println("Please enter the name of the movie");
                String movie_name = scanner.nextLine();
                System.out.println("Please enter the link of the movie");
                String movie_link = scanner.nextLine();
                controller.upload_movie(movie_name, movie_link);
            } else if (choose.equals("Delete movie")){
                System.out.println("Please enter the name of the movie");
                String movie_name = scanner.nextLine();
                controller.delete_movie(movie_name);
            } else {
                System.out.println("wrong input");
                System.exit(1);
            }

        }else {
            System.out.println("wrong input");
            System.exit(1);
        }
    }
}





