package foi.air.szokpt.transactionmng.dtos.responses;

import java.util.List;

public class TransactionPageData{
    private List<TransactionDataResponse> transactions;
    private int currentPage;
    private int totalPages;

    public TransactionPageData(List<TransactionDataResponse> transactions, int currentPage, int totalPages) {
        this.transactions = transactions;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<TransactionDataResponse> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDataResponse> transactions) {
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
