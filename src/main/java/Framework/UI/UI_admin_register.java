package Framework.UI;

import InterfaceAdapter.InstanceMain;

import java.util.List;
import java.util.Scanner;

public class UI_admin_register {

    public static void main(Scanner scanner) {

        List<String> result = admin_helper.register(scanner);
        String userName = result.get(0);
        String password = result.get(1);
        String code = result.get(2);

        InstanceMain.getNormalCUser().setCurrNormalName(userName);
        InstanceMain.getNormalCMovie().setCurrNormalName(userName);
        InstanceMain.getNormalCCoin().setCurrNormalName(userName);

        if(InstanceMain.getAdminInputProcessor().login(userName, password, code)) {
            System.out.println("Admin account successfully created, you are automatically logged in.");
        }
        admin_helper.upload_movie(scanner);
    }

}
