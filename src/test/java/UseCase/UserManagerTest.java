package UseCase;

import Entity.User.AdminUser;
import Entity.User.NormalUser;
import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserManagerTest {
    private static Object[] infoList;
    private static final ArrayList<String> playList1 = new ArrayList<>();
    private static ArrayList<Object> fileData;

    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUpBeforeClass() {
        List<String> list = Arrays.asList("Apple", "Candy", "Team28", "Happy Life");

        infoList = new Object[8];
        infoList[0] = "UserManager";
        infoList[1] ="12";
        infoList[2] ="Empty contact info - um";
        infoList[3] ="Empty description - um";
        infoList[4] ="Empty category - um";
        infoList[5] = "300";
        infoList[6] = list;
        infoList[7] ="/src/main/res/GUIPic/paint.jpg";

        //InstanceMain setUp
        InstanceMain.setClearInstance();
        WriteUser wu = new WriteUser(str1 + "/src/test/res/NormalUser", str1 +
                "/src/test/res/AdminUser");
        WriteReview wr = new WriteReview();
        WriteMovie wm = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(wu, wm, wr);

        //save original file data
        fileData = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "UserManager.txt");

    }
    @After
    public void tearDown(){
        //Restore the file to its original state
        InstanceMain.getWriteUser().writeFile(str1 + "/src/test/res/NormalUser/" + "UserManager.txt", fileData);
    }


    @Test
    public void createAdminuser() {
        ArrayList<AdminUser> adminUser = InstanceMain.getUserManager().getAdminUserList();
        int originalSize = adminUser.size();
        InstanceMain.getUserManager().createAdminuser("Nico", "123");
        adminUser = InstanceMain.getUserManager().getAdminUserList();
        assertEquals(originalSize + 1,  adminUser.size());

        Object[] getUserInfo = InstanceMain.getUserManager().getUserInfoList("Nico", "AdminUser");
        Object[] userInfo = new Object[]{"Nico", "123"};
        assertTrue(fileContentTest(userInfo, getUserInfo));

    }

    @Test
    public void createNormaluser() {
        ArrayList<NormalUser> normalUser = InstanceMain.getUserManager().getNormalUserList();
        int originalSize = normalUser.size();
        InstanceMain.getUserManager().createNormaluser("Jac", "123", "511",
                "Anime is the best", "Romantic", 300, playList1,
                "/src/main/res/GUIPic/paint.jpg");
        normalUser = InstanceMain.getUserManager().getNormalUserList();
        assertEquals(originalSize + 1,  normalUser.size());

        Object[] getUserInfo = InstanceMain.getUserManager().getUserInfoList("Jac", "NormalUser");
        Object[] userInfo = new Object[]{"Jac", "123", "511", "Anime is the best", "Romantic", 300, playList1,
                "/src/main/res/GUIPic/paint.jpg"};
        assertTrue(fileContentTest(userInfo, getUserInfo));

    }

    @Test
    public void createNewAdminuser() throws IOException {
        ArrayList<AdminUser> adminUser = InstanceMain.getUserManager().getAdminUserList();
        int originalSize = adminUser.size();
        InstanceMain.getUserManager().createNewAdminuser("Nico", "123");
        adminUser = InstanceMain.getUserManager().getAdminUserList();
        assertEquals(originalSize + 1,  adminUser.size());

        Object[] getUserInfo = InstanceMain.getUserManager().getUserInfoList("Nico", "AdminUser");
        Object[] userInfo = new Object[]{"Nico", "123"};
        assertTrue(fileContentTest(userInfo, getUserInfo));

        assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/AdminUser/" + "Nico" + ".txt")));
    }

    @Test
    public void createNewNormaluser() throws IOException {
        ArrayList<NormalUser> normalUser = InstanceMain.getUserManager().getNormalUserList();
        int originalSize = normalUser.size();
        InstanceMain.getUserManager().createNewNormaluser("Nico", "123");
        normalUser = InstanceMain.getUserManager().getNormalUserList();
        assertEquals(originalSize + 1,  normalUser.size());

        Object[] getUserInfo1 = InstanceMain.getUserManager().getUserInfoList("Nico", "NormalUser");
        Object[] userInfo1 = new Object[]{"Nico", "123", "Empty contact info", "Empty description" ,"Empty category",
                300, new ArrayList<>(), "/src/main/res/GUIPic/paint.jpg"};
        getUserInfo1[7] = "/src/main/res/GUIPic/paint.jpg";
        assertTrue(fileContentTest(userInfo1, getUserInfo1));
        getUserInfo1[7] = "/src/main/res/GUIPic/shake hand.jpg";

        assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/NormalUser/" + "Nico" + ".txt")));
    }

    @Test
    public void updateInfo() {
        InstanceMain.getUserManager().updateInfo("UserManager", "511","contactInfo");
        InstanceMain.getUserManager().updateInfo("UserManager", "I like anime",
                "description");
        InstanceMain.getUserManager().updateInfo("UserManager", "Action","category");
        InstanceMain.getUserManager().updateInfo("UserManager", "/src/main/res/GUIPic/paint.jpg",
                "picPath");

        Object[] getUserInfo = InstanceMain.getUserManager().getUserInfoList("UserManager",
                "NormalUser");
        ArrayList<String> lst = new ArrayList<>(List.of(new String[]{"Apple", "Candy", "Team28", "Happy Life"}));

        Object[] userInfo = new Object[]{"UserManager", "12", "511", "I like anime" ,"Action", 300, lst,
                "/src/main/res/GUIPic/paint.jpg"};
        assertTrue(fileContentTest(userInfo, getUserInfo));

        InstanceMain.getUserManager().updateInfo("UserManager", "Empty contact info - um",
                "contactInfo");
        InstanceMain.getUserManager().updateInfo("UserManager", "Empty description - um",
                "description");
        InstanceMain.getUserManager().updateInfo("UserManager", "Empty category - um",
                "category");
        InstanceMain.getUserManager().updateInfo("UserManager", "/src/main/res/GUIPic/winnie.jpg",
                "picPath");
    }

    @Test
    public void updateCoin() {
        InstanceMain.getUserManager().updateCoin("UserManager", 2);
        Object[] getUserInfo1 = InstanceMain.getUserManager().getUserInfoList("UserManager",
                "NormalUser");
        assertEquals(302, getUserInfo1[5]);

        InstanceMain.getUserManager().updateCoin("UserManager", -2);
        Object[] getUserInfo2 = InstanceMain.getUserManager().getUserInfoList("UserManager",
                "NormalUser");
        assertEquals(300, getUserInfo2[5]);


    }

    @Test
    @SuppressWarnings("unchecked")
    public void giveLike() {
        InstanceMain.getUserManager().giveLike("UserManager", "Water");
        Object[] lst = InstanceMain.getUserManager().getUserInfoList("UserManager", "NormalUser");
        ArrayList<String> lst2 = (ArrayList<String>) lst[6];
        assertTrue(lst2.contains("Water"));
        InstanceMain.getUserManager().undoLike("UserManager", "Water");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void undoLike() {
        assertTrue(InstanceMain.getUserManager().undoLike("UserManager", "Happy Life"));
        Object[] lst = InstanceMain.getUserManager().getUserInfoList("UserManager", "NormalUser");
        ArrayList<String> lst2 = (ArrayList<String>) lst[6];
        assertFalse(lst2.contains("Happy Life"));
        InstanceMain.getUserManager().giveLike("UserManager", "Happy Life");
    }

    @Test
    public void getUserInfoList() {
        Object[] lst = InstanceMain.getUserManager().getUserInfoList("UserManager", "NormalUser");
        lst[5] = lst[5].toString();
        infoList[7] = "/src/main/res/GUIPic/winnie.jpg";
        assertTrue(fileContentTest(lst, infoList));
        infoList[7] = "/src/main/res/GUIPic/paint.jpg";
    }


    @Test
    public void userIfExist() {
        assertTrue(InstanceMain.getUserManager().userIfExist("UserManager", "12",
                "NormalUser"));
        assertTrue(InstanceMain.getUserManager().userIfExist("WriteUser", "12",
                "AdminUser"));
        assertFalse(InstanceMain.getUserManager().userIfExist("NormalUser","123",
                "NormalUser"));
        assertFalse(InstanceMain.getUserManager().userIfExist("Ella", "12","AdminUser"));
    }

    @Test
    public void usernameIfUnique() {
        assertTrue(InstanceMain.getUserManager().usernameIfUnique("Ella","NormalUser"));
        assertTrue(InstanceMain.getUserManager().usernameIfUnique("Jac","AdminUser"));
        assertFalse(InstanceMain.getUserManager().usernameIfUnique("UserManager","NormalUser"));
        assertFalse(InstanceMain.getUserManager().usernameIfUnique("WriteUser","AdminUser"));
    }

    @Test
    public void checkCoinBiggerThanOne() {
        assertTrue(InstanceMain.getUserManager().checkCoinBiggerThanOne("UserManager"));
    }


    private boolean fileContentTest(Object[] ul, Object[] infoList) {
        for (int i = 0; i < ul.length; i++){
            if (!infoList[i].toString().equals(ul[i].toString()))
                return false;
        }
        return true;
    }
}