package bbs.exceptions;

public class UserExistException extends RuntimeException {
    public UserExistException(String s) {
        super(s);
    }
}
