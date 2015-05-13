package lv.mrusk.neuedahomework.exception;

public class MindMapFormatException extends RuntimeException {

    public MindMapFormatException() {
    }

    public MindMapFormatException(String s) {
        super(s);
    }

    public MindMapFormatException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MindMapFormatException(Throwable throwable) {
        super(throwable);
    }
}
