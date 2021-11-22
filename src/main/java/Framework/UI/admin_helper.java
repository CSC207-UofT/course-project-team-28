package Framework.UI;

import InterfaceAdapter.Controller.InstanceMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class admin_helper {
    public static List<String> register(Scanner scanner, InstanceMain IM){
        String username;
        String password;
        String code;
        System.out.println("Please enter your username (should only contains numbers and letter):");
        username = scanner.nextLine();
        System.out.println("Please enter your password (should only contains numbers and letter):");
        password = scanner.nextLine();
        System.out.println("Please enter your administration code:");
        code = scanner.nextLine();
        while (!(IM.aucontroller.register(username, password, code, IM.wu))) {
            System.out.println("Wrong input of username/password/code, please try again.");
            System.out.println("Please enter your username (should only contains numbers and letter):");
            username = scanner.nextLine();
            System.out.println("Please enter your password (should only contains numbers and letter):");
            password = scanner.nextLine();
            System.out.println("Please enter your administration code:");
            code = scanner.nextLine();
        }
        return Arrays.asList(username, password, code);
    }

    public static List<String> login(Scanner scanner, InstanceMain IM){
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
        return Arrays.asList(username, password, code);
    }

    public static void upload_movie(Scanner scanner, InstanceMain IM){
        // now admin can upload movie or delete movie
        System.out.println("Enter 'Upload movie'.");
        String choice = scanner.nextLine();

        String[] option= new String[]{"Upload movie"};
        List<String> options = new ArrayList<>(Arrays.asList(option));
        while (!options.contains(choice)) {
            System.out.println("Please reenter 'Upload movie':");
            choice = scanner.nextLine();
        }

        if (choice.equals("Upload movie")){
            System.out.println("Please enter the name of the movie");
            String movie_name = scanner.nextLine();
            System.out.println("Please enter the link of the movie");
            String movie_link = scanner.nextLine();
            while (!(IM.aucontroller.uploadMovie(movie_name, movie_link, IM.wm))) {
                System.out.println("invalid inputs, please try again: ");
                System.out.println("Please enter the name of the movie");
                movie_name = scanner.nextLine();
                System.out.println("Please enter the link of the movie");
                movie_link = scanner.nextLine();
            }

            IM.aucontroller.uploadMovie(movie_name, movie_link, IM.wm);
            System.out.println("Core.Movie added");
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
    }
}
