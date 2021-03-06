package InterfaceAdapter;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import UseCase.GatewayInterface;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GatewayTest {

    private static Object[] infoList;
    private static Object[] auInfoList;
    private static Object[] movieList;
    private static List<String> list = Arrays.asList("Apple", "Candy", "Team28", "Happy Life");
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
    private static final GatewayInterface gateway = new Gateway();
    private static ArrayList<Object> fileData1;

    @BeforeClass
    public static void setUpBeforeClass() {
        infoList = new Object[8];
        infoList[0] = "Ella";
        infoList[1] = "123";
        infoList[2] = "Empty contact info";
        infoList[3] = "Empty description";
        infoList[4] = "Empty category";
        infoList[5] = "300";
        infoList[6] = list;
        infoList[7] = "/src/main/res/GUIPic/winnie.jpg";

        auInfoList = new Object[3];
        auInfoList[0] = "Nico";
        auInfoList[1] = "123";
        auInfoList[2] = "/src/main/res/GUIPic/shake hand.jpg";

        movieList = new Object[4];
        movieList[0] = "Sugar";
        movieList[1] = "123456789";
        movieList[2] = "Action";
        movieList[3] = 3;


        InstanceMain.setClearInstance();
        WriteUser wu = new WriteUser(str1 + "/src/test/res/NormalUser", str1 + "/src/test/res/AdminUser");
        WriteReview wr = new WriteReview(str1 +"/src/test/res/Review");
        WriteMovie wm = new WriteMovie(str1 + "/src/test/res/MovieData/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(wu, wm, wr);

        fileData1 = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "UserManager.txt");
    }

    @After
    public void tearDown(){
        //Restore the file to its original state
        InstanceMain.getWriteUser().writeFile(str1 + "/src/test/res/NormalUser/" + "UserManager.txt", fileData1);
    }


    @Test
    public void createFileNormalUser() {
        gateway.createFileNormalUser("Ella","123","Empty contact info","Empty description",
                "Empty category",300, new ArrayList<>(),"/src/main/res/GUIPic/winnie.jpg");
        fileContentTest(InstanceMain.getUserManager().getUserInfoList("Ella","NormalUser"), infoList);
    }


    @Test
    public void createFileAdminUser() {
        gateway.createFileAdminUser("Nico","123");
        fileContentTest(InstanceMain.getUserManager().getUserInfoList("Nico","AdminUser"), auInfoList);
    }

    @Test
    public void createFileMovie() {
        gateway.createFileMovie("Sugar","123456789","Action",3);
        fileContentTest(InstanceMain.getMovieManager().getMovieProfile("Sugar"), movieList);
    }

    @Test
    public void createFileReview() {
        gateway.createFileReview("Ella", "Sugar","I like this movie",6,3);
        fileContentTest(InstanceMain.getReviewManager().listRevsOfMovie("Sugar").toArray(), movieList);
    }

    @Test
    public void createNewUser() {
        try {
            gateway.createNewUser("Ella", "123","NormalUser");

            infoList[6] = new ArrayList<>();

            Object[] ls = InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "Ella" + ".txt").toArray();
            ls[6] = new ArrayList<>();
            ls[7] = "/src/main/res/GUIPic/winnie.jpg";

            assertTrue(fileContentTest(infoList, ls));

            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/NormalUser/" + "Ella" + ".txt")));
            infoList[6] = list;
        }
        catch (Exception e){
            System.out.println("Unable to create new user");
        }
    }

    @Test
    public void createNewMovie() {
        try {
            gateway.createNewMovie("Sugar","123456789","Action");

            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/MovieData/Action/" + "Sugar" + ".txt")));
        }
        catch (Exception e){
            System.out.println("Unable to create new user");
        }
    }

    @Test
    public void createNewReview() {
        try {
            gateway.createNewReview("UserManager", "Apple","I like this movie",30);
            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/Review/" + "30" + ".txt")));
        }
        catch (Exception e){
            System.out.println("Unable to create new review");
        }
    }

    @Test
    public void updateInfo() {
        gateway.updateInfo("UserManager","51129","contactInfo");
        infoList[2] = "51129";
        fileContentTest(InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "UserManager.txt").toArray(), infoList);
        infoList[2] = "Empty contact info";
    }

    @Test
    public void editPlayList() {
        assertTrue(gateway.editPlayList("Sugar", "UserManager","Like"));
        list = Arrays.asList("Apple", "Candy", "Team28", "Happy Life", "Sugar");
        fileContentTest(InstanceMain.getWriteUser().readFile(str1 + "/src/test/res/NormalUser/" + "UserManager.txt").toArray(), infoList);
        list = Arrays.asList("Apple", "Candy", "Team28", "Happy Life");
        assertTrue(gateway.editPlayList("Sugar", "UserManager","Undo"));

    }

    @Test
    public void editLikeToMovieFile() {
        assertTrue(gateway.editLikeToMovieFile("gateway", "Increase","Action"));
        assertTrue(gateway.editLikeToMovieFile("gateway", "Decrease","Action"));
    }

    @Test
    public void editCoin() {
        assertTrue(gateway.editCoin(2));
        InstanceMain.getWriteReview().addCoinsToReview(2,-1);
    }


    private boolean fileContentTest(Object[] ul, Object[] infoList) {
        for (int i = 0; i < ul.length; i++){
            if (!infoList[i].toString().equals(ul[i].toString()))
                return false;
        }
        return true;
    }
}