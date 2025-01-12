package foi.air.szokpt.transactionmng.services;

import foi.air.szokpt.transactionmng.entities.Merchant;
import foi.air.szokpt.transactionmng.entities.RawMerchant;
import foi.air.szokpt.transactionmng.repositories.MerchantRepository;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Merchant saveMerchant(RawMerchant rawMerchant) {
        Merchant merchant = refineRawMerchant(rawMerchant);

        return merchantRepository.findByOib(merchant.getOib())
                .orElseGet(() -> merchantRepository.save(merchant));
    }

    private Merchant refineRawMerchant(RawMerchant rawMerchant){
        return  new Merchant(rawMerchant.getName(),rawMerchant.getOib());
    }
}
