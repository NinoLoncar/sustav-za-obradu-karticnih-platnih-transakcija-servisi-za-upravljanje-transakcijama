package foi.air.szokpt.transactionmng.services;

import foi.air.szokpt.transactionmng.entities.Merchant;
import foi.air.szokpt.transactionmng.entities.Mid;
import foi.air.szokpt.transactionmng.entities.RawMid;
import foi.air.szokpt.transactionmng.repositories.MidRepository;
import org.springframework.stereotype.Service;

@Service
public class MidService {

    private final MidRepository midRepository;

    public MidService(MidRepository midRepository) {
        this.midRepository = midRepository;
    }

    public Mid saveMid(RawMid rawMid, Merchant merchant) {
        Mid mid = refineRawMid(rawMid);
        mid.setMerchant(merchant);
        return midRepository.findByPosMid(mid.getPosMid())
                .orElseGet(() -> midRepository.save(mid));
    }

    private Mid refineRawMid(RawMid rawMid){
        return new Mid(
                rawMid.getPosMid(),
                rawMid.getSalePointName(),
                rawMid.getCity(),
                rawMid.getStateCode(),
                rawMid.getTypeCode(),
                rawMid.getLocationCode(),
                rawMid.getPostalCode(),
                rawMid.getCountryCode(),
                rawMid.getSecurityCode()
        );
    }
}

