package Entity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    private Movie movie;

    @Before
    public void setUpBeforeTest() {
        movie = new Movie("Peach", "asduip", 0, "Romantic");
    }

    @SuppressWarnings("TextBlockMigration")
    @Test
    public void testToString() {
        assertEquals("Movie name: Peach" + ", \n" + "Link: asduip" + ", \n" + "Category: Romantic" + ", \n"
                + "# of Likes: 0", movie.toString());
    }

    @Test
    public void testGetMovieName() {
        assertEquals("Peach", movie.getMovieName());
    }

    @Test
    public void testGetLink() {
        assertEquals("asduip", movie.getLink());
    }

    @Test
    public void testGetLikes() {
        assertEquals(0, movie.getLikes());
    }

    @Test
    public void testCategory() {
        assertEquals("Romantic", movie.getCategory());
    }

    @Test
    public void testAddLike() {
        movie.AddLike();
        assertEquals(1, movie.getLikes());
    }

    @Test
    public void testUndoLike() {
        movie.UndoLike();
        assertEquals(-1, movie.getLikes());
    }
}
