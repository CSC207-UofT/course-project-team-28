package Framework.DataAccess;

import InterfaceAdapter.Gateway;
import InterfaceAdapter.Interface.WriteUserInterface;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Called for read and write user's file.
 * it should be only called by User class and its subclass.
 */
public class WriteUser implements WriteUserInterface {

    protected BufferedReader userlogin;
    protected FileWriter writeuser;
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File AdminUserFolderPath = new File(str1 + "/src/main/res/AdminUser"); //get full path for AdminUser folder
    protected File NormalUserFolderPath = new File(str1 + "/src/main/res/NormalUser"); //get full path for NormalUser folder
    protected String halfAuPath = str1 + "/src/main/res/AdminUser/"; //get half path for AdminUser file
    protected String halfNuPath = str1 + "/src/main/res/NormalUser/"; //get half path for NormalUser file
    protected Gateway gateway = new Gateway();


    /**
     * call getObjectFromFile method first, then store all the object into all Manager classes, finally, store those
     * Manager classes into controller.
     */
    public WriteUser(){
        getObjectFromFile();
    }


    /**
     * Constructor for test use only
     * @param normalPath NormalUser test folder path
     * @param adminPath AdminUser test folder path
     */
    public WriteUser(String normalPath, String adminPath){
        this.AdminUserFolderPath = new File(adminPath);
        this.NormalUserFolderPath = new File(normalPath);
        this.halfAuPath = adminPath +"/";
        this.halfNuPath = normalPath + "/";

        getObjectFromFile();

    }



    /**
     * Depending the usertype, create the admin user or normal user file, and add user object to UserManager.
     * @param userName the name of user
     * @param userPassword the password of user
     * @param userType the type of user
     * @return return true if the file and object successfully created.
     */

    @Override
    public boolean createFile(String userName, String userPassword, String userType) {
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
            infoList.add("/src/main/res/GUIPic/shake hand.jpg");

            writeFile(halfNuPath + userName + ".txt", infoList);
            file_if_exist = new File(halfNuPath + userName + ".txt");
        }
        else{
            writeFile(halfAuPath + userName + ".txt", infoList);
            file_if_exist = new File(halfAuPath  + userName + ".txt");
        }

        return file_if_exist.exists();

    }

    /**
     * At the beginning state of program, this method will read all the file in the AdminUser folder and create object for
     * each user, then store all the user object into UserManager.
     */
    @Override
    public void getObjectFromFile() {
        String[] lstOfAdmin = AdminUserFolderPath.list();// get all the file name in AdminUser folder

        if(lstOfAdmin != null){
            for(String au: lstOfAdmin) {
                ArrayList<Object> lst = readFile(halfAuPath + au);

                this.gateway.createFileAdminUser(lst.get(0).toString(), lst.get(1).toString());
            }
        }
        getNormalUserFromFile();

    }


    /**
     * At the beginning state of program, this method will read all the file in the NormalUser folder and create object for
     * each user, then store all the user object into UserManager.
     * It is automatically called by <getObjectFromFile> method
     */

    public void getNormalUserFromFile(){
        String[] lstOfNormal = NormalUserFolderPath.list();// get all the file name in Core.User.NormalUser folder
        if(lstOfNormal != null ){
            for(String nu: lstOfNormal){
                ArrayList<Object> lst = readFile(halfNuPath + nu);
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



                this.gateway.createFileNormalUser(lst.get(0).toString(), lst.get(1).toString(), lst.get(2).toString(),
                        lst.get(3).toString(), lst.get(4).toString(), Integer.parseInt(lst.get(5).toString()), pl2, lst.get(7).toString());

            }
        }
    }
    

    /**
     * Add movie to user playlist in the file
     * @param movieName the name of movie
     * @param username the name of user
     */
    @Override
    public boolean givelikeReadAndWrite(String movieName, String username){
        ArrayList<Object> lst = readFile(halfNuPath + username + ".txt");

        if (lst.get(6).equals("[]")){
            lst.set(6, lst.get(6).toString().replace("[]","[" + movieName + "]"));
        }
        else if(!lst.get(6).toString().contains(movieName)){
            lst.set(6, lst.get(6).toString().replace("]","," + movieName + "]"));
        }

        writeFile(halfNuPath + username + ".txt", lst);

        return lst.get(6).toString().contains(movieName);
    }

    /**
     * remove movie from playlist in the file
     * @param movieName the name of movie
     * @param username the name of user
     * @return return a string of new playlist
     */
    @Override
    public boolean undoLikeReadAndWrite(String movieName, String username) {
        ArrayList<Object> lst = readFile(halfNuPath + username + ".txt");

        lst.set(6, lst.get(6).toString().replace("[",""));//For playlist String. get rid of "[" and "]"
        lst.set(6, lst.get(6).toString().replace("]",""));//For playlist String. get rid of "[" and "]"
        String[] movielst1 = lst.get(6).toString().split(",");// split playlist String into Array
        ArrayList<String> movielst2 = new ArrayList<>(Arrays.asList(movielst1));// convert Array into ArrayList
        movielst2.remove(movieName);// remove movie from playlist
        lst.set(6, movielst2.toString().replaceAll(", ", ","));// Change playlist with new playlist, make sure there is no space around "," in playlist


        writeFile(halfNuPath + username + ".txt", lst);

        return !lst.get(6).toString().contains(movieName);

    }

    /**
     * edit the user info in the file.
     * @param newUpdate the new contact info of user
     * @param username the name of user
     * @param writeType the type of info that user wants to update. e.g. contactInfo, description, category, picPath, coin
     * @return return true if update completed. Otherwise, return false.
     */
    @Override
    public boolean editProfileReadAndWrite(String username, String newUpdate, String writeType) {
        ArrayList<Object> lst = readFile(halfNuPath + username + ".txt");

        //noinspection EnhancedSwitchMigration
        switch (writeType) {
            case "contactInfo":
                lst.set(2, newUpdate);
                writeFile(halfNuPath + username + ".txt", lst);
                return lst.get(2).toString().equals(newUpdate);
            case "description":
                lst.set(3, newUpdate);
                writeFile(halfNuPath + username + ".txt", lst);
                return lst.get(3).toString().equals(newUpdate);
            case "category":
                lst.set(4, newUpdate);
                writeFile(halfNuPath + username + ".txt", lst);
                return lst.get(4).toString().equals(newUpdate);
            case "picPath":
                lst.set(7, newUpdate);
                writeFile(halfNuPath + username + ".txt", lst);
                return lst.get(7).toString().equals(newUpdate);
            default:
                // it changes coin.
                // give a positive or negative number
                String originalCoin = (String) lst.get(5);
                int coin = Integer.parseInt((String) lst.get(5)) + Integer.parseInt(newUpdate);

                lst.set(5, Integer.toString(coin));
                writeFile(halfNuPath + username + ".txt", lst);
                return lst.get(5).toString().equals(Integer.toString(Integer.parseInt(originalCoin) + Integer.parseInt(newUpdate)));
        }
    }






    /**
     * Helper method, read file
     */
    public ArrayList<Object> readFile(String path) {
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
    public void writeFile(String path, ArrayList<Object> lst) {
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
