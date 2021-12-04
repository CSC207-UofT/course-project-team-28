import Entity.User.AdminUser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminUserTest {

    private AdminUser au;

    @Before
    public void setUpBeforeMethod(){
        au = new AdminUser("TestAdminUser","12", "");
    }

    @Test
    public void getObject() {
        assertEquals(2, au.getObject().length);
    }

    @Test
    public void getObjectContent() {
        Object[] auInfo = au.getObject();

        assertEquals("TestAdminUser", auInfo[0]);
        assertEquals("12", auInfo[1]);
    }

    @Test
    public void getUsername() {
        assertEquals("TestAdminUser", au.getUsername());
    }

    @Test
    public void getUserPassword() {
        assertEquals("12", au.getUserPassword());
    }
}