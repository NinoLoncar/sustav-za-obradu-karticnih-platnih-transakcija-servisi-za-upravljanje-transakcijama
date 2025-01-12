package foi.air.szokpt.transactionmng.clients;

import foi.air.szokpt.transactionmng.dtos.responses.RawMerchant;
import foi.air.szokpt.transactionmng.dtos.responses.RawTransactionsResponseData;
import foi.air.szokpt.transactionmng.exceptions.ExternalServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RawTransactionDataClient {
    private final RestTemplate restTemplate;

    @Value("${RawTransaction.api.base.url}")
    private String baseUrl;

    public RawTransactionDataClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<RawMerchant> fetchRawData() {
        String url = baseUrl + "/raw-data";
        try {
            ResponseEntity<RawTransactionsResponseData> response = restTemplate.getForEntity(url, RawTransactionsResponseData.class);
            return Optional.ofNullable(response.getBody())
                    .map(RawTransactionsResponseData::getMerchants)
                    .orElse(new ArrayList<>());
        }catch(Exception e){
            throw new ExternalServiceException(e.getMessage());
        }
    }
}
