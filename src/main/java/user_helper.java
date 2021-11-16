import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class user_helper {
    public static List<String> register(Scanner scanner, InstanceMain IM){
        String username;
        String password;
        System.out.println("Please enter your username (should only contains numbers and letter):");
        username = scanner.nextLine();
        System.out.println("Please enter your password (should only contains numbers and letter):");
        password = scanner.nextLine();
        while (!(IM.ncu.register(username, password, IM.wu))){
            System.out.println("Username or password incorrect, please try again.");
            System.out.println("Please enter your username (should only contains numbers and letter):");
            username = scanner.nextLine();
            System.out.println("Please enter your password (should only contains numbers and letter):");
            password = scanner.nextLine();
        }
        return Arrays.asList(username, password);
    }
    public static List<String> login(Scanner scanner, InstanceMain IM){
        String username;
        String password;
        System.out.println("Please enter your username (should only contains numbers and letter):");
        username = scanner.nextLine();
        System.out.println("Please enter your password (should only contains numbers and letter):");
        password = scanner.nextLine();
        while (!(IM.ncu.login(username, password))){
            System.out.println("Username or password incorrect, please try again.");
            System.out.println("Please enter your username (should only contains numbers and letter):");
            username = scanner.nextLine();
            System.out.println("Please enter your password (should only contains numbers and letter):");
            password = scanner.nextLine();
        }
        return Arrays.asList(username, password);
    }

    public static void ui_user_body(Scanner scanner, InstanceMain IM, String userName) {
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
            Object[] arr = IM.ncu.profilePage(userName);
            Object[] profile = new Object[]{arr[0], arr[2], arr[3], arr[4], arr[5], arr[6]};

            //System.arraycopy(arr, 2, arr, 0, 5);
            //System.out.println(profile +"\n" + Arrays.toString(arr));
            System.out.println(Arrays.toString(profile));
            System.out.println("Enter 'edit profile' or 'undo-like the movie' to remove movie from your playlist");
            String choice = scanner.nextLine();

            String[] option_2= new String[]{"edit profile", "undo-like the movie"};
            List<String> options_2 = new ArrayList<>(Arrays.asList(option_2));
            while (!options_2.contains(choice)) {
                System.out.println("Please reenter 'edit profile' or 'undo-like the movie' to remove movie from your playlist:");
                choice = scanner.nextLine();
            }

            if (choice.equals("edit profile")){
                System.out.println("Please choose the datatype you want to edit from 'contactInfo', 'description' or 'category'");
                String datatype = scanner.nextLine();
                System.out.println("Please enter the info content you want to update");
                String info = scanner.nextLine();


                IM.ncu.editProfile(info, datatype, IM.wu);

                Object[] arr1 = IM.ncu.profilePage(userName);
                Object[] profile1 = new Object[]{arr1[0], arr1[2], arr1[3], arr1[4], arr1[5], arr1[6]};
                System.out.println("your new profile is: " + Arrays.toString(profile1));
            } else if (choice.equals("undo-like the movie")){
                if (IM.ncm.emptyPlaylist()){
                    System.out.println("You have an empty playlist.");
                    System.out.println("program exits");
                    System.exit(0);
                }
                else {
                    try {
                        System.out.println("type the movie name you would like to remove from your playlist");
                        String mvname = scanner.nextLine();
                        while (!(IM.ncm.undoLike(mvname, IM.wm, IM.wu))){
                            System.out.println("movie is not in your playlist, please enter a movie that is in your playlist: ");
                            mvname = scanner.nextLine();
                        }

                        Object[] arr2 = IM.ncu.profilePage(userName);
                        Object[] profile2 = new Object[]{arr2[0], arr2[2], arr2[3], arr2[4], arr2[5], arr2[6]};
                        System.out.println("your new profile is " + Arrays.toString(profile2));
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
            while (!(IM.ncm.ifMovieExist(moviename))) {
                System.out.println("Movie does not exits, please re-enter the movie name: ");
                moviename = scanner.nextLine();
            }
            System.out.println(IM.ncm.movieProfile(moviename));
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


                IM.ncm.writeReview(moviename, review_content, IM.wr, IM.wm);
                IM.ncc.EarnCoinAfterWriteRev(IM.wu);
                System.out.println("You wrote a review and earned one coin");

                System.out.println("Check your review: " + IM.ncm.movieProfile(moviename));
                System.out.println("program exits.");
            } else if (c.equals("Like the movie")){


                IM.ncm.likeMovie(moviename, IM.wm, IM.wu);

                System.out.println("Check your likes: " + IM.ncm.movieProfile(moviename));
                System.out.println("program exits.");
            } else if (c.equals("Exit program")){
                System.out.println("Program exits.");
                System.exit(0);
            }
//            else if (c.equals("Give coin to review")){
//                System.out.println("please enter the review id:");
//                String reviewid = scanner.nextLine();
//                if (!reviewid.equals("")) {
//                    Integer review_id = Integer.parseInt(reviewid);
//                    IM.ncc.giveCoinToRev(review_id, IM.wr, IM.wu);
//                    System.out.println("You gave a coin to review " + reviewid );
//                    System.out.println("program exits.");
//                }
//
//            }
            else {
                System.out.println("wrong input");
                System.out.println("Program exits.");
                System.exit(1);
            }
        } else {
            System.out.println("wrong input");
            System.out.println("Program exits.");
            System.exit(1);
        }
    }
}
