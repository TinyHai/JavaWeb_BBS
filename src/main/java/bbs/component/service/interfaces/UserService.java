package bbs.component.service.interfaces;

import bbs.model.User;

import java.util.List;

public interface UserService {

    User getAccountByName(String userName);

    boolean pwdChanged(long userId);

    List<User> getSimpleUsers();

    void insertUserIfAbsent(String userName, String password, String email);

    User getUserById(long userId);

    User getUserByName(String userName);

}
