package Framework.GUI;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;

public class DemoMain {
    public static void main(String[] args) {
        WriteUser writeUser = new WriteUser();
        WriteReview writeReview = new WriteReview();
        WriteMovie writeMovie = new WriteMovie();

        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
//        InstanceMain.getWriteReview().addCoinsToReview(4,200);
//        InstanceMain.getNormalCCoin().currNormalName = "Ella";
//        InstanceMain.getNormalCCoin().giveCoinToRev(4);
        System.out.println(InstanceMain.getReviewManager().listRevsOfMovie("Apple"));
    }
}
