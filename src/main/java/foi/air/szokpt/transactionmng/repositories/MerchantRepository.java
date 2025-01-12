package foi.air.szokpt.transactionmng.repositories;

import foi.air.szokpt.transactionmng.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    Optional<Merchant> findByOib(String oib);
}
