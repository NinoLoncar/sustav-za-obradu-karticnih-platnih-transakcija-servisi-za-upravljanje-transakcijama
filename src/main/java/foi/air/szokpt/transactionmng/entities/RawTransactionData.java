package foi.air.szokpt.transactionmng.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RawTransactionData {

    private String posTid;
    private String hostTid;
    private String hostMid;
    private BigDecimal amount;
    private String currency;
    private String trxType;
    private Integer installmentsNumber;
    private String installmentsCreditor;
    private Integer bankHostId;
    private String cardBrand;
    private LocalDateTime transactionTimestamp;
    private String maskedPan;
    private Boolean pinUsed;
    private String responseCode;
    private String approvalCode;
    private String rrn;
    private String emv1;
    private String emv2;
    private String psn;
    private String guid;

    public RawTransactionData(String posTid, String hostTid, String hostMid, BigDecimal amount, String currency,
                              String trxType, Integer installmentsNumber, String installmentsCreditor, Integer bankHostId,
                              String cardBrand, LocalDateTime transactionTimestamp, String maskedPan, Boolean pinUsed,
                              String responseCode, String approvalCode, String rrn, String emv1, String emv2, String psn, String guid) {
        this.posTid = posTid;
        this.hostTid = hostTid;
        this.hostMid = hostMid;
        this.amount = amount;
        this.currency = currency;
        this.trxType = trxType;
        this.installmentsNumber = installmentsNumber;
        this.installmentsCreditor = installmentsCreditor;
        this.bankHostId = bankHostId;
        this.cardBrand = cardBrand;
        this.transactionTimestamp = transactionTimestamp;
        this.maskedPan = maskedPan;
        this.pinUsed = pinUsed;
        this.responseCode = responseCode;
        this.approvalCode = approvalCode;
        this.rrn = rrn;
        this.emv1 = emv1;
        this.emv2 = emv2;
        this.psn = psn;
        this.guid = guid;
    }

    public String getPosTid() {
        return posTid;
    }

    public void setPosTid(String posTid) {
        this.posTid = posTid;
    }

    public String getHostTid() {
        return hostTid;
    }

    public void setHostTid(String hostTid) {
        this.hostTid = hostTid;
    }

    public String getHostMid() {
        return hostMid;
    }

    public void setHostMid(String hostMid) {
        this.hostMid = hostMid;
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

    public Integer getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(Integer installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }

    public String getInstallmentsCreditor() {
        return installmentsCreditor;
    }

    public void setInstallmentsCreditor(String installmentsCreditor) {
        this.installmentsCreditor = installmentsCreditor;
    }

    public Integer getBankHostId() {
        return bankHostId;
    }

    public void setBankHostId(Integer bankHostId) {
        this.bankHostId = bankHostId;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
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

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getEmv1() {
        return emv1;
    }

    public void setEmv1(String emv1) {
        this.emv1 = emv1;
    }

    public String getEmv2() {
        return emv2;
    }

    public void setEmv2(String emv2) {
        this.emv2 = emv2;
    }

    public String getPsn() {
        return psn;
    }

    public void setPsn(String psn) {
        this.psn = psn;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}


