package com.foodApp.DAO;

import java.util.List;
import com.foodApp.Model.User;

public interface UserDAO {
    List<User> getUsers();

    User getUser(String email);

    boolean updateUser(User user);

    boolean addUser(String username, String email, String password, String mobile);

    boolean removeUser(String email);
}
