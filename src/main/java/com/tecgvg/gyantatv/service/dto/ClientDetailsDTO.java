package com.tecgvg.gyantatv.service.dto;

import com.tecgvg.gyantatv.domain.enumeration.ClientType;
import com.tecgvg.gyantatv.domain.enumeration.RegistrationLevel;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.tecgvg.gyantatv.domain.ClientDetails} entity.
 */
public class ClientDetailsDTO implements Serializable {

    private Long id;

    private String customerID;

    @NotNull
    private String clientName;

    private ClientType clientType;

    private String mobileNo;

    private String email;

    private Integer pincode;

    private String billingAddress;

    private String companyName;

    private String companyContactNo;

    private String website;

    private String gstinNumber;

    private String companyPan;

    @Lob
    private byte[] pancardImage;

    private String pancardImageContentType;
    private String companyTan;

    @Lob
    private byte[] tanImage;

    private String tanImageContentType;

    @Lob
    private byte[] gstCertificateImage;

    private String gstCertificateImageContentType;

    @Lob
    private byte[] cancelledChequeImage;

    private String cancelledChequeImageContentType;

    @Lob
    private byte[] udyogAadharImage;

    private String udyogAadharImageContentType;
    private String description;

    private String nameOfBeneficiary;

    private String accountNumber;

    private String bankName;

    private String accountType;

    private String ifscCode;

    private String registrationCategory;

    private RegistrationLevel registrationLevel;

    private Boolean isApproved;

    private Boolean isActivated;

    private String freeField1;

    private String freeField2;

    private String freeField3;

    private String freeField4;

    private String lastModified;

    private String lastModifiedBy;

    private DistrictDTO district;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContactNo() {
        return companyContactNo;
    }

    public void setCompanyContactNo(String companyContactNo) {
        this.companyContactNo = companyContactNo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGstinNumber() {
        return gstinNumber;
    }

    public void setGstinNumber(String gstinNumber) {
        this.gstinNumber = gstinNumber;
    }

    public String getCompanyPan() {
        return companyPan;
    }

    public void setCompanyPan(String companyPan) {
        this.companyPan = companyPan;
    }

    public byte[] getPancardImage() {
        return pancardImage;
    }

    public void setPancardImage(byte[] pancardImage) {
        this.pancardImage = pancardImage;
    }

    public String getPancardImageContentType() {
        return pancardImageContentType;
    }

    public void setPancardImageContentType(String pancardImageContentType) {
        this.pancardImageContentType = pancardImageContentType;
    }

    public String getCompanyTan() {
        return companyTan;
    }

    public void setCompanyTan(String companyTan) {
        this.companyTan = companyTan;
    }

    public byte[] getTanImage() {
        return tanImage;
    }

    public void setTanImage(byte[] tanImage) {
        this.tanImage = tanImage;
    }

    public String getTanImageContentType() {
        return tanImageContentType;
    }

    public void setTanImageContentType(String tanImageContentType) {
        this.tanImageContentType = tanImageContentType;
    }

    public byte[] getGstCertificateImage() {
        return gstCertificateImage;
    }

    public void setGstCertificateImage(byte[] gstCertificateImage) {
        this.gstCertificateImage = gstCertificateImage;
    }

    public String getGstCertificateImageContentType() {
        return gstCertificateImageContentType;
    }

    public void setGstCertificateImageContentType(String gstCertificateImageContentType) {
        this.gstCertificateImageContentType = gstCertificateImageContentType;
    }

    public byte[] getCancelledChequeImage() {
        return cancelledChequeImage;
    }

    public void setCancelledChequeImage(byte[] cancelledChequeImage) {
        this.cancelledChequeImage = cancelledChequeImage;
    }

    public String getCancelledChequeImageContentType() {
        return cancelledChequeImageContentType;
    }

    public void setCancelledChequeImageContentType(String cancelledChequeImageContentType) {
        this.cancelledChequeImageContentType = cancelledChequeImageContentType;
    }

    public byte[] getUdyogAadharImage() {
        return udyogAadharImage;
    }

    public void setUdyogAadharImage(byte[] udyogAadharImage) {
        this.udyogAadharImage = udyogAadharImage;
    }

    public String getUdyogAadharImageContentType() {
        return udyogAadharImageContentType;
    }

    public void setUdyogAadharImageContentType(String udyogAadharImageContentType) {
        this.udyogAadharImageContentType = udyogAadharImageContentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameOfBeneficiary() {
        return nameOfBeneficiary;
    }

    public void setNameOfBeneficiary(String nameOfBeneficiary) {
        this.nameOfBeneficiary = nameOfBeneficiary;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getRegistrationCategory() {
        return registrationCategory;
    }

    public void setRegistrationCategory(String registrationCategory) {
        this.registrationCategory = registrationCategory;
    }

    public RegistrationLevel getRegistrationLevel() {
        return registrationLevel;
    }

    public void setRegistrationLevel(RegistrationLevel registrationLevel) {
        this.registrationLevel = registrationLevel;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    public String getFreeField1() {
        return freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return freeField4;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDetailsDTO)) {
            return false;
        }

        ClientDetailsDTO clientDetailsDTO = (ClientDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clientDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDetailsDTO{" +
            "id=" + getId() +
            ", customerID='" + getCustomerID() + "'" +
            ", clientName='" + getClientName() + "'" +
            ", clientType='" + getClientType() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", pincode=" + getPincode() +
            ", billingAddress='" + getBillingAddress() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", companyContactNo='" + getCompanyContactNo() + "'" +
            ", website='" + getWebsite() + "'" +
            ", gstinNumber='" + getGstinNumber() + "'" +
            ", companyPan='" + getCompanyPan() + "'" +
            ", pancardImage='" + getPancardImage() + "'" +
            ", companyTan='" + getCompanyTan() + "'" +
            ", tanImage='" + getTanImage() + "'" +
            ", gstCertificateImage='" + getGstCertificateImage() + "'" +
            ", cancelledChequeImage='" + getCancelledChequeImage() + "'" +
            ", udyogAadharImage='" + getUdyogAadharImage() + "'" +
            ", description='" + getDescription() + "'" +
            ", nameOfBeneficiary='" + getNameOfBeneficiary() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", registrationCategory='" + getRegistrationCategory() + "'" +
            ", registrationLevel='" + getRegistrationLevel() + "'" +
            ", isApproved='" + getIsApproved() + "'" +
            ", isActivated='" + getIsActivated() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", district=" + getDistrict() +
            "}";
    }
}
