package adverProgram;

import adverProgram.exception.ModelNotFoundException;
import adverProgram.interfaces.Commands;
import adverProgram.models.*;
import adverProgram.storages.AdStorage;
import adverProgram.storages.UserStorage;

import java.util.Date;
import java.util.Scanner;

public class AdMain implements Commands {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final AdStorage AD_STORAGE = new AdStorage();
    public static final UserStorage USER_STORAGE = new UserStorage();
    public static User LoginUser;


    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
                System.out.println("Input number please");
            }
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Wrong command!");

            }
        }
    }

    private static void login() {
        System.out.println("Please input phoneNumber, password");
        String userStr = SCANNER.nextLine();
        String[] userData = userStr.split(",");
        try {
            LoginUser = USER_STORAGE.getUserByPhoneAndPassword(userData[0], userData[1]);
            if (USER_STORAGE.isEmpty()) {
                register();
            } else {
                printLoginCommands();
            }
        } catch (ArrayIndexOutOfBoundsException | ModelNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void register() {
        System.out.println("Please input name, surname, age, phoneNumber,password, gender");
        try {
            String userStr = SCANNER.nextLine();
            String[] userData = userStr.split(",");
            User user = new User();
            user.setName(userData[0]);
            user.setSurname(userData[1]);
            user.setAge(Integer.parseInt(userData[2]));
            user.setPhoneNumber(userData[3]);
            user.setPassword(userData[4]);
            user.setGender(Gender.valueOf(userData[5].toUpperCase()));
            USER_STORAGE.addUser(user);
            System.out.println("You are registered");
        } catch (Exception e) {
            System.out.println("Please input again");
            register();
        }
    }

    private static void printLoginCommands() {
        User user = new User();
        boolean isRun = true;
        while (isRun) {
            Commands.printLoginCommands();
            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
                System.out.println("Input number please");
            }
            switch (command) {
                case LOGOUT:
                    isRun = false;
                    break;
                case ADD_NEW_AD:
                    addAd();
                    break;
                case PRINT_MY_ALL_ADS:
                    printMyAllAds();
                    break;
                case PRINT_ALL_ADS:
                    AD_STORAGE.printAllAds();
                    break;
                case PRINT_AD_BY_CATEGORY:
                    printAdByCategory();
                    break;
                case PRINT_ALL_ADS_BY_TITLE_SORT:
                    printAllAdsByTytleSort();
                    break;
                case PRINT_ALL_ADS_BY_DATE_SORT:
                    printAllAdsByDateSort();
                    break;
                case DELETE_MY_ALL_ADS:
                    deleteMyAllAds();
                    break;
                case DELETE_AD_BY_TITLE:
                    deleteAdByTitle();
                    break;
                default:
                    System.out.println("Wrong command!!!");
            }

        }
    }

    private static void printMyAllAds() {
        AD_STORAGE.printMyAllAds(LoginUser);
    }

    private static void printAdByCategory() {
        System.out.println("Input category");
        String category = SCANNER.nextLine();
        AD_STORAGE.printAdsByCategory(Category.valueOf(category.toUpperCase()));
    }

    private static void deleteAdByTitle() {
        AD_STORAGE.printAllAds();
        System.out.println("Please select by title");
        String title = SCANNER.nextLine();
        AD_STORAGE.deleteAdByTitles(title);
    }

    private static void deleteMyAllAds() {
        AD_STORAGE.deleteMyAllAd(LoginUser);
    }

    private static void printAllAdsByDateSort() {
        AD_STORAGE.sortAdsByCreatDate();
    }

    private static void printAllAdsByTytleSort() {
        AD_STORAGE.sortAdsByTitle();
    }

    private static void addAd() {
        System.out.println("Please input Ad data: title, text, price, createdDate, category, author");
        AD ad = new AD();
        try {
            String AdDataStr = SCANNER.nextLine();
            String[] AdData = AdDataStr.split(",");
            ad.setTitle(AdData[0]);
            ad.setText(AdData[1]);
            ad.setPrice(Integer.parseInt(AdData[2]));
            ad.setCreatedDate(new Date());
            ad.setCategory(Category.valueOf(AdData[3].toUpperCase()));
            ad.setAuthor(LoginUser);
            AD_STORAGE.add(ad);
            System.out.println("Post was added!");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid data");
            addAd();
        }
    }

}







