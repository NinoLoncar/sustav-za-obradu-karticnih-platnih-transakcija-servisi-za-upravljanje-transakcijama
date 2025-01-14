package foi.air.szokpt.transactionmng.exceptions;

public class TokenValidationException extends RuntimeException {
    public TokenValidationException() {
        super("Token is invalid or expired");
    }
}
