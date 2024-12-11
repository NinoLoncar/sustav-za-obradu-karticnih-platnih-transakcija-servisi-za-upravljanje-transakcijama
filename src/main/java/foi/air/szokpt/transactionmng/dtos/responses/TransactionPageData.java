package foi.air.szokpt.transactionmng.dtos.responses;

import foi.air.szokpt.transactionmng.entities.Transaction;

import java.util.List;

public class TransactionPageData{
    private List<Transaction> transactions;
    private int currentPage;
    private int totalPages;

    public TransactionPageData(List<Transaction> transactions, int currentPage, int totalPages) {
        this.transactions = transactions;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
