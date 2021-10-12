
public class UserInterface {
    public static void main(String[] args) {
        NormalUser u = new NormalUser("Linda","123456");
        u.give_like("chocolate");

        NormalUser s = new NormalUser("Betty","3333333");
        s.give_like("Banana");
    }
}
