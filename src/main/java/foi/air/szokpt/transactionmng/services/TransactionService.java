package foi.air.szokpt.transactionmng.services;

import foi.air.szokpt.transactionmng.dtos.responses.TransactionPageData;
import foi.air.szokpt.transactionmng.entities.Transaction;
import foi.air.szokpt.transactionmng.enums.CardBrand;
import foi.air.szokpt.transactionmng.enums.TrxType;
import foi.air.szokpt.transactionmng.exceptions.NotFoundException;
import foi.air.szokpt.transactionmng.repositories.TransactionRepository;
import foi.air.szokpt.transactionmng.specs.TransactionSpecs;
import foi.air.szokpt.transactionmng.util.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    final int pageSize = 15;
    private final TransactionRepository transactionRepository;
    private final Validator<Transaction> transactionValidator;

    public TransactionService(TransactionRepository transactionRepository,
                              @Qualifier("transactionDataValidator") Validator<Transaction> transactionValidator) {
        this.transactionRepository = transactionRepository;
        this.transactionValidator = transactionValidator;
    }

    public TransactionPageData getTransactions(
            Integer page,
            CardBrand cardBrand,
            TrxType trxType,
            LocalDateTime before,
            LocalDateTime after,
            BigDecimal amountGreaterThan,
            BigDecimal amountLessThan, Boolean processed) {

        Specification<Transaction> spec = Specification
                .where(TransactionSpecs.hasCardBrand(cardBrand))
                .and(TransactionSpecs.hasTrxType(trxType))
                .and(TransactionSpecs.beforeDateTime(before))
                .and(TransactionSpecs.afterDateTime(after))
                .and(TransactionSpecs.amountGreaterThan(amountGreaterThan))
                .and(TransactionSpecs.amountLessThan(amountLessThan))
                .and(TransactionSpecs.isProcessed(processed));
        if (page == null) return getAllTransactions(spec);
        return getTransactionsByPage(page, pageSize, spec);
    }

    private TransactionPageData getAllTransactions(
            Specification<Transaction> spec) {
        List<Transaction> allTransactions = transactionRepository.findAll(spec);
        return new TransactionPageData(allTransactions, 0, 0);
    }

    private TransactionPageData getTransactionsByPage(
            int page,
            int pageSize,
            Specification<Transaction> spec) {

        int pageNumber = calculatePageIndex(page, pageSize);
        int pageIndex = pageNumber - 1;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Transaction> transactionPage = transactionRepository
                .findAll(spec, pageable);
        return new TransactionPageData(
                transactionPage.getContent(),
                transactionPage.getNumber() + 1,
                transactionPage.getTotalPages()
        );
    }

    public Transaction getTransaction(int id){
        return transactionRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void updateTransaction(int id, Transaction newTransactionData){
        Optional<Transaction> optionalExistingTransaction = transactionRepository.findById(id);
        if(optionalExistingTransaction.isPresent()){
            newTransactionData.setId(id);
            transactionValidator.validateData(newTransactionData);
            Transaction existingTransaction = optionalExistingTransaction.get();
            saveTransactionUpdate(existingTransaction, newTransactionData);
        }else{
            throw new NotFoundException();
        }
    }

    private void saveTransactionUpdate(Transaction existingTransaction, Transaction newTransactionData){
        existingTransaction.setAmount(newTransactionData.getAmount());
        existingTransaction.setTransactionTimestamp(newTransactionData.getTransactionTimestamp());
        transactionRepository.save(existingTransaction);
    }

    private int calculatePageIndex(int page, int pageSize) {
        long totalItems = transactionRepository.count();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        return Math.max(1, Math.min(page, totalPages));
    }
}
