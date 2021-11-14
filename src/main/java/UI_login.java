import java.util.List;
import java.util.Scanner;

public class UI_login {
    public static void main(Scanner scanner, InstanceMain IM) {
        List<String> result = user_helper.login(scanner, IM);
        String username = result.get(0);
        System.out.println("Login successful.");

        user_helper.ui_user_body(scanner, IM, username);
    }
}
