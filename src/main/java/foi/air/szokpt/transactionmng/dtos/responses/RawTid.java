package foi.air.szokpt.transactionmng.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RawTid {
    @JsonProperty("pos_tid")
    private String posTid;

    @JsonProperty("mcc")
    private String mcc;

    private RawMid mid;

    @JsonProperty("transactions")
    private List<RawTransaction> transactions;

    public RawTid() {
    }

    public RawTid(String posTid, String mcc, List<RawTransaction> transactions) {
        this.posTid = posTid;
        this.mcc = mcc;
        this.transactions = transactions;
    }

    public String getPosTid() {
        return posTid;
    }

    public void setPosTid(String pos_tid) {
        this.posTid = pos_tid;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public RawMid getMid() {
        return mid;
    }

    public void setMid(RawMid mid) {
        this.mid = mid;
    }

    public List<RawTransaction> getTransactions() {
        return transactions;
    }

}
