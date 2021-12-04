package Framework.UI;

import InterfaceAdapter.InstanceMain;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UI_login {
    public static void main(Scanner scanner) throws IOException {
        List<String> result = user_helper.login(scanner);
        String userName = result.get(0);


        InstanceMain.getNormalCUser().setCurrNormalName(userName);
        InstanceMain.getNormalCMovie().setCurrNormalName(userName);
        InstanceMain.getNormalCCoin().setCurrNormalName(userName);

        System.out.println("Login successful.");

        user_helper.ui_user_body(scanner, userName);
    }
}
