package foi.air.szokpt.transactionmng.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import foi.air.szokpt.transactionmng.enums.CardBrand;
import foi.air.szokpt.transactionmng.enums.InstallmentsCreditor;
import foi.air.szokpt.transactionmng.enums.TrxType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "guid")
    private UUID guid;

    @Column(name = "amount")
    @JsonProperty("amount")
    private BigDecimal amount;

    @Column(name = "currency")
    @JsonProperty("currency")
    private String currency;

    @Column(name = "trx_type")
    @JsonProperty("trx_type")
    @Enumerated(EnumType.STRING)
    private TrxType trxType;

    @Column(name = "installments_number")
    @JsonProperty("installments_number")
    private int installmentsNumber;

    @Column(name = "installments_creditor")
    @JsonProperty("installments_creditor")
    @Enumerated(EnumType.STRING)
    private InstallmentsCreditor installmentsCreditor;

    @Column(name = "card_brand")
    @JsonProperty("card_brand")
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;

    @Column(name = "transaction_timestamp")
    @JsonProperty("transaction_timestamp")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime transactionTimestamp;

    @Column(name = "masked_pan")
    @JsonProperty("masked_pan")
    private String maskedPan;

    @Column(name = "pin_used")
    @JsonProperty("pin_used")
    private boolean pinUsed;

    @Column(name = "response_code")
    @JsonProperty("response_code")
    private String responseCode;

    @Column(name = "approval_code")
    @JsonProperty("approval_code")
    private String approvalCode;

    @Column(name = "processed")
    @JsonProperty("processed")
    private Boolean processed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_tid")
    private Tid tid;

    public Transaction() {
    }

    public Transaction(UUID guid, BigDecimal amount, String currency, TrxType trxType, int installmentsNumber, InstallmentsCreditor installmentsCreditor, CardBrand cardBrand, LocalDateTime transactionTimestamp, String maskedPan, boolean pinUsed, String responseCode, String approvalCode, Boolean processed) {
        this.guid = guid;
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
        this.approvalCode = approvalCode;
        this.processed = processed;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
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

    public int getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(int installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }

    public InstallmentsCreditor getInstallmentsCreditor() {
        return installmentsCreditor;
    }

    public void setInstallmentsCreditor(InstallmentsCreditor installmentsCreditor) {
        this.installmentsCreditor = installmentsCreditor;
    }

    public CardBrand getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(CardBrand cardBrand) {
        this.cardBrand = cardBrand;
    }

    public LocalDateTime getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(LocalDateTime transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    public String getMaskedPan() {
        return maskedPan;
    }

    public void setMaskedPan(String maskedPan) {
        this.maskedPan = maskedPan;
    }

    public boolean isPinUsed() {
        return pinUsed;
    }

    public void setPinUsed(boolean pinUsed) {
        this.pinUsed = pinUsed;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed =  processed;
    }

    public Tid getTid() {
        return tid;
    }

    public void setTid(Tid tid) {
        this.tid = tid;
    }
}
