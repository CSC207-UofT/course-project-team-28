package InterfaceAdapter;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.Controller.NormalCCoin;
import InterfaceAdapter.Controller.NormalCMovie;
import InterfaceAdapter.Controller.NormalCUser;
import UseCase.CoinManager;
import UseCase.MovieManager;
import UseCase.ReviewManager;
import UseCase.UserManager;


/**
 * A static class that stores all data access instances, Controller instances and use case instances.
 * If other class want to interact with those classes, they must use getter method in this class to get the instance they need.
 */
public class InstanceMain {

    private InstanceMain(){}


    private static class InstanceMainHolder {
        //data access instance
        private static WriteUser WRITEUSER;
        private static WriteMovie WRITEMOVIE;
        private static WriteReview WRITEREVIEW;

        //Controller instance
        private static final NormalCUser NORMALCUSER = new NormalCUser();
        private static final NormalCCoin NORMALCCOIN = new NormalCCoin();
        private static final NormalCMovie NORMALCMOVIE = new NormalCMovie();
        private static final AdminInputProcessor ADMININPUTPROCESSOR = new AdminInputProcessor();

        //use case instance
        private static final UserManager USERMANAGER = new UserManager();
        private static final ReviewManager REVIEWMANAGER = new ReviewManager();
        private static final MovieManager MOVIEMANAGER = new MovieManager(REVIEWMANAGER);
        private static final CoinManager COINMANAGER = new CoinManager(USERMANAGER, REVIEWMANAGER);

    }

    //getter method for all static variable
    public static AdminInputProcessor getAdminInputProcessor(){
        return InstanceMainHolder.ADMININPUTPROCESSOR;
    }
    public static WriteUser getWriteUser(){
        return InstanceMainHolder.WRITEUSER;
    }
    public static WriteMovie getWriteMovie(){
        return InstanceMainHolder.WRITEMOVIE;
    }
    public static WriteReview getWriteReview(){
        return InstanceMainHolder.WRITEREVIEW;
    }

    public static NormalCUser getNormalCUser(){
        return InstanceMainHolder.NORMALCUSER;
    }

    public static NormalCCoin getNormalCCoin(){
        return InstanceMainHolder.NORMALCCOIN;
    }

    public static NormalCMovie getNormalCMovie(){
        return InstanceMainHolder.NORMALCMOVIE;
    }

    public static UserManager getUserManager(){
        return InstanceMainHolder.USERMANAGER;
    }

    public static MovieManager getMovieManager(){
        return InstanceMainHolder.MOVIEMANAGER;
    }

    public static ReviewManager getReviewManager(){
        return InstanceMainHolder.REVIEWMANAGER;
    }

    public static CoinManager getCoinManager(){
        return InstanceMainHolder.COINMANAGER;
    }


    /**
     * setter method for three variables of data access class, this aim to make auto-call of data access class
     * @param writeUser the instance of WriteUser
     * @param writeMovie the instance of WriteMovie
     * @param writeReview the instance of WriteReview
     */
    public static void setWriteFileClass(WriteUser writeUser, WriteMovie writeMovie, WriteReview writeReview){
        InstanceMainHolder.WRITEUSER = writeUser;
        InstanceMainHolder.WRITEMOVIE = writeMovie;
        InstanceMainHolder.WRITEREVIEW = writeReview;

    }



}
