package com.informaticsint.adminunit.dto;

import com.informaticsint.common.dto.DescriptionDto;
import com.informaticsint.starter.data.Dto;

import java.util.Objects;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

public class SecretariatDto extends Dto {

    private String id;
    private String code;
    private String districtCode;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String phoneNumber;
    private String mobileNumber;
    private String faxNumber;
    private String email;
    private String lifeCode;
    private DescriptionDto description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLifeCode() {
        return lifeCode;
    }

    public void setLifeCode(String lifeCode) {
        this.lifeCode = lifeCode;
    }

    public DescriptionDto getDescription() {
        return description;
    }

    public void setDescription(DescriptionDto description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecretariatDto that = (SecretariatDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(code, that.code)) return false;
        if (!Objects.equals(districtCode, that.districtCode)) return false;
        if (!Objects.equals(addressLine1, that.addressLine1)) return false;
        if (!Objects.equals(addressLine2, that.addressLine2)) return false;
        if (!Objects.equals(postalCode, that.postalCode)) return false;
        if (!Objects.equals(phoneNumber, that.phoneNumber)) return false;
        if (!Objects.equals(mobileNumber, that.mobileNumber)) return false;
        if (!Objects.equals(faxNumber, that.faxNumber)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(lifeCode, that.lifeCode)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (districtCode != null ? districtCode.hashCode() : 0);
        result = 31 * result + (addressLine1 != null ? addressLine1.hashCode() : 0);
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (faxNumber != null ? faxNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lifeCode != null ? lifeCode.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
