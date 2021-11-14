public class InstanceMain {
    //controller
    public final NormalCUser ncu = new NormalCUser();
    public final NormalCCoin ncc = new NormalCCoin();
    public final NormalCMovie ncm = new NormalCMovie();
    public final AdminInputProcessor aucontroller = new AdminInputProcessor();

    //use case
    public final UserManager um = new UserManager();
    public final MovieManager mm = new MovieManager();
    public final ReviewManager rm = new ReviewManager(mm);
    public final CoinManager cm = new CoinManager(um, rm);

    //gateway
    public final WriteUser wu = new WriteUser(ncu, ncc, ncm, aucontroller, um);
    public final WriteMovie wm = new WriteMovie(ncu, ncc, ncm, aucontroller, mm);
    public final WriteReview wr = new WriteReview(ncu, ncc, ncm, rm);

    public InstanceMain(){
        ncu.setCoinMana(cm);
    }


}
