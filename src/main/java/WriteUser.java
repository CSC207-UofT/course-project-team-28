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
    protected BufferedReader userlogin;
    protected FileWriter writeuser;
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File AdminUserPath = new File(str1 + "/src/main/res/AdminUser"); //get full path for AdminUser folder
    protected File NormalUserPath = new File(str1 + "/src/main/res/NormalUser"); //get full path for NormalUser folder

    /**
     * Add an admin user to the admin user list.
     * @param user the object of user
     * @return return true if the file successfully created.
     */
    @Override
    public boolean create_file(Object user) throws IOException {
        File file_if_exist;
        if(user instanceof NormalUser) {
            writeuser = new FileWriter(str1 + "/src/main/res/NormalUser/" + ((NormalUser) user).username + ".txt");
            writeuser.write(((NormalUser) user).getUsername());
            writeuser.write("\r\n");
            writeuser.write(((NormalUser) user).getUserPassword());
            writeuser.write("\r\n");
            writeuser.write(((NormalUser) user).getContactinfo());
            writeuser.write("\r\n");
            writeuser.write(((NormalUser) user).getDescription());
            writeuser.write("\r\n");
            writeuser.write(((NormalUser) user).getCategory());
            writeuser.write("\r\n");
            writeuser.write(String.valueOf(((NormalUser) user).getCoin()));
            writeuser.write("\r\n");
            writeuser.write("[]");
            writeuser.close();//!!! remember
            file_if_exist = new File(str1 + "/src/main/res/NormalUser/" + ((NormalUser) user).username + ".txt");
        }
        else{
            writeuser = new FileWriter(str1 + "/src/main/res/AdminUser/" + ((AdminUser) user).username + ".txt");
            writeuser.write(((AdminUser) user).username);
            writeuser.write("\r\n");
            writeuser.write(((AdminUser) user).password);
            writeuser.close();//!!! remember
            file_if_exist = new File(str1 + "/src/main/res/AdminUser/" + ((AdminUser) user).username + ".txt");
        }

        return file_if_exist.exists();
    }

    /**
     * @return read object from AdminUser folder, return an arraylist of AdminUser object.
     */
    @Override
    public ArrayList<AdminUser> get_object_from_file() throws IOException{
        String[] lstOfAdmin = AdminUserPath.list();// get all the file name in AdminUser folder

        ArrayList<AdminUser> AdminUser_lst = new ArrayList<>();

        if(lstOfAdmin == null){
            return new ArrayList<>();
        }

        else{
            for(String au: lstOfAdmin) {
                ArrayList<String> lst = read_file(AdminUserPath + "/" + au);

                AdminUser nur = new AdminUser(lst.get(0), lst.get(1)); // create object foe this single user
                AdminUser_lst.add(nur);
            }
        }

        return AdminUser_lst;
    }

    /**
     * @return read object from NormalUser folder, return an arraylist of NormalUser object.
     */
    public ArrayList<NormalUser> get_NormalUser_from_file() throws IOException{
        String[] lstOfNormal = NormalUserPath.list();// get all the file name in NormalUser folder

        ArrayList<NormalUser> NormalUser_lst = new ArrayList<>();

        if(lstOfNormal == null ){
            return new ArrayList<>();
        }
        else {
            for(String nu: lstOfNormal){
                ArrayList<String> lst = read_file(NormalUserPath + "/" + nu);
                ArrayList<String> pl2;

                lst.set(6, lst.get(6).replace("[", "")); //get rid of "[]" in playlist
                lst.set(6, lst.get(6).replace("]", "")); //get rid of "[]" in playlist
                if (lst.get(6).isEmpty()){
                    pl2 = new ArrayList<>();
                }
                else{
                    String[] pl1 = lst.get(6).split(","); // change playlist from string to array
                    pl2 = new ArrayList<>(Arrays.asList(pl1)); // change playlist from array to arraylist
                }
                NormalUser nur = new NormalUser(lst.get(0), lst.get(1), lst.get(2), lst.get(3), lst.get(4), Integer.parseInt(lst.get(5)), pl2);
                NormalUser_lst.add(nur);// Add single user object into Arraylist
            }
        }

        return NormalUser_lst;
    }

    /**
     * Add movie to playlist
     * @param moviename the name of movie
     * @param username the name of user
     * @return return a string of new playlist
     */
    public String give_like_readandwrite(String moviename, String username) throws IOException {
        ArrayList<String> lst = read_file(NormalUserPath + "/" + username + ".txt");

        if (lst.get(6).equals("[]")){
            lst.set(6, lst.get(6).replace("[]","[" + moviename + "]"));
        }
        else if(!lst.get(6).contains(moviename)){
            lst.set(6, lst.get(6).replace("]","," + moviename + "]"));
        }

        write_file(NormalUserPath + "/" + username + ".txt", lst);

        return lst.get(6);
    }

    /**
     * remove movie from playlist
     * @param moviename the name of movie
     * @param username the name of user
     * @return return a string of new playlist
     */
    public String undo_like_readandwrite(String moviename, String username) throws IOException {
        ArrayList<String> lst = read_file(NormalUserPath + "/" + username + ".txt");

        lst.set(3, lst.get(3).replace("[",""));//For playlist String. get rid of "[" and "]"
        lst.set(3, lst.get(3).replace("]",""));//For playlist String. get rid of "[" and "]"
        String[] movielst1 = lst.get(3).split(",");// split playlist String into Array
        ArrayList<String> movielst2 = new ArrayList<>(Arrays.asList(movielst1));// convert Array into ArrayList
        movielst2.remove(moviename);// remove movie from playlist
        lst.set(3, movielst2.toString().replaceAll(", ", ","));// Change playlist with new playlist, make sure there is no space around "," in playlist


        write_file(NormalUserPath + "/" + username + ".txt", lst);

        return lst.get(3);
    }

    /**
     * edit the user info
     * @param newUpdate the new contact info of user
     * @param username the name of user
     * @param writeType the type of info that user wants to update. e.g. contactInfo, description
     * @return return a string of new contact info
     */
    public String edit_profile_readandwrite(String newUpdate, String username, String writeType) throws IOException{
        ArrayList<String> lst = read_file(NormalUserPath + "/" + username + ".txt");

        switch (writeType) {
            case "contactInfo":
                lst.set(2, newUpdate);
                write_file(NormalUserPath + "/" + username + ".txt", lst);
                return lst.get(2);
            case "description":
                lst.set(3, newUpdate);
                write_file(NormalUserPath + "/" + username + ".txt", lst);
                return lst.get(3);
            case "category":
                lst.set(4, newUpdate);
                write_file(NormalUserPath + "/" + username + ".txt", lst);
                return lst.get(4);
            default:
                lst.set(5, newUpdate);
                write_file(NormalUserPath + "/" + username + ".txt", lst);
                return lst.get(5);
        }

    }







    /**
     * Helper method, read file
     */
    public ArrayList<String> read_file(String path) throws IOException {
        userlogin = new BufferedReader(new FileReader(path));

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
    public void write_file(String path, ArrayList<String> lst) throws IOException {
        writeuser = new FileWriter(path);
        for(String str: lst){
            writeuser.write(str);
            writeuser.write("\r\n");
        }
        writeuser.close();
    }

}
