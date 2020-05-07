package advertisProgram.util;


import adverProgram.models.Item;
import adverProgram.models.User;

import java.io.*;
import java.util.List;
import java.util.Map;

public class FileUtil {

    private static final String FILE_PATH = "C:\\JAVA+\\Advertising-program-Java\\src\\adverProgram\\util\\fileUser.txt";


    public static void serializeUserMap(Map<String, User>userMap) throws IOException, ClassNotFoundException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
        objectOutputStream.writeObject(userMap);
        objectOutputStream.close();
    }


    public static Map<String, User> deserializeUserMap() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
        try {
            Object deserialization = objectInputStream.readObject();
            Map<String, User> userMap = (Map<String, User>) deserialization;
            objectInputStream.close();
            return userMap;

        } catch (IOException e) {

            System.out.println("Dont found");

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static final String FILE_PATH1 = "C:\\JAVA+\\Advertising-program-Java\\src\\adverProgram\\util\\fileAd.txt";

    public static void serializeItemList(List<Item>items) throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH1));
        objectOutputStream.writeObject(items);
        objectOutputStream.close();
    }


    public static List<Item> deserializeItemList(List<Item> items) throws IOException, ClassNotFoundException {
       try{
           ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(FILE_PATH1));
           Object deserialization=objectInputStream.readObject();
           List<Item> item=(List<Item>) deserialization;
           objectInputStream.close();
           return item;
       }catch (IOException e){
           System.out.println(e.getMessage());
       }catch (ClassNotFoundException e){
           System.out.println(e.getMessage());
       }


        return items;
    }


}



