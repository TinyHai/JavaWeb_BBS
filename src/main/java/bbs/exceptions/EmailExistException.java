package bbs.exceptions;

public class EmailExistException extends RuntimeException {
    public EmailExistException(String s) {
        super(s);
    }
}
