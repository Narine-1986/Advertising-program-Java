package advertisProgram.storage;

import adverProgram.comparators.SortByTitle;
import adverProgram.models.Category;
import adverProgram.models.Item;
import adverProgram.models.User;
import adverProgram.util.FileUtil;

import java.io.IOException;
import java.util.*;

public class DataStorage {
    private static long itemId = 1;

    private Map<String, User> userMap = new HashMap<>();
    private List<Item> items = new ArrayList<>();

    public void add(User user) throws IOException, ClassNotFoundException {
        userMap.put(user.getPhoneNumber(), user);
        FileUtil.serializeUserMap(userMap);
    }

    public void add(Item item) throws IOException, NullPointerException {
        item.setId(itemId++);
        items.add(item);
        FileUtil.serializeItemList(items);

    }

    public User getUser(String phoneNumber) {
        return userMap.get(phoneNumber);
    }

    public Item getItemById(long id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void printItems() {
        for (Item item : items) {
            System.out.println("Id: "+item.getId());
            System.out.println(item.getTitle());
            System.out.println("Advertisement: " + item.getCreatedDate());
            System.out.println("by: " + item.getAuthor().getName());
            System.out.println(item.getText());
            System.out.println("------------------------------------------");
        }
    }


    public void printItemsOrderByTitle() {
        SortByTitle byTitleComparator = new SortByTitle();
        items.sort(byTitleComparator);
        printItems();

    }

    public void printItemsOrderByDate() {
        List<Item> orderedList = new ArrayList<>(items);
        orderedList.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getCreatedDate().compareTo(o2.getCreatedDate());
            }
        });
        for (Item item : orderedList) {
            System.out.println(item);
        }
    }

    public void printItemsByUser(User user) {
        for (Item item : items) {
            if (item.getAuthor().equals(user)) {
                System.out.println(item);
            }
        }
    }

    public void printItemsByCategory(Category category) {
        for (Item item : items) {
            if (item.getCategory() == category) {
                System.out.println(item);
            }
        }
    }

    public void deleteItemsByUser(User user) throws IOException{
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item next = iterator.next();
            if (next.getAuthor().equals(user)) {
                iterator.remove();
            }
        }
       FileUtil.serializeItemList(items);

    }

    public void deleteItemsById(long id) {
        items.remove(getItemById(id));
    }


    public void initData()  {
       try{
           userMap=FileUtil.deserializeUserMap();
           items=FileUtil.deserializeItemList(items);
       }catch (IOException | ClassNotFoundException e){
           e.getMessage();
       }
        System.out.println(userMap);
        System.out.println(items);


    }


}
