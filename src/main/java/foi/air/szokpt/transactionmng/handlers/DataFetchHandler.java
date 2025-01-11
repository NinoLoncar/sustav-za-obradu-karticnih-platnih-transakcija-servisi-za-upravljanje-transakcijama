package foi.air.szokpt.transactionmng.handlers;

import foi.air.szokpt.transactionmng.clients.RawTransactionDataClient;
import foi.air.szokpt.transactionmng.entities.*;
import foi.air.szokpt.transactionmng.services.MerchantService;
import foi.air.szokpt.transactionmng.services.MidService;
import foi.air.szokpt.transactionmng.services.TidService;
import foi.air.szokpt.transactionmng.services.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataFetchHandler {

    private final RawTransactionDataClient apiClient;
    private final MerchantService merchantService;
    private final MidService midService;
    private final TidService tidService;
    private final TransactionService transactionService;

    public DataFetchHandler(
            RawTransactionDataClient apiClient,
            MerchantService merchantService,
            MidService midService,
            TidService tidService,
            TransactionService transactionService) {
        this.apiClient = apiClient;
        this.merchantService = merchantService;
        this.midService = midService;
        this.tidService = tidService;
        this.transactionService = transactionService;
    }

    public void fetchData() {

        List<RawMerchant> merchants = apiClient.fetchRawData();
        for (RawMerchant rawMerchant : merchants) {

            List<RawMid> rawMids = rawMerchant.getMids();

            Merchant savedMerchant = merchantService.saveMerchant(rawMerchant);

            for (RawMid rawMid : rawMids) {

                List<RawTid> rawTids = rawMid.getTids();

                Mid savedMid = midService.saveMid(rawMid, savedMerchant);

                for (RawTid rawTid : rawTids) {

                    List<RawTransaction> rawTransactions = rawTid.getTransactions();

                    Tid savedTid = tidService.saveTid(rawTid, savedMid);

                    transactionService.saveTransactions(rawTransactions, savedTid);

                }

            }
        }
    }
}
