package Framework.UI;

import InterfaceAdapter.Controller.InstanceMain;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UI_login {
    public static void main(Scanner scanner, InstanceMain IM) throws IOException {
        List<String> result = user_helper.login(scanner, IM);
        String username = result.get(0);

        IM.ncu.setCurrNuname(username);
        IM.ncm.setCurrNuname(username);
        IM.ncc.setCurrNuname(username);


        System.out.println("Login successful.");

        user_helper.ui_user_body(scanner, IM, username);
    }
}
