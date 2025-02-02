package foi.air.szokpt.transactionmng.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "merchants")
public class Merchant {

    @Id
    @Column(name = "oib")
    @JsonProperty("oib")
    private String oib;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;


    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Mid> mids;

    public Merchant() {
    }

    public Merchant(String name, String oib) {
        this.name = name;
        this.oib = oib;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Mid> getMids() {
        return mids;
    }

    public void addMid(Mid mid) {
        mids.add(mid);
        mid.setMerchant(this);
    }
}


