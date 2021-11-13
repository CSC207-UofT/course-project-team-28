public class InstanceMain {
    //controller
    public final NormalInputProcessor nucontroller = new NormalInputProcessor();
    public final AdminInputProcessor aucontroller = new AdminInputProcessor();

    //use case
    public final UserManager um = new UserManager("");
    public final MovieManager mm = new MovieManager("");
    public final ReviewManager rm = new ReviewManager("", mm);
//    public final CoinManager cm = new CoinManager(um, rm);

    //gateway
    public final WriteUser wu = new WriteUser(nucontroller, aucontroller, um);
    public final WriteMovie wm = new WriteMovie(nucontroller, aucontroller, mm);
    public final WriteReview wr = new WriteReview(nucontroller, rm);

//    public InstanceMain(){
//        nucontroller.setCoinMana(cm);
//    }
}
