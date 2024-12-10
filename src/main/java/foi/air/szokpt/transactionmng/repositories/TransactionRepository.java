package foi.air.szokpt.transactionmng.repositories;

import foi.air.szokpt.transactionmng.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
        Page<Transaction> findAll(Pageable pageable);
}
