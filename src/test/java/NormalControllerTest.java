//import InterfaceAdapter.Controller.NormalController;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class NormalControllerTest {
//    private NormalController nc;
//
//    @Before
//    public void setUpNc() {
//        nc = new NormalController();
//    }
//
//    @Test
//    public void pass1() {
//        assertTrue(nc.isNonemptyalphanumeric("1a"));
//    }
//
//    @Test
//    public void pass2() {
//        assertTrue(nc.isNonemptyalphanumeric("1GSKNskj28a"));
//    }
//
//    @Test
//    public void emptyString() {
//        assertFalse(nc.isNonemptyalphanumeric(""));
//    }
//
//    @Test
//    public void ContainOtherChar() {
//        assertFalse(nc.isNonemptyalphanumeric("qwcdaCG4/"));
//    }
//
//    @Test
//    public void ContainSpace() {
//        assertFalse(nc.isNonemptyalphanumeric("1GSK Nskj  28a"));
//    }
//
//
//}