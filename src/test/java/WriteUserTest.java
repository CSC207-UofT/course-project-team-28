import Entity.User.AdminUser;
import Entity.User.NormalUser;
import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WriteUserTest {

    private static Object[] infoList;
    private static Object[] auInfoList;
    private static ArrayList<Object> fileData;
    Object[] playList1;
    Object[] playList2;

    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUpBeforeClass() {
        infoList = new Object[8];
        infoList[0] = "Nico";
        infoList[1] ="123";
        infoList[2] ="Empty contact info";
        infoList[3] ="Empty description";
        infoList[4] ="Empty category";
        infoList[5] ="300";
        infoList[6] ="[]";
        infoList[7] ="/src/main/res/GUIPic/winnie.jpg";

        auInfoList = new Object[3];
        auInfoList[0] = "Ella";
        auInfoList[1] = "12";
        auInfoList[2] = "/src/main/res/GUIPic/shake hand.jpg";


        //InstanceMain setUp
        WriteUser wu = new WriteUser(str1 + "/src/test/res/NormalUser", str1 +
                "/src/test/res/AdminUser");
        WriteReview wr = new WriteReview();
        WriteMovie wm = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(wu, wm, wr);

        //save original file data
        fileData = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "WriteUser.txt");

    }
    @AfterClass
    public static void tearDown(){
        //Restore the file to its original state
        InstanceMain.getWriteUser().writeFile(str1 + "/src/test/res/NormalUser/" + "WriteUser.txt", fileData);
    }

    @Before
    public void setUp() {
        playList2 = new Object[]{"Apple", "Candy", "Team28", "Happy Life", "Water", "Banana"};
        playList1 = new Object[]{"Apple", "Candy", "Team28", "Happy Life"};
    }

    @Test
    public void createFileNu() {
        try{
            assertTrue(InstanceMain.getWriteUser().createFile("Nico", "123",
                    "NormalUser"));

            assertTrue(fileContentTest(infoList, InstanceMain.getWriteUser().readFile(str1 +
                    "/src/test/res/NormalUser/" + "Nico" + ".txt")));

            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/NormalUser/" + "Nico" + ".txt")));

        }
        catch (IOException e){
            System.out.println("create file test for NormalUser fails");
        }

    }

    @Test
    public void createFileAu() {
        try{
            assertTrue(InstanceMain.getWriteUser().createFile("Ella", "12",
                    "AdminUser"));


            assertTrue(fileContentTest(auInfoList, InstanceMain.getWriteUser().readFile(str1 +
                    "/src/test/res/AdminUser/" + "Ella" + ".txt")));

            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/AdminUser/" + "Ella" + ".txt")));
        }
        catch (IOException e){
            System.out.println("create file test for AdminUser fails");
        }

    }

    @Test
    public void getObjectFromFile() {

         ArrayList<AdminUser> auList = InstanceMain.getUserManager().getAdminUserList();
         assertEquals(2, auList.size());

         Object[] userFile = {"AdminController", "WriteUser"};

         for (int i = 0; i < 2; i++){
             assertEquals(auList.get(i).getUsername(), userFile[i]);
         }
    }

    @Test
    public void getNormalUserFromFile() {

        ArrayList<NormalUser> nuList = InstanceMain.getUserManager().getNormalUserList();
        assertEquals(8, nuList.size());

        Object[] userFile = {"CoinManager", "MovieManager", "NormalController", "ReviewManager1", "ReviewManager2",
                "ReviewManager3", "UserManager", "WriteUser"};

        for (int i = 0; i < 8; i++){
            assertEquals(nuList.get(i).getUsername(), userFile[i]);
        }

    }

    @Test
    public void givelikeReadAndWrite() {
        assertTrue(InstanceMain.getWriteUser().givelikeReadAndWrite("Water", "WriteUser"));
        assertTrue(InstanceMain.getWriteUser().givelikeReadAndWrite("Banana", "WriteUser"));

        ArrayList<Object> lst1  = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" +
                "WriteUser.txt");
        ArrayList<Object> pl2;

        lst1.set(6, lst1.get(6).toString().replace("[", "")); //get rid of "[" in playlist
        lst1.set(6, lst1.get(6).toString().replace("]", "")); //get rid of "]" in playlist
        String[] pl1 = lst1.get(6).toString().split(","); // change playlist from string to array
        pl2 = new ArrayList<>(Arrays.asList(pl1)); // change playlist from array to arraylist


        assertTrue(fileContentTest(playList2, pl2));


        InstanceMain.getWriteUser().undoLikeReadAndWrite("Water", "WriteUser");
        InstanceMain.getWriteUser().undoLikeReadAndWrite("Banana", "WriteUser");
    }

    @Test
    public void undoLikeReadAndWrite() {
        InstanceMain.getWriteUser().undoLikeReadAndWrite("Water", "WriteUser");
        InstanceMain.getWriteUser().undoLikeReadAndWrite("Banana", "WriteUser");

        ArrayList<Object> lst2  = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" +
                "WriteUser.txt");
        ArrayList<Object> pl3;

        lst2.set(6, lst2.get(6).toString().replace("[", "")); //get rid of "[" in playlist
        lst2.set(6, lst2.get(6).toString().replace("]", "")); //get rid of "]" in playlist
        String[] pl4 = lst2.get(6).toString().split(","); // change playlist from string to array
        pl3 = new ArrayList<>(Arrays.asList(pl4)); // change playlist from array to arraylist


        assertTrue(fileContentTest(playList1, pl3));

        assertTrue(InstanceMain.getWriteUser().givelikeReadAndWrite("Water", "WriteUser"));
        assertTrue(InstanceMain.getWriteUser().givelikeReadAndWrite("Banana", "WriteUser"));


    }

    @Test
    public void editProfile() {
        assertTrue(InstanceMain.getWriteUser().editProfileReadAndWrite("WriteUser", "647",
                "contactInfo"));
        ArrayList<Object> data = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" +
                "WriteUser.txt");
        assertEquals(data.get(2), "647");

        assertTrue(InstanceMain.getWriteUser().editProfileReadAndWrite("WriteUser", "51129",
                "contactInfo"));
        data = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "WriteUser.txt");
        assertEquals(data.get(2), "51129");

        assertTrue(InstanceMain.getWriteUser().editProfileReadAndWrite("WriteUser", "I like Anime",
                "description"));
        data = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "WriteUser.txt");
        assertEquals(data.get(3), "I like Anime");

        assertTrue(InstanceMain.getWriteUser().editProfileReadAndWrite("WriteUser", "I like Movie",
                "description"));
        data = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "WriteUser.txt");
        assertEquals(data.get(3), "I like Movie");

        assertTrue(InstanceMain.getWriteUser().editProfileReadAndWrite("WriteUser", "Action",
                "category"));
        data = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "WriteUser.txt");
        assertEquals(data.get(4), "Action");

        assertTrue(InstanceMain.getWriteUser().editProfileReadAndWrite("WriteUser", "Romantic",
                "category"));
        data = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "WriteUser.txt");
        assertEquals(data.get(4), "Romantic");

        assertTrue(InstanceMain.getWriteUser().editProfileReadAndWrite("WriteUser", "2",
                "coin"));
        data = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "WriteUser.txt");
        assertEquals(data.get(5), "302");

        assertTrue(InstanceMain.getWriteUser().editProfileReadAndWrite("WriteUser", "-2",
                "coin"));
        data = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "WriteUser.txt");
        assertEquals(data.get(5), "300");


    }



    private boolean fileContentTest(Object[] ul, ArrayList<Object> infoList) {
        for (int i = 0; i < ul.length; i++){
            if (!infoList.get(i).equals(ul[i].toString()))
                return false;
        }
        return true;
    }


}