package dmitreev.petproject.java.oneDayOneWay.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AppError {
    private int status;
    private final String reason;
    private final String message;
    private final LocalDateTime timestamp;

    public AppError(String reason, String message, LocalDateTime timestamp) {
        this.reason = reason;
        this.message = message;
        this.timestamp = timestamp;
    }
}
