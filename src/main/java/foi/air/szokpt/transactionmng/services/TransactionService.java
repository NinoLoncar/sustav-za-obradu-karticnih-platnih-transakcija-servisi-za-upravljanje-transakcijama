package foi.air.szokpt.transactionmng.services;

import foi.air.szokpt.transactionmng.dtos.responses.RawTransaction;
import foi.air.szokpt.transactionmng.dtos.responses.TransactionDataResponse;
import foi.air.szokpt.transactionmng.dtos.responses.TransactionPageData;
import foi.air.szokpt.transactionmng.entities.Tid;
import foi.air.szokpt.transactionmng.entities.Transaction;
import foi.air.szokpt.transactionmng.enums.CardBrand;
import foi.air.szokpt.transactionmng.enums.InstallmentsCreditor;
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
import java.util.*;
import java.util.stream.Collectors;

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
            List<CardBrand> cardBrand,
            List<TrxType> trxType,
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
        List<TransactionDataResponse> allTransactionsData = convertToTransactionData(allTransactions);
        return new TransactionPageData(allTransactionsData, 0, 0);
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
        List<TransactionDataResponse> transactionDataList = convertToTransactionData(transactionPage.getContent());

        return new TransactionPageData(
                transactionDataList,
                transactionPage.getNumber() + 1,
                transactionPage.getTotalPages()
        );
    }

    private List<TransactionDataResponse> convertToTransactionData(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::convertToTransactionData)
                .collect(Collectors.toList());
    }

    private TransactionDataResponse convertToTransactionData(Transaction transaction) {
        return new TransactionDataResponse(transaction);
    }

    public TransactionDataResponse getTransaction(UUID guid) {
        return convertToTransactionData(transactionRepository.findByGuid(guid).orElseThrow(NotFoundException::new));
    }

    public void updateTransaction(UUID guid, Transaction newTransactionData) {
        Optional<Transaction> optionalExistingTransaction = transactionRepository.findByGuid(guid);
        if (optionalExistingTransaction.isPresent()) {
            newTransactionData.setGuid(guid);
            transactionValidator.validateData(newTransactionData);
            Transaction existingTransaction = optionalExistingTransaction.get();
            saveTransactionUpdate(existingTransaction, newTransactionData);
        } else {
            throw new NotFoundException();
        }
    }

    private void saveTransactionUpdate(Transaction existingTransaction, Transaction newTransactionData) {
        existingTransaction.setAmount(newTransactionData.getAmount());
        existingTransaction.setTransactionTimestamp(newTransactionData.getTransactionTimestamp());
        transactionRepository.save(existingTransaction);
    }

    private int calculatePageIndex(int page, int pageSize) {
        long totalItems = transactionRepository.count();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        return Math.max(1, Math.min(page, totalPages));
    }

    public void saveTransactions(List<RawTransaction> rawTransactions, Tid tid) {
        List<Transaction> transactions = refineRawTransactions(rawTransactions);

        transactions.forEach(transaction -> {
            if (!transactionRepository.existsByGuid(transaction.getGuid())) {
                transaction.setTid(tid);
                transactionRepository.save(transaction);
            }
        });
    }

    private List<Transaction> refineRawTransactions(List<RawTransaction> rawTransactions) {
        return rawTransactions.stream().map(
                rawTransaction -> {
                    Transaction transaction = new Transaction();
                    transaction.setGuid(rawTransaction.getGuid());
                    transaction.setAmount(rawTransaction.getAmount());
                    transaction.setCurrency(rawTransaction.getCurrency());
                    transaction.setTrxType(TrxType.valueOf(rawTransaction.getTrxType()));
                    transaction.setInstallmentsNumber(rawTransaction.getInstallmentsNumber());
                    transaction.setInstallmentsCreditor(InstallmentsCreditor.valueOf(rawTransaction.getInstallmentsCreditor()));
                    transaction.setCardBrand(CardBrand.valueOf(rawTransaction.getCardBrand()));
                    transaction.setTransactionTimestamp(rawTransaction.getTransactionTimestamp());
                    transaction.setMaskedPan(rawTransaction.getMaskedPan());
                    transaction.setPinUsed(rawTransaction.getPinUsed());
                    transaction.setResponseCode(rawTransaction.getResponseCode());
                    transaction.setApprovalCode(rawTransaction.getApprovalCode());
                    transaction.setProcessed(false);

                    return transaction;
                }).collect(Collectors.toList());
    }

    public Transaction getDetailedTransaction(UUID guid) {
        return transactionRepository
                .findTransactionWithDetailsByGuid(guid)
                .orElseThrow(NotFoundException::new);
    }

    public List<Transaction> getDetailedTransactions(List<UUID> guids) {
        List<Transaction> transactions = new ArrayList<>();
        Set<UUID> uniqueGuids = new HashSet<>(guids);
        for (UUID guid : uniqueGuids) {
            Optional<Transaction> transaction = transactionRepository.findTransactionWithDetailsByGuid(guid);
            transaction.ifPresent(transactions::add);
        }
        return transactions;
    }
}
