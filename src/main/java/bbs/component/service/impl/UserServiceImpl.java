package bbs.component.service.impl;

import bbs.exceptions.EmailExistException;
import bbs.exceptions.UserExistException;
import bbs.dao.mapper.UserMapper;
import bbs.model.User;
import bbs.component.service.interfaces.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserMapper userMapper, BCryptPasswordEncoder encoder) {
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
    public User getAccountByName(String userName) {
        return userMapper.getAccountByName(userName);
    }

    @Override
    public boolean pwdChanged(long userId) {
        return 1 == userMapper.pwdChanged(userId);
    }

    @Override
    public List<User> getSimpleUsers() {
        return userMapper.getSimpleUsers();
    }

    @Override
    public void insertUserIfAbsent(String userName, String password, String email) {
        if (userMapper.getAccountByName(userName) != null) {
            throw new UserExistException(userName + " is always exist");
        }
        if (userMapper.getUserByEmail(email) != null) {
            throw new EmailExistException(email + " is always exist");
        }
        userMapper.insertUser(userName, encoder.encode(password), email);
    }

    @Override
    public User getUserById(long userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }
}
