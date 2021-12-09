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
        private static NormalCUser NORMALCUSER;
        private static NormalCCoin NORMALCCOIN;
        private static NormalCMovie NORMALCMOVIE;
        private static AdminInputProcessor ADMININPUTPROCESSOR;

        //use case instance
        private static UserManager USERMANAGER;
        private static ReviewManager REVIEWMANAGER;
        private static MovieManager MOVIEMANAGER;
        private static CoinManager COINMANAGER;

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
     * Reinitialize the instance variable
     */
    public static void setClearInstance(){
        InstanceMainHolder.USERMANAGER = new UserManager();
        InstanceMainHolder.REVIEWMANAGER = new ReviewManager();
        InstanceMainHolder.MOVIEMANAGER = new MovieManager(InstanceMainHolder.REVIEWMANAGER);
        InstanceMainHolder.COINMANAGER = new CoinManager(InstanceMainHolder.USERMANAGER, InstanceMainHolder.REVIEWMANAGER);

        InstanceMainHolder.NORMALCUSER = new NormalCUser();
        InstanceMainHolder.NORMALCCOIN = new NormalCCoin();
        InstanceMainHolder.NORMALCMOVIE = new NormalCMovie();
        InstanceMainHolder.ADMININPUTPROCESSOR = new AdminInputProcessor();


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
