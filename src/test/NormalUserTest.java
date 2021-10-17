import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class NormalUserTest {
    final private UserManager um = new UserManager();

    public NormalUserTest() throws IOException {
    }

    @Test
    public void TestNormUserExist() throws IOException {
        um.create_normaluser("Mark", "abc123");
        boolean b = um.userIfExist("Mark", "abc123", "NormalUser");
        assertTrue(b);
    }

    @Test
    public void Testgetobject() throws IOException {
        um.create_normaluser("Jack", "abc123");
        Object[] jackgetactual = um.getUserInfoList("Jack", "NormalUser");
        String expect0 = new String("Jack");
        String expect1 = new String("abc123");
        assertEquals(expect0, jackgetactual[0]);
        assertEquals(expect1, jackgetactual[1]);
    }
}
