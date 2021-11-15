import org.junit.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class WriteUserTest {

    //use case


    //gateway
    public static WriteUser wu;
    private static UserManager um;
    ArrayList<String> playList;

    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();


    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() {
        //controller
        NormalCUser ncu = new NormalCUser();
        NormalCCoin ncc = new NormalCCoin();
        NormalCMovie ncm = new NormalCMovie();
        AdminInputProcessor aucontroller = new AdminInputProcessor();
        um = new UserManager();

        playList = new ArrayList<>(Arrays.asList("Apple", "Banana", "Candy", "Happy Life", "Team28", "Water"));
        wu = new WriteUser(str1 + "/src/test/res/NormalUser", str1 + "/src/test/res/AdminUser", ncu, ncc, ncm, aucontroller, um);
    }

    @Test
    public void createFileNu() {
        try{
            assertTrue(wu.createFile("Nico", "123", "NormalUser"));

            assertTrue(um.userIfExist("Nico", "123","NormalUser"));

            assertTrue(fileContentTest(um.getUserInfoList("Nico", "NormalUser"), wu.readFile(str1 + "/src/test/res/NormalUser/" + "Nico" + ".txt")));

            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/NormalUser/" + "Nico" + ".txt")));

        }
        catch (IOException e){
            System.out.println("create file test for NormalUser fails");
        }

    }

    @Test
    public void createFileAu() {
        try{
            assertTrue(wu.createFile("Ella", "12", "AdminUser"));

            assertTrue(um.userIfExist("Ella", "12","AdminUser"));

            assertTrue(fileContentTest(um.getUserInfoList("Ella", "AdminUser"), wu.readFile(str1 + "/src/test/res/AdminUser/" + "Ella" + ".txt")));

            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/AdminUser/" + "Ella" + ".txt")));
        }
        catch (IOException e){
            System.out.println("create file test for AdminUser fails");
        }

    }

    @Test
    public void getObjectFromFile() {

         ArrayList<AdminUser> auList = um.getAdminUserList();
         assertEquals(10, auList.size());

         Object[] userFile = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

         for (int i = 0; i < 10; i++){
             assertEquals(auList.get(i).getUsername(), userFile[i]);
         }
    }

    @Test
    public void getNormalUserFromFile() {

        ArrayList<NormalUser> nuList = um.getNormalUserList();
        assertEquals(10, nuList.size());

        Object[] userFile = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

        for (int i = 0; i < 10; i++){
            assertEquals(nuList.get(i).getUsername(), userFile[i]);
        }

    }

    @Test
    public void givelikeReadAndWrite() {
        String nuPlayLists = "";
        for (int i = 0; i < 6; i++){
            nuPlayLists = wu.givelikeReadAndWrite(playList.get(i), "a");
        }
        nuPlayLists = nuPlayLists.replace("[","");//For playlist String. get rid of "[" and "]"
        nuPlayLists = nuPlayLists.replace("]","");
        String[] nuPlayList = nuPlayLists.split(",");
        assertEquals(Arrays.toString(nuPlayList), playList.toString());

        for (String movie: playList){
            wu.undoLikeReadAndWrite(movie, "a");
        }
    }

    @Test
    public void undoLikeReadAndWrite() {
        String nuPlayLists = "";
        for (int i = 0; i < 6; i++){
            nuPlayLists = wu.givelikeReadAndWrite(playList.get(i), "a");
        }

        for (String movie: playList){
            nuPlayLists = wu.undoLikeReadAndWrite(movie, "a");
        }
        assertEquals(nuPlayLists, "[]");
    }

    @Test
    public void editProfile() {
        assertTrue(wu.editProfileReadAndWrite("236", "c", "contactInfo"));
        assertTrue(wu.editProfileReadAndWrite("Empty ContactInfo", "c", "contactInfo"));

        assertTrue(wu.editProfileReadAndWrite("I like movie", "c", "description"));
        assertTrue(wu.editProfileReadAndWrite("Empty description", "c", "description"));

        assertTrue(wu.editProfileReadAndWrite("Anime", "c", "category"));
        assertTrue(wu.editProfileReadAndWrite("Empty category", "c", "category"));

        assertTrue(wu.editProfileReadAndWrite("2", "c", "coin"));
        assertTrue(wu.editProfileReadAndWrite("-2", "c", "coin"));


    }



    public boolean fileContentTest(Object[] ul, ArrayList<Object> infoList) {
        for (int i = 0; i < ul.length; i++){
            if (!infoList.get(i).equals(ul[i].toString()))
                return false;
        }
        return true;
    }


}