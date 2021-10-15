import java.io.IOException;
import java.util.Scanner;



public class UserInterface {

    public static void main(String[] args) throws IOException {
        System.out.println("Please enter your choice from Register, Login, Admin register and Admin login:");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equals("Register")){
            NormalInputProcessor controller = new NormalInputProcessor();
            System.out.println("Please enter your username:");
            String username = scanner.nextLine();
            System.out.println("Please enter your password:");
            String password = scanner.nextLine();
            controller.register(username,password);
        }else if (choice.equals("Login")){
            NormalInputProcessor controller = new NormalInputProcessor();
            System.out.println("Please enter your username:");
            String username = scanner.nextLine();
            System.out.println("Please enter your password:");
            String password = scanner.nextLine();
            controller.login(username,password);
            System.out.println("Enter 'Search' to search a movie or 'Profile' to update your info.");
            String choose = scanner.nextLine();
            if (choose.equals("Profile")){
                System.out.println(controller.profile_page(username));
                System.out.println("Enter 'edit profile' or 'undo-like the movie' to remove movie from your playlist");
                String choise = scanner.nextLine();
                if (choise.equals("edit profile")){
                    System.out.println("Please enter your phone number");
                    String contactinfo = scanner.nextLine();
                    controller.edit_profile(contactinfo);
                } else if (choise.equals("undo-like the movie")){
                    System.out.println("type the movie name you would like to remove from your playlist");
                    String mvname = scanner.nextLine();
                    controller.undo_like(mvname);
                } else {
                    System.out.println("wrong input"); // might replace with try...catch
                }
            } else if (choose.equals("Search")){
                System.out.println("Enter the movie name you'd like to find");
                String moviename = scanner.nextLine();
                System.out.println(controller.movie_profile(moviename));
                System.out.println("Enter 'Write a review', 'Like the movie' or 'Exit program'");
                String c = scanner.nextLine();
                if (c.equals("Write a review")){
                    System.out.println("Enter your review of the movie");
                    String review_content = scanner.nextLine();
                    controller.write_review(moviename, review_content);
                } else if (c.equals("Like the movie")){
                    controller.like_movie(moviename);
                } else if (c.equals("Exit program")){
                    System.exit(1);
                }
            }


        }else if (choice.equals("Admin register")){
            AdminInputProcessor controller = new AdminInputProcessor();
            System.out.println("Please enter your username:");
            String username = scanner.nextLine();
            System.out.println("Please enter your password:");
            String password = scanner.nextLine();
            System.out.println("Please enter your administration code:");
            String code = scanner.nextLine();
            controller.register(username,password, code);

        }else if (choice.equals("Admin login")){
            AdminInputProcessor controller = new AdminInputProcessor();
            System.out.println("Please enter your username:");
            String username = scanner.nextLine();
            System.out.println("Please enter your password:");
            String password = scanner.nextLine();
            System.out.println("Please enter your administration code:");
            String code = scanner.nextLine();
            controller.login(username,password, code);
        }else {
            System.exit(1);
        }





    }
}





