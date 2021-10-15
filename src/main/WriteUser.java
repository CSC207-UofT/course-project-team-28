import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Called for read and write user's file.
 * it should be only called by User class and its subclass.
 */
public class WriteUser implements WriteFile{
    protected FileReader userreader;
    protected BufferedReader userlogin;
    protected FileWriter writeuser;


    /**
     * Called by NormalUser constructor, it aims to create a new file for the new user.
     * pass object of user as parameters.
     */

    @Override
    public void create_file(Object user) throws IOException {
        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        if(user instanceof NormalUser) {
            writeuser = new FileWriter(str1 + "\\src\\main\\NormalUser\\" + ((NormalUser) user).username + ".txt");
            writeuser.write(((NormalUser) user).username);
            writeuser.write("\r\n");
            writeuser.write(((NormalUser) user).password);
            writeuser.write("\r\n");
            writeuser.write("Empty contact info");
            writeuser.write("\r\n");
            writeuser.write("[]");
        }
        else{
            writeuser = new FileWriter(str1 + "\\src\\main\\AdminUser\\" + ((AdminUser) user).username + ".txt");
            writeuser.write(((AdminUser) user).username);
            writeuser.write("\r\n");
            writeuser.write(((AdminUser) user).password);

        }
        writeuser.close();//!!! remember
    }

    /**
     * Read AdminUser's folder, create object for each admin user and return an ArrayList of AdminUser.
     */
    @Override
    public ArrayList<AdminUser> get_object_from_file() throws IOException{
        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
        File AdminUserPath = new File(str1 + "\\src\\main\\AdminUser"); //get full path for AdminUser folder

        String[] lstOfAdmin = AdminUserPath.list();// get all the file name in AdminUser folder

        ArrayList<AdminUser> AdminUser_lst = new ArrayList<>();

        if(lstOfAdmin == null){
            return new ArrayList<>();
        }

        else{
            for(String au: lstOfAdmin) {
                ArrayList<String> lst = read_file(str1, au, "AdminUser");

                AdminUser nur = new AdminUser(lst.get(0), lst.get(1)); // create object foe this single user
                AdminUser_lst.add(nur);
            }
        }

        return AdminUser_lst;
    }

    /**
     * Read NormalUser's folder, create object for each Normal user and return an ArrayList of NormalUser.
     */
    public ArrayList<NormalUser> get_NormalUser_from_file() throws IOException{
        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
        File NormalUserPath = new File(str1 + "\\src\\main\\NormalUser"); //get full path for NormalUser folder

        String[] lstOfNormal = NormalUserPath.list();// get all the file name in NormalUser folder

        ArrayList<NormalUser> NormalUser_lst = new ArrayList<>();

        if(lstOfNormal == null ){
            return new ArrayList<>();
        }
        else {
            for(String nu: lstOfNormal){
                ArrayList<String> lst = read_file(str1, nu, "NormalUser");

                lst.set(3, lst.get(3).replace("[", "")); //get rid of "[]" in playlist
                lst.set(3, lst.get(3).replace("]", "")); //get rid of "[]" in playlist
                String[] pl1 = lst.get(3).split(","); // change playlist from string to array
                ArrayList<String> pl2 = new ArrayList<>(Arrays.asList(pl1)); // change playlist from array to arraylist

                NormalUser nur = new NormalUser(lst.get(0), lst.get(1), lst.get(2), pl2); // create object foe this single user
                NormalUser_lst.add(nur);// Add single user object into Arraylist
            }
        }

        return NormalUser_lst;
    }

    /**
     * Called by NormalUser give_like method, it aims to read and write file for playlist in file for the user.
     * pass moviename of movie and username of user as parameters.
     */
    public String give_like_readandwrite(String moviename, String username) throws IOException {
        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        ArrayList<String> lst = read_file(str1, username + ".txt", "NormalUser");

        if (lst.get(3).equals("[]")){
            lst.set(3, lst.get(3).replace("[]","[" + moviename + "]"));
        }
        else if(!lst.get(3).contains(moviename)){
            lst.set(3, lst.get(3).replace("]","," + moviename + "]"));
        }

        write_file(str1, username, lst);

        return lst.get(3);
    }

    public String undo_like_readandwrite(String moviename, String username) throws IOException {
        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        ArrayList<String> lst = read_file(str1, username + ".txt", "NormalUser");

        lst.set(3, lst.get(3).replace("[",""));//For playlist String. get rid of "[" and "]"
        lst.set(3, lst.get(3).replace("]",""));////For playlist String. get rid of "[" and "]"
        String[] movielst1 = lst.get(3).split(",");// split playlist String into Array
        ArrayList<String> movielst2 = new ArrayList<>(Arrays.asList(movielst1));// convert Array into ArrayList
        movielst2.remove(moviename);// remove movie from playlist
        lst.set(3, movielst2.toString().replaceAll(", ", ","));// Change playlist with new playlist, make sure there is no space around "," in playlist


        write_file(str1, username, lst);

        return lst.get(3);
    }

    public String edit_profile_readandwrite(String newcontactinfo, String username) throws IOException{
        Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        ArrayList<String> lst = read_file(str1, username + ".txt", "NormalUser");

        lst.set(2, newcontactinfo);
        write_file(str1, username, lst);

        return lst.get(2);
    }







    /**
     * Helper method, read file
     */
    public ArrayList<String> read_file(Path str1, String fn, String folder) throws IOException {
        userreader = new FileReader(str1.toString() + "\\src\\main\\" + folder + "\\" + fn);
        userlogin = new BufferedReader(userreader);

        ArrayList<String> lst = new ArrayList<>();
        String line = userlogin.readLine();
        while (line != null) {
            lst.add(line);
            line = userlogin.readLine();
        }
        userlogin.close();

        return lst;
    }

    /**
     * Helper method, write file
     */
    public void write_file(Path str1, String username, ArrayList<String> lst) throws IOException {
        writeuser = new FileWriter(str1 + "\\src\\main\\NormalUser\\" + username + ".txt");
        for(String str: lst){
            writeuser.write(str);
            writeuser.write("\r\n");
        }
        writeuser.close();
    }

}
