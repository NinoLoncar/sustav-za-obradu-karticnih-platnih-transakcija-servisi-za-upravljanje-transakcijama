package foi.air.szokpt.transactionmng.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import foi.air.szokpt.transactionmng.enums.CardBrand;
import foi.air.szokpt.transactionmng.enums.InstallmentsCreditor;
import foi.air.szokpt.transactionmng.enums.TrxType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount", nullable = false, precision = 20, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "trx_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TrxType trxType;

    @Column(name = "installments_number", nullable = false)
    private Integer installmentsNumber;

    @Column(name = "installments_creditor", nullable = false)
    @Enumerated(EnumType.STRING)
    private InstallmentsCreditor installmentsCreditor;

    @Column(name = "card_brand", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;

    @Column(name = "transaction_timestamp", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Timestamp transactionTimestamp;

    @Column(name = "masked_pan", nullable = false)
    private String maskedPan;

    @Column(name = "pin_used", nullable = false)
    private Boolean pinUsed;

    @Column(name = "response_code", nullable = false)
    private String responseCode;

    @Column(name = "processed", nullable = false)
    private Boolean processed;

    public Transaction() {}

    public Transaction(BigDecimal amount, String currency,
                       TrxType trxType, Integer installmentsNumber, InstallmentsCreditor installmentsCreditor,
                       CardBrand cardBrand, Timestamp transactionTimestamp,
                       String maskedPan, Boolean pinUsed, String responseCode, Boolean processed) {
        this.amount = amount;
        this.currency = currency;
        this.trxType = trxType;
        this.installmentsNumber = installmentsNumber;
        this.installmentsCreditor = installmentsCreditor;
        this.cardBrand = cardBrand;
        this.transactionTimestamp = transactionTimestamp;
        this.maskedPan = maskedPan;
        this.pinUsed = pinUsed;
        this.responseCode = responseCode;
        this.processed = processed;
    }

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

    public TrxType getTrxType() {
        return trxType;
    }

    public void setTrxType(TrxType trxType) {
        this.trxType = trxType;
    }

    public Integer getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(Integer installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }

    public InstallmentsCreditor getInstallmentsCreditor() {
        return installmentsCreditor;
    }

    public void setInstallmentsNumber(InstallmentsCreditor installmentsCreditor) {
        this.installmentsCreditor = installmentsCreditor;
    }

    public CardBrand getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(CardBrand cardBrand) {
        this.cardBrand = cardBrand;
    }

    public Timestamp getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(Timestamp transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    public String getMaskedPan() {
        return maskedPan;
    }

    public void setMaskedPan(String maskedPan) {
        this.maskedPan = maskedPan;
    }

    public Boolean getPinUsed() {
        return pinUsed;
    }

    public void setPinUsed(Boolean pinUsed) {
        this.pinUsed = pinUsed;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean wasProcessed) {
        this.processed = wasProcessed;
    }
}
