package lv.mrusk.neuedahomework.exception;

public class MethodNotFoundException extends RuntimeException {

    public MethodNotFoundException() {
    }

    public MethodNotFoundException(String s) {
        super(s);
    }

    public MethodNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MethodNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
