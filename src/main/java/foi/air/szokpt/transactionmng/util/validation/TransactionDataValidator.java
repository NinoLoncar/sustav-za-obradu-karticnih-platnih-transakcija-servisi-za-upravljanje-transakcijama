package foi.air.szokpt.transactionmng.util.validation;

import foi.air.szokpt.transactionmng.entities.Transaction;
import foi.air.szokpt.transactionmng.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Qualifier("transactionDataValidator")
public class TransactionDataValidator implements Validator<Transaction>{

    public void validateData(Transaction transaction){
        validateRequiredFields(transaction);
    }

    protected void validateRequiredFields(Transaction transaction){
        validateField(transaction.getTransactionTimestamp(), "Time and date cannot be null or empty");
        validateField(transaction.getAmount(), "Amount cannot be null or empty");
    }

    private void validateField(BigDecimal field, String errorMessage){
        if(field == null){
            throw new ValidationException(errorMessage);
        }
    }

    private void validateField(LocalDateTime field, String errorMessage){
        if(field == null){
            throw new ValidationException(errorMessage);
        }
    }
}
