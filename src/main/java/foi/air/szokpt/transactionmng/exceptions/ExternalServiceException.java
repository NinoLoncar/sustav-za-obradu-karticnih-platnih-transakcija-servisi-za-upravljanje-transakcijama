package foi.air.szokpt.transactionmng.exceptions;

public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String message) {
        super("Could not connect to external service:" + message);
    }
}
