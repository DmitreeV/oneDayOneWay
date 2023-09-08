package dmitreev.petproject.java.oneDayOneWay.error.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
