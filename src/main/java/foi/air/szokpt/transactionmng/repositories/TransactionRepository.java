package foi.air.szokpt.transactionmng.repositories;

import foi.air.szokpt.transactionmng.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>,
        JpaSpecificationExecutor<Transaction> {
    @NonNull
    Page<Transaction> findAll(@NonNull Pageable pageable);

    Optional<Transaction> findById(int id);

    Optional<Transaction> findByGuid(UUID guid);

    boolean existsByGuid(UUID guid);

    @Query("""
        SELECT t 
        FROM Transaction t 
        JOIN FETCH t.tid tid
        JOIN FETCH tid.mid mid
        JOIN FETCH mid.merchant merchant
        WHERE t.guid = :guid
    """)
    Optional<Transaction> findTransactionWithDetailsByGuid(@Param("guid") UUID guid);

}
