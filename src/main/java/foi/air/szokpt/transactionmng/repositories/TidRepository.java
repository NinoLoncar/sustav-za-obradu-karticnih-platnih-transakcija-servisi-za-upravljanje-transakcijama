package foi.air.szokpt.transactionmng.repositories;

import foi.air.szokpt.transactionmng.entities.Tid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TidRepository extends JpaRepository<Tid, String> {
}
