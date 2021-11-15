import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    private Review rev;

    @Before
    public void setUpBeforeMethod(){
        rev = new Review("TestReviewer","TestMovie","TestRevContent",300,1);
    }

    @Test
    public void testToString(){
        assertEquals("ID: 1, for movie TestMovie - TestReviewer with 300 coins: TestRevContent",
                rev.toString());
    }
}
