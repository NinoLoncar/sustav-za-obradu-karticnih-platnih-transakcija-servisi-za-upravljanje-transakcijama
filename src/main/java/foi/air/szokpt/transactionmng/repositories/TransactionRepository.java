package foi.air.szokpt.transactionmng.repositories;

import foi.air.szokpt.transactionmng.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @NonNull
    Page<Transaction> findAll(@NonNull Pageable pageable);
}
