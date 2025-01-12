package foi.air.szokpt.transactionmng.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tids")
public class Tid {
    @Id
    @JsonProperty("pos_tid")
    @Column(name = "pos_tid")
    private String posTid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_mid")
    private Mid mid;

    @OneToMany(mappedBy = "tid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public Tid() {
    }

    public Tid(String posTid) {
        this.posTid = posTid;
    }

    public String getPosTid() {
        return posTid;
    }

    public void setPosTid(String pos_tid) {
        this.posTid = pos_tid;
    }

    public Mid getMid() {
        return mid;
    }

    public void setMid(Mid mid) {
        this.mid = mid;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
