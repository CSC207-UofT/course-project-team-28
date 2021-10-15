import java.io.IOException;

public class demoMain {
    public static void main(String[] args) throws IOException{
        WriteReview wr = new WriteReview();
        Review r = new Review("Ella", "Apple", "I like apple", 1);
        wr.create_file(r);
    }
}
