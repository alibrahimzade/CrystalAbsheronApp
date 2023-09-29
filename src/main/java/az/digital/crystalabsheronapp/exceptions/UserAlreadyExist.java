package az.digital.crystalabsheronapp.exceptions;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String s) {
        super(s);
    }
}
