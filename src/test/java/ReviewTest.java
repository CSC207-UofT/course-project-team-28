import Entity.Review;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    private Review rev;

    @Before
    public void setUpBeforeMethod(){
        rev = new Review("TestReviewer","TestMovie",
                "TestRevContent",300,10);
    }

    @Test
    public void testToString(){
        assertEquals("ID: 10, for movie TestMovie - TestReviewer with 300 coins: TestRevContent",
                rev.toString());
    }

    @Test
    public void testGetReviewer(){
        assertEquals("TestReviewer", rev.getReviewer());
    }

    @Test
    public void testGetMovie(){
        assertEquals("TestMovie", rev.getMovie());
    }

    @Test
    public void testGetContent(){
        assertEquals("TestRevContent", rev.getContent());
    }

    @Test
    public void testGetNumCoin(){
        assertEquals(300, rev.getNumCoin());
    }

    @Test
    public void testGetID(){
        assertEquals(10, rev.getID());
    }

    @Test
    public void testGetReviewInfo(){
        Object[] result1 = new Object[]{"TestReviewer", "TestMovie", "TestRevContent",300,10};
        Object[] result2 = rev.getReviewInfo();
        for (int index = 0; index <= 4; index++){
            assertEquals(result1[index], result2[index]);
        }
        assertEquals(result1.length, result2.length);

    }
}
