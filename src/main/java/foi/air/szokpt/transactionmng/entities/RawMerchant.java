package foi.air.szokpt.transactionmng.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RawMerchant {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("oib")
    private String oib;

    @JsonProperty("mids")
    private List<RawMid> mids;

    public RawMerchant() {
        mids = new ArrayList<>();
    }

    public RawMerchant(Integer id, String name, String oib, List<RawMid> mids) {
        this.id = id;
        this.name = name;
        this.oib = oib;
        this.mids = mids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public List<RawMid> getMids() {
        return mids;
    }

    public void setMids(List<RawMid> mids) {
        this.mids = mids;
    }
}
