package foi.air.szokpt.transactionmng.repositories;

import foi.air.szokpt.transactionmng.entities.Mid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MidRepository extends JpaRepository<Mid, String> {
    Optional<Mid> findByPosMid(String posMid);
}
