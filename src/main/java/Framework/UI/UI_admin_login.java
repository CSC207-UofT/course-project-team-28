package Framework.UI;

import InterfaceAdapter.Controller.InstanceMain;

import java.util.Scanner;

public class UI_admin_login {

    public static void main(Scanner scanner, InstanceMain IM) {
        admin_helper.login(scanner, IM);
        System.out.println("Login successful.");

        admin_helper.upload_movie(scanner, IM);
    }
}
