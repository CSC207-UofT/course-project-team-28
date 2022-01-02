package InterfaceAdapter.Controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NormalControllerTest {
    private NormalController nc;

    @Before
    public void setUpNc() {
        nc = new NormalCCoin();
    }

    @Test
    public void pass1() {
        assertTrue(nc.isNonEmptyAlphanumeric("1a"));
    }

    @Test
    public void pass2() {
        assertTrue(nc.isNonEmptyAlphanumeric("1GSKNskj28a"));
    }

    @Test
    public void emptyString() {
        assertFalse(nc.isNonEmptyAlphanumeric(""));
    }

    @Test
    public void ContainOtherChar() {
        assertFalse(nc.isNonEmptyAlphanumeric("qwcdaCG4/"));
    }

    @Test
    public void ContainSpace() {
        assertFalse(nc.isNonEmptyAlphanumeric("1GSK Nskj  28a"));
    }


}