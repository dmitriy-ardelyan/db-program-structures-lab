package main.java.vtp.lab;

public class ResourseReadFailedException extends RuntimeException {

    public ResourseReadFailedException(final String message) {
        super(message);
    }

    public ResourseReadFailedException(final String messageTemplate, Object... args) {
        super(String.format(messageTemplate, args));
    }
}
