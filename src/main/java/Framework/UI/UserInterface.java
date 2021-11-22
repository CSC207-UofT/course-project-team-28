package Framework.UI;

import InterfaceAdapter.Controller.InstanceMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A user interface which interacts with users to prompt to get input from them.
 */

public class UserInterface {
    private static final InstanceMain IM = new InstanceMain();

    public static void main(String[] args) throws IOException {
        // Core.User chooses to register/login as normal or admin user
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
            UI_register.main(scanner, IM);

            // if user chooses to log in as normal user
        }else if (choice.equals("Login")){
            UI_login.main(scanner, IM);

            // If user chooses to register as admin, a fixed administration code is needed.
        }else if (choice.equals("Admin register")){
            UI_admin_register.main(scanner, IM);


            //if user chooses to log in as admin user.
        }else if (choice.equals("Admin login")){
            UI_admin_login.main(scanner, IM);

        }else {
            System.out.println("wrong input");
            System.out.println("program exits.");
            System.exit(1);
        }
    }
}