import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.addAll;


/**
 * Called for read and write user's file.
 * it should be only called by User class and its subclass.
 */
public class WriteUser implements WriteFile{

    private final UserManager um;

    protected BufferedReader userlogin;
    protected FileWriter writeuser;
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File AdminUserFolderPath = new File(str1 + "/src/main/res/AdminUser"); //get full path for AdminUser folder
    protected File NormalUserFolderPath = new File(str1 + "/src/main/res/NormalUser"); //get full path for NormalUser folder
    protected String halfAuPath = str1 + "/src/main/res/AdminUser/"; //get half path for AdminUser file
    protected String halfNuPath = str1 + "/src/main/res/NormalUser/"; //get half path for NormalUser file

    protected NormalInputProcessor nip;
    protected AdminInputProcessor aip;


    public  WriteUser(NormalInputProcessor nip, AdminInputProcessor aip, UserManager um){
        this.um = um;
        get_object_from_file();

        this.nip = nip;
        this.aip = aip;
        this.nip.setUser_mana(this.um);
        this.aip.setUser_mana(this.um);
    }


    public  WriteUser(String normalPath, String adminPath, NormalInputProcessor nip, AdminInputProcessor aip, UserManager um){
        this.AdminUserFolderPath = new File(adminPath);
        this.NormalUserFolderPath = new File(normalPath);
        this.halfAuPath = adminPath +"/";
        this.halfNuPath = normalPath + "/";
        get_object_from_file();

        this.um = um;

        this.nip = nip;
        this.aip = aip;
        this.nip.setUser_mana(this.um);
        this.aip.setUser_mana(this.um);
    }

    /**
     * Add an admin user to the admin user list.
     * @param userName the name of user
     * @return return true if the file successfully created.
     */

    @Override
    public boolean create_file(String userName, String userPassword, String userType) {
        try{
            File file_if_exist;
            ArrayList<Object> infoList = new ArrayList<>();
            infoList.add(userName);
            infoList.add(userPassword);


            if(userType.equals("NormalUser")) {
                infoList.add("Empty contact info");
                infoList.add("Empty description");
                infoList.add("Empty category");
                infoList.add("300");
                infoList.add("[]");

                write_file(halfNuPath + userName + ".txt", infoList);
                file_if_exist = new File(halfNuPath + userName + ".txt");
                this.um.create_normaluser(userName, userPassword, "Empty contact info", "Empty description" ,"Empty category", 300, new ArrayList<>());
            }
            else{

                write_file(halfAuPath + userName + ".txt", infoList);
                file_if_exist = new File(halfAuPath  + userName + ".txt");
                this.um.create_adminuser(userName, userPassword);
            }

            return file_if_exist.exists() && this.um.userIfExist(userName, userPassword, userType);
        }
        catch (IOException e){
            System.out.println("Cannot create the file");
            return false;
        }

    }


    @Override
    public void get_object_from_file() {
        try{
            String[] lstOfAdmin = AdminUserFolderPath.list();// get all the file name in AdminUser folder

            if(lstOfAdmin == null){
            }

            else{
                for(String au: lstOfAdmin) {
                    ArrayList<Object> lst = read_file(halfAuPath + au);

                    this.um.create_adminuser(lst.get(0).toString(), lst.get(1).toString());
                }
            }
            get_NormalUser_from_file();
        }
        catch (IOException e){
            System.out.println("Unable to get the file from the AdminUser Folder");
        }

    }


    public void get_NormalUser_from_file(){
        try{
            String[] lstOfNormal = NormalUserFolderPath.list();// get all the file name in NormalUser folder
            if(lstOfNormal == null ){
            }
            else {
                for(String nu: lstOfNormal){
                    ArrayList<Object> lst = read_file(halfNuPath + nu);
                    ArrayList<String> pl2;

                    lst.set(6, lst.get(6).toString().replace("[", "")); //get rid of "[" in playlist
                    lst.set(6, lst.get(6).toString().replace("]", "")); //get rid of "]" in playlist
                    if (lst.get(6).toString().isEmpty()){
                        pl2 = new ArrayList<>();
                    }
                    else{
                        String[] pl1 = lst.get(6).toString().split(","); // change playlist from string to array
                        pl2 = new ArrayList<>(Arrays.asList(pl1)); // change playlist from array to arraylist
                    }
                    this.um.create_normaluser(lst.get(0).toString(), lst.get(1).toString(), lst.get(2).toString(),
                            lst.get(3).toString(), lst.get(4).toString(), Integer.parseInt(lst.get(5).toString()), pl2);

                }
            }

        }
        catch (IOException e){
            System.out.println("Unable to get the file from the NormalUser Folder");
        }
    }
    

    /**
     * Add movie to playlist
     * @param movieName the name of movie
     * @param username the name of user
     * @return return a string of new playlist
     */
    public String give_like_readandwrite(String movieName, String username){
        try{
            ArrayList<Object> lst = read_file(halfNuPath + username + ".txt");

            if (lst.get(6).equals("[]")){
                lst.set(6, lst.get(6).toString().replace("[]","[" + movieName + "]"));
            }
            else if(!lst.get(6).toString().contains(movieName)){
                lst.set(6, lst.get(6).toString().replace("]","," + movieName + "]"));
            }

            write_file(halfNuPath + username + ".txt", lst);

            return lst.get(6).toString();
        }
        catch (IOException e){
            System.out.println("Unable to add the movie to user playlist");
        }
        return "";
    }

    /**
     * remove movie from playlist
     * @param moviename the name of movie
     * @param username the name of user
     * @return return a string of new playlist
     */
    public String undo_like_readandwrite(String moviename, String username) {
        try{
            ArrayList<Object> lst = read_file(halfNuPath + username + ".txt");

            lst.set(6, lst.get(6).toString().replace("[",""));//For playlist String. get rid of "[" and "]"
            lst.set(6, lst.get(6).toString().replace("]",""));//For playlist String. get rid of "[" and "]"
            String[] movielst1 = lst.get(6).toString().split(",");// split playlist String into Array
            ArrayList<String> movielst2 = new ArrayList<>(Arrays.asList(movielst1));// convert Array into ArrayList
            movielst2.remove(moviename);// remove movie from playlist
            lst.set(6, movielst2.toString().replaceAll(", ", ","));// Change playlist with new playlist, make sure there is no space around "," in playlist


            write_file(halfNuPath + username + ".txt", lst);

            return lst.get(6).toString();
        }
        catch (IOException e){
            System.out.println("Unable to remove the movie to user playlist");
        }
        return "";

    }

    /**
     * edit the user info
     * @param newUpdate the new contact info of user
     * @param username the name of user
     * @param writeType the type of info that user wants to update. e.g. contactInfo, description
     * @return return a string of new contact info
     */
    public String edit_profile_readandwrite(String newUpdate, String username, String writeType) {
        try{
            ArrayList<Object> lst = read_file(halfNuPath + username + ".txt");

            switch (writeType) {
                case "contactInfo":
                    lst.set(2, newUpdate);
                    write_file(halfNuPath + username + ".txt", lst);
                    return lst.get(2).toString();
                case "description":
                    lst.set(3, newUpdate);
                    write_file(halfNuPath + username + ".txt", lst);
                    return lst.get(3).toString();
                case "category":
                    lst.set(4, newUpdate);
                    write_file(halfNuPath + username + ".txt", lst);
                    return lst.get(4).toString();
                default:
                    // give a positive or negative
                    int coin = Integer.parseInt((String) lst.get(5)) + Integer.parseInt(newUpdate);

                    lst.set(5, Integer.toString(coin));
                    write_file(halfNuPath + username + ".txt", lst);
                    return lst.get(5).toString();
            }
        }
        catch (IOException e){
            System.out.println("Unable to edit user profile");
            return "";
        }
    }






    /**
     * Helper method, read file
     */
    public ArrayList<Object> read_file(String path) throws IOException {
        ArrayList<Object> lst = new ArrayList<>();
        try{
            userlogin = new BufferedReader(new FileReader(path));

            String line = userlogin.readLine();
            while (line != null) {
                lst.add(line);
                line = userlogin.readLine();
            }
            userlogin.close();

            return lst;
        }
        catch (IOException e){
            System.out.println("Unable to read file");
        }
        return lst;
    }

    /**
     * Helper method, write file
     */
    public void write_file(String path, ArrayList<Object> lst) throws IOException {
        try{
            writeuser = new FileWriter(path);
            for(Object str: lst){
                writeuser.write(str.toString());
                writeuser.write("\r\n");
            }
            writeuser.close();
        }
        catch (IOException e){
            System.out.println("Unable to write file");
        }

    }

}
