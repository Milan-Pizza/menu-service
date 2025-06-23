package app.cloudkitchen.menuservice.exception;

public class PizzaSizeNotFoundException extends RuntimeException {
    public PizzaSizeNotFoundException(String s) {
        super(s);
    }
}
