import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<AdminUser> lstOfAdminUser;
    private List<NormalUser> lstOfNormalUser;

    public UserManager(){
        lstOfAdminUser = new ArrayList<>();
        lstOfNormalUser = new ArrayList<>();
    }

    public void update_info(String new_name, String new_password){
        NormalUser u = new NormalUser(new_name, new_password);


    }


}
