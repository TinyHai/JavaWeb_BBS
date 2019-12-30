package bbs.component.service.impl;

import bbs.dao.mapper.UserMapper;
import bbs.component.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;

    @Autowired
    public LoginServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void signIn(String userName, String password) {
    }

    @Override
    public void signOut(String userName) {

    }

    @Override
    public void signUp(String userName, String password, String email) {

    }
}
