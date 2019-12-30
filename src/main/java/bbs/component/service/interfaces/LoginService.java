package bbs.component.service.interfaces;

public interface LoginService {

    void signIn(String userName, String password);

    void signOut(String userName);

    void signUp(String userName, String password, String email);
}
