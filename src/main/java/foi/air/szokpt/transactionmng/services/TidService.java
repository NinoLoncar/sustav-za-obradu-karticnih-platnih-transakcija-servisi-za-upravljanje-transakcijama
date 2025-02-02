package foi.air.szokpt.transactionmng.services;

import foi.air.szokpt.transactionmng.dtos.responses.RawTid;
import foi.air.szokpt.transactionmng.entities.Mid;
import foi.air.szokpt.transactionmng.entities.Tid;
import foi.air.szokpt.transactionmng.repositories.TidRepository;
import org.springframework.stereotype.Service;

@Service
public class TidService {

    private final TidRepository tidRepository;

    public TidService(TidRepository tidRepository) {
        this.tidRepository = tidRepository;
    }

    public Tid saveTid(RawTid rawTid, Mid mid) {
        Tid tid = refineRawTid(rawTid);
        tid.setMid(mid);
        return tidRepository.findById(tid.getPosTid())
                .orElseGet(() -> tidRepository.save(tid));
    }

    private Tid refineRawTid(RawTid rawTid){
        return new Tid(rawTid.getPosTid());
    }

}
