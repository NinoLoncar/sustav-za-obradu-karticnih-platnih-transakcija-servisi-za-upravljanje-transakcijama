package foi.air.szokpt.transactionmng.util;

import foi.air.szokpt.transactionmng.clients.TokenValidationClient;
import foi.air.szokpt.transactionmng.exceptions.TokenValidationException;
import org.springframework.stereotype.Component;

@Component
public class Authorizer {
    private final TokenValidationClient tokenValidationClient;

    public Authorizer(TokenValidationClient tokenValidationClient){
        this.tokenValidationClient = tokenValidationClient;
    }

    public void verifyToken(String authenticationHeader){
        if(tokenValidationClient.validateToken(authenticationHeader) == null){
            throw new TokenValidationException();
        }

    }
}
