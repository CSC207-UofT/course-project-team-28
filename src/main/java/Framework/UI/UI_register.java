package Framework.UI;

import InterfaceAdapter.InstanceMain;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UI_register {

    public static void main(Scanner scanner) throws IOException {
        List<String> result = user_helper.register(scanner);
        String userName = result.get(0);
        String password = result.get(1);

        InstanceMain.getNormalCUser().setCurrNormalName(userName);
        InstanceMain.getNormalCMovie().setCurrNormalName(userName);
        InstanceMain.getNormalCCoin().setCurrNormalName(userName);

        if(InstanceMain.getNormalCUser().login(userName, password)) {
            System.out.println("Account successfully created, you are automatically logged in.");
        }


        user_helper.ui_user_body(scanner, userName);
    }
}
