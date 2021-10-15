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





