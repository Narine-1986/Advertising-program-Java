package adverProgram.storages;

import adverProgram.exception.ModelNotFoundException;
import adverProgram.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {


    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getPassword(), user);
    }

    public User getUserByPhoneAndPassword(String phoneNumber, String password) throws ModelNotFoundException {
        for (User user : users.values()) {
            if (user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new ModelNotFoundException("Don't found that user");

    }

    public int getSize() {
        return users.size();
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }
}








