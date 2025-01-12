package foi.air.szokpt.transactionmng.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RawTransactionsResponseData {
    @JsonProperty("data")
    private List<RawMerchant> merchants;

    public List<RawMerchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<RawMerchant> merchants) {
        this.merchants = merchants;
    }

}
