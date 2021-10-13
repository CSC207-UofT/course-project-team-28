import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Called for read and write user's file.
 * it should be only called by User class and its subclass.
 */
public class WriteUser implements WriteFile{
    public FileReader userreader;
    public BufferedReader userlogin;
    public FileWriter writeuser;


    /**
     * Called by NormalUser constructor, it aims to create a new file for the new user.
     * pass username and password of user as parameters.
     */

    @Override
    public void create_file(Object user) throws IOException {
        if(user instanceof NormalUser) {
            Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
            writeuser = new FileWriter(str1.toString() + "\\main\\NormalUser\\" + ((NormalUser) user).username + ".txt");
            writeuser.write(((NormalUser) user).username);
            writeuser.write("\r\n");
            writeuser.write(((NormalUser) user).password);
            writeuser.write("\r\n");
            writeuser.write("c");
            writeuser.write("\r\n");
            writeuser.write("[]");
            writeuser.close();//!!! remember
        }
        else{
            Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
            writeuser = new FileWriter(str1.toString() + "\\main\\AdminUser\\" + ((AdminUser) user).username + ".txt");
            writeuser.write(((AdminUser) user).username);
            writeuser.write("\r\n");
            writeuser.write(((AdminUser) user).password);

            writeuser.close();
        }
    }

    /**
     * Read NormalUser and AdminUser two folders, create obejct for each user and return a two-dimensional array.
     * the array at first place is NormalUser, the array at second place is AdminUser.
     */
    @Override
    public ArrayList<Object> get_object_from_file() throws IOException{
        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
        File NormalUserPath = new File(str1.toString() + "\\main\\NormalUser"); //get full path for NormalUser folder
        File AdminUserPath = new File(str1.toString() + "\\main\\AdminUser"); //get full path for AdminUser folder

        String[] lstOfNormal = NormalUserPath.list();// get all the file name in NormalUser folder
        String[] lstOfAdmin = AdminUserPath.list();// get all the file name in AdminUser folder

        ArrayList<Object> AdminUser_lst = new ArrayList<Object>();
        ArrayList<Object> NormalUser_lst = new ArrayList<Object>();
        ArrayList<Object> user_lst = new ArrayList<Object>();

        if(lstOfNormal == null & lstOfAdmin == null){
            return new ArrayList<Object>();
        }
        else if(lstOfNormal != null){
            for(String nu: lstOfNormal){
                ArrayList<String> lst = read_file(str1, nu, "NormalUser");

                lst.set(3, lst.get(3).replace("[", "")); //get rid of "[]" in playlist
                lst.set(3, lst.get(3).replace("]", "")); //get rid of "[]" in playlist
                String[] pl1 = lst.get(3).split(","); // change playlist from string to array
                ArrayList<String> pl2 = new ArrayList<String>(Arrays.asList(pl1)); // change playlist from array to arraylist

                NormalUser nur = new NormalUser(lst.get(0), lst.get(1), lst.get(2), pl2); // create object foe this single user
                NormalUser_lst.add(nur);
            }
        }
        else{
            for(String au: lstOfAdmin) {
                ArrayList<String> lst = read_file(str1, au, "AdminUser");

                AdminUser nur = new AdminUser(lst.get(0), lst.get(1)); // create object foe this single user
                AdminUser_lst.add(nur);
            }
        }
        user_lst.add(AdminUser_lst);
        user_lst.add(NormalUser_lst);
        return user_lst;
    }

    /**
     * Called by NormalUser give_like method, it aims to read and write file for playlist in file for the user.
     * pass moviename of movie and username of user as parameters.
     */
    public ArrayList<String> give_like_readandwrite(String moviename, String username) throws IOException {
        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        ArrayList<String> lst = read_file(str1, username, "NormalUser");

        if (lst.get(3).equals("[]")){
            lst.set(3, lst.get(3).replace("[]","[" + moviename + "]"));
        }
        else if(!lst.get(3).contains(moviename)){
            lst.set(3, lst.get(3).replace("]","," + moviename + "]"));
        }

        writeuser = new FileWriter(str1.toString() + "\\main\\NormalUser\\" + username + ".txt");
        for(String str: lst){
            writeuser.write(str);
            writeuser.write("\r\n");
        }
        writeuser.close();

        return lst;
    }

    /**
     * Helper method, read file
     */
    public ArrayList<String> read_file(Path str1, String fn, String folder) throws IOException {
        userreader = new FileReader(str1.toString() + "\\main\\" + folder + "\\" + fn);
        userlogin = new BufferedReader(userreader);

        ArrayList<String> lst = new ArrayList<String>();
        String line = userlogin.readLine();
        while (line != null) {
            lst.add(line);
            line = userlogin.readLine();
        }
        userlogin.close();

        return lst;
    }
}
