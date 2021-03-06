package advertisingProgram;

import adverProgram.DataStorage;
import adverProgram.interfaces.Commands;
import adverProgram.models.Category;
import adverProgram.models.Gender;
import adverProgram.models.Item;
import adverProgram.models.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class AdverMain implements Commands {

    private static Scanner scanner = new Scanner(System.in);
    private static DataStorage dataStorage = new DataStorage();
    private static User currentUser = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        dataStorage.initData();

        boolean isRun = true;
        while (isRun) {
            Commands.printMainCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    loginUser();
                    break;
                case REGISTER:
                    registerUser();
                    break;
                default:
                    System.out.println("Wrong command!");
            }
        }
    }

    private static void registerUser() {
        System.out.println("Please input user data " +
                "name,surname,gender(MALE,FEMALE),age,phoneNumber,password");
        try {
            String userDataStr = scanner.nextLine();
            String[] userDataArr = userDataStr.split(",");
            User userFromStorage = dataStorage.getUser(userDataArr[4]);
            if (userFromStorage == null) {
                User user = new User();
                user.setName(userDataArr[0]);
                user.setSurname(userDataArr[1]);
                user.setGender(Gender.valueOf(userDataArr[2].toUpperCase()));
                user.setAge(Integer.parseInt(userDataArr[3]));
                user.setPhoneNumber(userDataArr[4]);
                user.setPassword(userDataArr[5]);
                dataStorage.add(user);
                System.out.println("User was successfully added");
            } else {
                System.out.println("User already exists!");
            }
        } catch (ArrayIndexOutOfBoundsException | ClassNotFoundException e) {
            System.out.println("Wrong Data!");
        }catch (IOException e){
            e.printStackTrace();

        }
    }

    private static void loginUser() {
        System.out.println("Please input phoneNumber,password");
        try {
            String loginStr = scanner.nextLine();
            String[] loginArr = loginStr.split(",");
            User user = dataStorage.getUser(loginArr[0]);
            if (user != null && user.getPassword().equals(loginArr[1])) {
                currentUser = user;
                loginSuccess();
            } else {
                System.out.println("Wrong phoneNumber or password");
            }

        } catch (ArrayIndexOutOfBoundsException | ClassNotFoundException e) {
            System.out.println("Wrong Data!");
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private static void loginSuccess() throws IOException, ClassNotFoundException {
        System.out.println("Welcome " + currentUser.getName() + "!");
        boolean isRun = true;
        while (isRun) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    isRun = false;
                    break;
                case ADD_NEW_AD:
                    addNewItem();
                    break;
                case PRINT_MY_ADS:
                    dataStorage.printItemsByUser(currentUser);
                    break;
                case PRINT_ALL_ADS:
                    dataStorage.printItems();
                    break;
                case PRINT_ADS_BY_CATEGORY:
                    printByCategory();
                    break;
                case PRINT_ALL_ADS_SORT_BY_TITLE:
                    dataStorage.printItemsOrderByTitle();
                    break;
                case PRINT_ALL_ADS_SORT_BY_DATE:
                    dataStorage.printItemsOrderByDate();
                    break;
                case DELETE_MY_ALL_ADS:
                    dataStorage.deleteItemsByUser(currentUser);
                    break;
                case DELETE_AD_BY_ID:
                    deleteById();
                    break;
                default:
                    System.out.println("Wrong command!");
            }
        }
    }

    private static void deleteById() {
        System.out.println("please choose id from list");
        dataStorage.printItemsByUser(currentUser);
        long id = Long.parseLong(scanner.nextLine());
        Item itemById = dataStorage.getItemById(id);
        if (itemById != null && itemById.getAuthor().equals(currentUser)) {
            dataStorage.deleteItemsById(id);
        } else {
            System.out.println("Wrong id!");
        }
    }

    private static void printByCategory() {
        System.out.println("Please choose category name from list: " + Arrays.toString(Category.values()));
        try {
            String categoryStr = scanner.nextLine();
            Category category = Category.valueOf(categoryStr);
            dataStorage.printItemsByCategory(category);
        } catch (Exception e) {
            System.out.println("Wrong Category!");
        }
    }

    private static void addNewItem() {
        System.out.println("Please input item data title,text,price,category");
        System.out.println("Please choose category name from list: " + Arrays.toString(Category.values()));
        try {
            String itemDataStr = scanner.nextLine();
            String[] itemDataArr = itemDataStr.split(",");
            Item item = new Item();
            item.setTitle(itemDataArr[0]);
            item.setText(itemDataArr[1]);
            item.setPrice(Integer.parseInt(itemDataArr[2]));
            item.setCreatedDate(new Date());
            item.setCategory(Category.valueOf(itemDataArr[3].toUpperCase()));
            item.setAuthor(currentUser);
            dataStorage.add(item);
            System.out.println("Item was successfully added");
        } catch (IOException e) {
            e.printStackTrace();

        }catch (NullPointerException e){
            System.out.println("Wrong Data!");
        }


    }
}
