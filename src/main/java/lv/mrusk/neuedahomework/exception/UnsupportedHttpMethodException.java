package lv.mrusk.neuedahomework.exception;

public class UnsupportedHttpMethodException extends RuntimeException {

    public UnsupportedHttpMethodException() {
    }

    public UnsupportedHttpMethodException(String s) {
        super(s);
    }

    public UnsupportedHttpMethodException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UnsupportedHttpMethodException(Throwable throwable) {
        super(throwable);
    }
}
