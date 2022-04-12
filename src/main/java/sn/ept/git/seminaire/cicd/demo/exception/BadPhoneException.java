package sn.ept.git.seminaire.cicd.demo.exception;

public class BadPhoneException extends RuntimeException{
    public BadPhoneException() {
        super();
    }

    public BadPhoneException(String message) {
        super(message);
    }

    public BadPhoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadPhoneException(Throwable cause) {
        super(cause);
    }

    public BadPhoneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
