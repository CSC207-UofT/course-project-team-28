import java.util.List;
import java.util.Scanner;

public class UI_register {

    public static void main(Scanner scanner, InstanceMain IM) {
        List<String> result = user_helper.register(scanner, IM);
        String username = result.get(0);
        String password = result.get(1);
        IM.wu.create_file(username, password, "NormalUser");
        if(IM.nucontroller.login(username, password)) {
            System.out.println("Account successfully created, you are automatically logged in.");
        }


        user_helper.ui_user_body(scanner, IM, username);
    }
}
