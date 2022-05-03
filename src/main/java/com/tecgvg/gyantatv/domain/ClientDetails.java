package com.tecgvg.gyantatv.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tecgvg.gyantatv.domain.enumeration.ClientType;
import com.tecgvg.gyantatv.domain.enumeration.RegistrationLevel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ClientDetails.
 */
@Entity
@Table(name = "client_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private String customerID;

    @NotNull
    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private ClientType clientType;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "pincode")
    private Integer pincode;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_contact_no")
    private String companyContactNo;

    @Column(name = "website")
    private String website;

    @Column(name = "gstin_number", unique = true)
    private String gstinNumber;

    @Column(name = "company_pan")
    private String companyPan;

    @Lob
    @Column(name = "pancard_image")
    private byte[] pancardImage;

    @Column(name = "pancard_image_content_type")
    private String pancardImageContentType;

    @Column(name = "company_tan")
    private String companyTan;

    @Lob
    @Column(name = "tan_image")
    private byte[] tanImage;

    @Column(name = "tan_image_content_type")
    private String tanImageContentType;

    @Lob
    @Column(name = "gst_certificate_image")
    private byte[] gstCertificateImage;

    @Column(name = "gst_certificate_image_content_type")
    private String gstCertificateImageContentType;

    @Lob
    @Column(name = "cancelled_cheque_image")
    private byte[] cancelledChequeImage;

    @Column(name = "cancelled_cheque_image_content_type")
    private String cancelledChequeImageContentType;

    @Lob
    @Column(name = "udyog_aadhar_image")
    private byte[] udyogAadharImage;

    @Column(name = "udyog_aadhar_image_content_type")
    private String udyogAadharImageContentType;

    @Column(name = "description")
    private String description;

    @Column(name = "name_of_beneficiary")
    private String nameOfBeneficiary;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "registration_category")
    private String registrationCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "registration_level")
    private RegistrationLevel registrationLevel;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "is_activated")
    private Boolean isActivated;

    @Column(name = "free_field_1")
    private String freeField1;

    @Column(name = "free_field_2")
    private String freeField2;

    @Column(name = "free_field_3")
    private String freeField3;

    @Column(name = "free_field_4")
    private String freeField4;

    @Column(name = "last_modified")
    private String lastModified;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @JsonIgnoreProperties(value = { "state", "clientDetails" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private District district;

    @OneToMany(mappedBy = "clientDetails")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "product", "clientDetails" }, allowSetters = true)
    private Set<ProductClientAgreement> productClientAgreements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ClientDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public ClientDetails customerID(String customerID) {
        this.setCustomerID(customerID);
        return this;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getClientName() {
        return this.clientName;
    }

    public ClientDetails clientName(String clientName) {
        this.setClientName(clientName);
        return this;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ClientType getClientType() {
        return this.clientType;
    }

    public ClientDetails clientType(ClientType clientType) {
        this.setClientType(clientType);
        return this;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public ClientDetails mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return this.email;
    }

    public ClientDetails email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPincode() {
        return this.pincode;
    }

    public ClientDetails pincode(Integer pincode) {
        this.setPincode(pincode);
        return this;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getBillingAddress() {
        return this.billingAddress;
    }

    public ClientDetails billingAddress(String billingAddress) {
        this.setBillingAddress(billingAddress);
        return this;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public ClientDetails companyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContactNo() {
        return this.companyContactNo;
    }

    public ClientDetails companyContactNo(String companyContactNo) {
        this.setCompanyContactNo(companyContactNo);
        return this;
    }

    public void setCompanyContactNo(String companyContactNo) {
        this.companyContactNo = companyContactNo;
    }

    public String getWebsite() {
        return this.website;
    }

    public ClientDetails website(String website) {
        this.setWebsite(website);
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGstinNumber() {
        return this.gstinNumber;
    }

    public ClientDetails gstinNumber(String gstinNumber) {
        this.setGstinNumber(gstinNumber);
        return this;
    }

    public void setGstinNumber(String gstinNumber) {
        this.gstinNumber = gstinNumber;
    }

    public String getCompanyPan() {
        return this.companyPan;
    }

    public ClientDetails companyPan(String companyPan) {
        this.setCompanyPan(companyPan);
        return this;
    }

    public void setCompanyPan(String companyPan) {
        this.companyPan = companyPan;
    }

    public byte[] getPancardImage() {
        return this.pancardImage;
    }

    public ClientDetails pancardImage(byte[] pancardImage) {
        this.setPancardImage(pancardImage);
        return this;
    }

    public void setPancardImage(byte[] pancardImage) {
        this.pancardImage = pancardImage;
    }

    public String getPancardImageContentType() {
        return this.pancardImageContentType;
    }

    public ClientDetails pancardImageContentType(String pancardImageContentType) {
        this.pancardImageContentType = pancardImageContentType;
        return this;
    }

    public void setPancardImageContentType(String pancardImageContentType) {
        this.pancardImageContentType = pancardImageContentType;
    }

    public String getCompanyTan() {
        return this.companyTan;
    }

    public ClientDetails companyTan(String companyTan) {
        this.setCompanyTan(companyTan);
        return this;
    }

    public void setCompanyTan(String companyTan) {
        this.companyTan = companyTan;
    }

    public byte[] getTanImage() {
        return this.tanImage;
    }

    public ClientDetails tanImage(byte[] tanImage) {
        this.setTanImage(tanImage);
        return this;
    }

    public void setTanImage(byte[] tanImage) {
        this.tanImage = tanImage;
    }

    public String getTanImageContentType() {
        return this.tanImageContentType;
    }

    public ClientDetails tanImageContentType(String tanImageContentType) {
        this.tanImageContentType = tanImageContentType;
        return this;
    }

    public void setTanImageContentType(String tanImageContentType) {
        this.tanImageContentType = tanImageContentType;
    }

    public byte[] getGstCertificateImage() {
        return this.gstCertificateImage;
    }

    public ClientDetails gstCertificateImage(byte[] gstCertificateImage) {
        this.setGstCertificateImage(gstCertificateImage);
        return this;
    }

    public void setGstCertificateImage(byte[] gstCertificateImage) {
        this.gstCertificateImage = gstCertificateImage;
    }

    public String getGstCertificateImageContentType() {
        return this.gstCertificateImageContentType;
    }

    public ClientDetails gstCertificateImageContentType(String gstCertificateImageContentType) {
        this.gstCertificateImageContentType = gstCertificateImageContentType;
        return this;
    }

    public void setGstCertificateImageContentType(String gstCertificateImageContentType) {
        this.gstCertificateImageContentType = gstCertificateImageContentType;
    }

    public byte[] getCancelledChequeImage() {
        return this.cancelledChequeImage;
    }

    public ClientDetails cancelledChequeImage(byte[] cancelledChequeImage) {
        this.setCancelledChequeImage(cancelledChequeImage);
        return this;
    }

    public void setCancelledChequeImage(byte[] cancelledChequeImage) {
        this.cancelledChequeImage = cancelledChequeImage;
    }

    public String getCancelledChequeImageContentType() {
        return this.cancelledChequeImageContentType;
    }

    public ClientDetails cancelledChequeImageContentType(String cancelledChequeImageContentType) {
        this.cancelledChequeImageContentType = cancelledChequeImageContentType;
        return this;
    }

    public void setCancelledChequeImageContentType(String cancelledChequeImageContentType) {
        this.cancelledChequeImageContentType = cancelledChequeImageContentType;
    }

    public byte[] getUdyogAadharImage() {
        return this.udyogAadharImage;
    }

    public ClientDetails udyogAadharImage(byte[] udyogAadharImage) {
        this.setUdyogAadharImage(udyogAadharImage);
        return this;
    }

    public void setUdyogAadharImage(byte[] udyogAadharImage) {
        this.udyogAadharImage = udyogAadharImage;
    }

    public String getUdyogAadharImageContentType() {
        return this.udyogAadharImageContentType;
    }

    public ClientDetails udyogAadharImageContentType(String udyogAadharImageContentType) {
        this.udyogAadharImageContentType = udyogAadharImageContentType;
        return this;
    }

    public void setUdyogAadharImageContentType(String udyogAadharImageContentType) {
        this.udyogAadharImageContentType = udyogAadharImageContentType;
    }

    public String getDescription() {
        return this.description;
    }

    public ClientDetails description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameOfBeneficiary() {
        return this.nameOfBeneficiary;
    }

    public ClientDetails nameOfBeneficiary(String nameOfBeneficiary) {
        this.setNameOfBeneficiary(nameOfBeneficiary);
        return this;
    }

    public void setNameOfBeneficiary(String nameOfBeneficiary) {
        this.nameOfBeneficiary = nameOfBeneficiary;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public ClientDetails accountNumber(String accountNumber) {
        this.setAccountNumber(accountNumber);
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return this.bankName;
    }

    public ClientDetails bankName(String bankName) {
        this.setBankName(bankName);
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public ClientDetails accountType(String accountType) {
        this.setAccountType(accountType);
        return this;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public ClientDetails ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getRegistrationCategory() {
        return this.registrationCategory;
    }

    public ClientDetails registrationCategory(String registrationCategory) {
        this.setRegistrationCategory(registrationCategory);
        return this;
    }

    public void setRegistrationCategory(String registrationCategory) {
        this.registrationCategory = registrationCategory;
    }

    public RegistrationLevel getRegistrationLevel() {
        return this.registrationLevel;
    }

    public ClientDetails registrationLevel(RegistrationLevel registrationLevel) {
        this.setRegistrationLevel(registrationLevel);
        return this;
    }

    public void setRegistrationLevel(RegistrationLevel registrationLevel) {
        this.registrationLevel = registrationLevel;
    }

    public Boolean getIsApproved() {
        return this.isApproved;
    }

    public ClientDetails isApproved(Boolean isApproved) {
        this.setIsApproved(isApproved);
        return this;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Boolean getIsActivated() {
        return this.isActivated;
    }

    public ClientDetails isActivated(Boolean isActivated) {
        this.setIsActivated(isActivated);
        return this;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public ClientDetails freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public ClientDetails freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public ClientDetails freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public ClientDetails freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getLastModified() {
        return this.lastModified;
    }

    public ClientDetails lastModified(String lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public ClientDetails lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public ClientDetails district(District district) {
        this.setDistrict(district);
        return this;
    }

    public Set<ProductClientAgreement> getProductClientAgreements() {
        return this.productClientAgreements;
    }

    public void setProductClientAgreements(Set<ProductClientAgreement> productClientAgreements) {
        if (this.productClientAgreements != null) {
            this.productClientAgreements.forEach(i -> i.setClientDetails(null));
        }
        if (productClientAgreements != null) {
            productClientAgreements.forEach(i -> i.setClientDetails(this));
        }
        this.productClientAgreements = productClientAgreements;
    }

    public ClientDetails productClientAgreements(Set<ProductClientAgreement> productClientAgreements) {
        this.setProductClientAgreements(productClientAgreements);
        return this;
    }

    public ClientDetails addProductClientAgreement(ProductClientAgreement productClientAgreement) {
        this.productClientAgreements.add(productClientAgreement);
        productClientAgreement.setClientDetails(this);
        return this;
    }

    public ClientDetails removeProductClientAgreement(ProductClientAgreement productClientAgreement) {
        this.productClientAgreements.remove(productClientAgreement);
        productClientAgreement.setClientDetails(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDetails)) {
            return false;
        }
        return id != null && id.equals(((ClientDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDetails{" +
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
            ", pancardImageContentType='" + getPancardImageContentType() + "'" +
            ", companyTan='" + getCompanyTan() + "'" +
            ", tanImage='" + getTanImage() + "'" +
            ", tanImageContentType='" + getTanImageContentType() + "'" +
            ", gstCertificateImage='" + getGstCertificateImage() + "'" +
            ", gstCertificateImageContentType='" + getGstCertificateImageContentType() + "'" +
            ", cancelledChequeImage='" + getCancelledChequeImage() + "'" +
            ", cancelledChequeImageContentType='" + getCancelledChequeImageContentType() + "'" +
            ", udyogAadharImage='" + getUdyogAadharImage() + "'" +
            ", udyogAadharImageContentType='" + getUdyogAadharImageContentType() + "'" +
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
            "}";
    }
}
