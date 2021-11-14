import java.util.List;
import java.util.Scanner;

public class UI_admin_register {

    public static void main(Scanner scanner, InstanceMain IM) {

        List<String> result = admin_helper.login(scanner, IM);
        String userName = result.get(0);
        String password = result.get(1);
        String code = result.get(2);
        IM.wu.create_file(userName, password, "AdminUser");
        if(IM.aucontroller.login(userName, password, code)) {
            System.out.println("Admin account successfully created, you are automatically logged in.");
        }
        admin_helper.upload_movie(scanner, IM);
    }

}
