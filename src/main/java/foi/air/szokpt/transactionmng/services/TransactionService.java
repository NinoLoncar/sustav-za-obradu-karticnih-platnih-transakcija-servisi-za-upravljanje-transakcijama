package foi.air.szokpt.transactionmng.services;

import foi.air.szokpt.transactionmng.dtos.responses.TransactionPageData;
import foi.air.szokpt.transactionmng.entities.Transaction;
import foi.air.szokpt.transactionmng.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    final int pageSize = 15;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionPageData getTransactions(Integer page) {
        if (page == null) return getAllTransactions();
        return getTransactionsByPage(page, pageSize);
    }

    private TransactionPageData getAllTransactions() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        return new TransactionPageData(allTransactions, 0, 0);
    }

    private TransactionPageData getTransactionsByPage(int page, int pageSize) {
        int pageIndex = calculatePageIndex(page, pageSize);
        int pageNumber = pageIndex - 1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Transaction> transactionPage = transactionRepository.findAll(pageable);
        return new TransactionPageData(
                transactionPage.getContent(),
                transactionPage.getNumber() + 1,
                transactionPage.getTotalPages()
        );
    }

    private int calculatePageIndex(int page, int pageSize) {
        long totalItems = transactionRepository.count();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        return Math.max(1, Math.min(page, totalPages));
    }
}
