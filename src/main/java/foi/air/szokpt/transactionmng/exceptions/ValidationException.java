package foi.air.szokpt.transactionmng.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super("Validation failed: " + message);
    }
}
