package dmitreev.petproject.java.oneDayOneWay.error.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String message) {
        super(message);
    }
}
