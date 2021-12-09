package Entity;

import Entity.User.NormalUser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class NormalUserTest {

    private NormalUser nu;
    private ArrayList<String> playList;

    @Before
    public void setUpBeforeMethod() {
        playList = new ArrayList<>(Arrays.asList("Apple", "Banana", "Candy", "Happy Life","Team28", "Water"));
        nu = new NormalUser("TestNormalUser", "123", "123456789", "I like movie","Comedy",300, playList, "");
    }


    @Test
    public void getObjectLength() {
        assertEquals(8, nu.getObject().length);
    }

    @Test
    public void getObjectContent() {
        Object[] nuInfo = nu.getObject();

        assertEquals("TestNormalUser", nuInfo[0]);
        assertEquals("123", nuInfo[1]);
        assertEquals("123456789", nuInfo[2]);
        assertEquals("I like movie", nuInfo[3]);
        assertEquals("Comedy", nuInfo[4]);
        assertEquals(300, nuInfo[5]);
        assertEquals(playList, nuInfo[6]);
    }

    @Test
    public void getUsername() {
        assertEquals("TestNormalUser", nu.getUsername());
    }

    @Test
    public void getUserPassword() {
        assertEquals("123", nu.getUserPassword());
    }

    @Test
    public void getContactinfo() {
        assertEquals("123456789", nu.getContactInfo());
    }

    @Test
    public void getPlaylist() {
        assertEquals(playList, nu.getPlaylist());
    }

    @Test
    public void getDescription() {
        assertEquals("I like movie", nu.getDescription());
    }

    @Test
    public void getCoin() {
        assertEquals(300, nu.getCoin());
    }

    @Test
    public void getCategory() {
        assertEquals("Comedy", nu.getCategory());
    }

    @Test
    public void updateContactinfo() {
        nu.updateContactInfo("123456");
        assertEquals("123456", nu.getContactInfo());
    }

    @Test
    public void addMovieToPlaylist() {
        int length = nu.getPlaylist().size();
        nu.addMovieToPlaylist("Sword Art Online");

        assertEquals(length + 1, nu.getPlaylist().size());
        assertTrue(nu.getPlaylist().contains("Sword Art Online"));
    }

    @Test
    public void removeMovieFromPlaylist() {
        int length = nu.getPlaylist().size();
        nu.removeMovieFromPlaylist("Apple");

        assertEquals(length - 1, nu.getPlaylist().size());
        assertNotNull(nu.getPlaylist());
        assertFalse(nu.getPlaylist().contains("Apple"));
    }

    @Test
    public void updateDescription() {
        nu.updateDescription("I like anime");
        assertEquals("I like anime", nu.getDescription());
    }

    @Test
    public void updateCategory() {
        nu.updateCategory("targedy");
        assertEquals("targedy", nu.getCategory());
    }

    @Test
    public void setCoin() {
        nu.setCoin(250);
        assertEquals(250, nu.getCoin());
    }

    @Test
    public void changePic() {
        nu.changePic("123456789");
        assertEquals("123456789", nu.getPicPath());
    }
}