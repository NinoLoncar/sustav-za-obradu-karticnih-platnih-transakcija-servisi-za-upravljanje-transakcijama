package foi.air.szokpt.transactionmng.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class RawMid {
    @JsonProperty("pos_mid")
    private String posMid;

    @JsonProperty("sale_point_name")
    private String salePointName;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state_code")
    private String stateCode;

    @JsonProperty("type_code")
    private String typeCode;

    @JsonProperty("location_code")
    private String locationCode;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("security_code")
    private String securityCode;

    @JsonProperty("tids")
    private List<RawTid> tids;

    private RawMerchant merchant;

    public RawMid() {
        tids = new ArrayList<>();
    }

    public RawMid(String posMid, String salePointName, String city, String stateCode, String typeCode, String locationCode, String postalCode, String countryCode, String securityCode,List<RawTid> tids) {
        this.posMid = posMid;
        this.salePointName = salePointName;
        this.city = city;
        this.stateCode = stateCode;
        this.typeCode = typeCode;
        this.locationCode = locationCode;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
        this.securityCode = securityCode;
        this.tids = tids;
    }

    public String getPosMid() {
        return posMid;
    }

    public void setPosMid(String posMid) {
        this.posMid = posMid;
    }

    public String getSalePointName() {
        return salePointName;
    }

    public void setSalePointName(String salePointName) {
        this.salePointName = salePointName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public RawMerchant getMerchant() {
        return merchant;
    }

    public void setMerchant(RawMerchant merchant) {
        this.merchant = merchant;
    }

    public List<RawTid> getTids() {
        return tids;
    }

    public void setTids(List<RawTid> tids) {
        this.tids = tids;
    }
}
