package hr.java.production.bervanakis10.exception;

public class DuplicateCategoryNameException extends RuntimeException {
    public DuplicateCategoryNameException() {
    }

    public DuplicateCategoryNameException(String message) {
        super(message);
    }

    public DuplicateCategoryNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateCategoryNameException(Throwable cause) {
        super(cause);
    }

    public DuplicateCategoryNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
