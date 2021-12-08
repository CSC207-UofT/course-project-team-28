package InterfaceAdapter.Controller;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class NormalCUserTest {
    private static NormalCUser ncu;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUp(){
        WriteUser writeUser = new WriteUser(str1 + "/src/test/resNormalControllers/NormalUser", str1 + "/src/test/resNormalControllers/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/resNormalControllers/Review");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/resNormalControllers/Moviedata/", str1 + "/src/test/resNormalControllers/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        ncu = InstanceMain.getNormalCUser();
    }


    @Test
    public void registerInvalidUsername() {
        assertFalse(ncu.register("", "b3jkds"));
    }

    @Test
    public void registerInvalidPassword() {
        assertFalse(ncu.register("Gkn6dks", "bj kds"));
    }

    @Test
    public void registerExistUsername() {
        assertFalse(ncu.register("NormalController", "b3jkds"));
    }

    @Test
    public void registerTrue() {
        assertTrue(ncu.register("newNUser", "dnayro"));
        try {
            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/resNormalControllers/NormalUser/" + "newNUser" + ".txt")));
        }
        catch (IOException e){
            System.out.println("create file test for NormalCUser fails");
        }
    }

    @Test
    public void loginUnmatchedUsernamePassword() {
        assertFalse(ncu.login("NormalController", "12"));
    }

    @Test
    public void loginUserNotExist() {
        assertFalse(ncu.login("randomStuff", "12"));
    }

    @Test
    public void login() {
        assertTrue(ncu.login("ReviewManager1", "12ab"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void profilePageOtherUser() {
        assertTrue(ncu.login("NormalController", "123yu"));
        assertEquals("NormalController", ncu.currNormalName);
        Object[] actual = ncu.profilePage("ReviewManager1");
        ArrayList<String> expectPlaylist = new ArrayList<>();
        expectPlaylist.add("Apple");
        expectPlaylist.add("Team28");
        expectPlaylist.add("Happy Life");
        Object[] expect = {"ReviewManager1", "12ab", "Empty contact info - rm1", "Empty description - rm1",
                "Empty category - rm1", 288, expectPlaylist, "/src/main/res/GUIPic/winnie.jpg"};
        for (int i = 0; i <= 7; i++){
            if (i != 6){ //skip playlist
                assertEquals(expect[i], actual[i]);
            }
        for (int j = 0; j <= 2; j++) {
            assertEquals(((ArrayList<String>) expect[6]).get(j), ((ArrayList<String>) actual[6]).get(j));
            }
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void profilePageCurrentUser() {
        assertTrue(ncu.login("ReviewManager1", "12ab"));
        assertEquals("ReviewManager1", ncu.currNormalName);
        Object[] actual = ncu.profilePage("ReviewManager1");
        ArrayList<String> expectPlaylist = new ArrayList<>();
        expectPlaylist.add("Apple");
        expectPlaylist.add("Team28");
        expectPlaylist.add("Happy Life");
        Object[] expect = {"ReviewManager1", "12ab", "Empty contact info - rm1", "Empty description - rm1",
                "Empty category - rm1", 288, expectPlaylist, "/src/main/res/GUIPic/winnie.jpg"};
        for (int i = 0; i <= 7; i++){
            if (i != 6){ //skip playlist
                assertEquals(expect[i], actual[i]);
            }
            for (int j = 0; j <= 2; j++) {
                assertEquals(((ArrayList<String>) expect[6]).get(j), ((ArrayList<String>) actual[6]).get(j));
            }
        }
    }

    @Test
    public void editProfile() {
        assertTrue(ncu.login("NormalController", "123yu"));
        ncu.editProfile("12345678ContactInfo", "contactInfo");
        assertEquals("12345678ContactInfo", ncu.profilePage("NormalController")[2]);
        ncu.editProfile("test description", "description");
        assertEquals("test description", ncu.profilePage("NormalController")[3]);
    }
}