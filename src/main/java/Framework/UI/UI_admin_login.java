package Framework.UI;

import InterfaceAdapter.InstanceMain;

import java.util.Scanner;

public class UI_admin_login {

    public static void main(Scanner scanner) {
        admin_helper.login(scanner);
        System.out.println("Login successful.");

        admin_helper.upload_movie(scanner);
    }
}
