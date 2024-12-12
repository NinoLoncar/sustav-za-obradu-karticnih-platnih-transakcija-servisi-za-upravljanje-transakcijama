package foi.air.szokpt.transactionmng.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Resource not found");
    }
}
