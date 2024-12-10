package foi.air.szokpt.transactionmng.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "trx_type", nullable = false)
    private String trxType;

    @Column(name = "card_brand", nullable = false)
    private String cardBrand;

    @Column(name = "transaction_timestamp", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Timestamp transactionTimestamp;

    @Column(name = "response_code", nullable = false)
    private String responseCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public Timestamp getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(Timestamp transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
