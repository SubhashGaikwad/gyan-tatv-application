package com.tecgvg.gyantatv.service.criteria;

import com.tecgvg.gyantatv.domain.enumeration.ClientType;
import com.tecgvg.gyantatv.domain.enumeration.RegistrationLevel;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.tecgvg.gyantatv.domain.ClientDetails} entity. This class is used
 * in {@link com.tecgvg.gyantatv.web.rest.ClientDetailsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /client-details?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ClientDetailsCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ClientType
     */
    public static class ClientTypeFilter extends Filter<ClientType> {

        public ClientTypeFilter() {}

        public ClientTypeFilter(ClientTypeFilter filter) {
            super(filter);
        }

        @Override
        public ClientTypeFilter copy() {
            return new ClientTypeFilter(this);
        }
    }

    /**
     * Class for filtering RegistrationLevel
     */
    public static class RegistrationLevelFilter extends Filter<RegistrationLevel> {

        public RegistrationLevelFilter() {}

        public RegistrationLevelFilter(RegistrationLevelFilter filter) {
            super(filter);
        }

        @Override
        public RegistrationLevelFilter copy() {
            return new RegistrationLevelFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter customerID;

    private StringFilter clientName;

    private ClientTypeFilter clientType;

    private StringFilter mobileNo;

    private StringFilter email;

    private IntegerFilter pincode;

    private StringFilter billingAddress;

    private StringFilter companyName;

    private StringFilter companyContactNo;

    private StringFilter website;

    private StringFilter gstinNumber;

    private StringFilter companyPan;

    private StringFilter companyTan;

    private StringFilter description;

    private StringFilter nameOfBeneficiary;

    private StringFilter accountNumber;

    private StringFilter bankName;

    private StringFilter accountType;

    private StringFilter ifscCode;

    private StringFilter registrationCategory;

    private RegistrationLevelFilter registrationLevel;

    private BooleanFilter isApproved;

    private BooleanFilter isActivated;

    private StringFilter freeField1;

    private StringFilter freeField2;

    private StringFilter freeField3;

    private StringFilter freeField4;

    private StringFilter lastModified;

    private StringFilter lastModifiedBy;

    private LongFilter districtId;

    private LongFilter productClientAgreementId;

    private Boolean distinct;

    public ClientDetailsCriteria() {}

    public ClientDetailsCriteria(ClientDetailsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.customerID = other.customerID == null ? null : other.customerID.copy();
        this.clientName = other.clientName == null ? null : other.clientName.copy();
        this.clientType = other.clientType == null ? null : other.clientType.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.pincode = other.pincode == null ? null : other.pincode.copy();
        this.billingAddress = other.billingAddress == null ? null : other.billingAddress.copy();
        this.companyName = other.companyName == null ? null : other.companyName.copy();
        this.companyContactNo = other.companyContactNo == null ? null : other.companyContactNo.copy();
        this.website = other.website == null ? null : other.website.copy();
        this.gstinNumber = other.gstinNumber == null ? null : other.gstinNumber.copy();
        this.companyPan = other.companyPan == null ? null : other.companyPan.copy();
        this.companyTan = other.companyTan == null ? null : other.companyTan.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.nameOfBeneficiary = other.nameOfBeneficiary == null ? null : other.nameOfBeneficiary.copy();
        this.accountNumber = other.accountNumber == null ? null : other.accountNumber.copy();
        this.bankName = other.bankName == null ? null : other.bankName.copy();
        this.accountType = other.accountType == null ? null : other.accountType.copy();
        this.ifscCode = other.ifscCode == null ? null : other.ifscCode.copy();
        this.registrationCategory = other.registrationCategory == null ? null : other.registrationCategory.copy();
        this.registrationLevel = other.registrationLevel == null ? null : other.registrationLevel.copy();
        this.isApproved = other.isApproved == null ? null : other.isApproved.copy();
        this.isActivated = other.isActivated == null ? null : other.isActivated.copy();
        this.freeField1 = other.freeField1 == null ? null : other.freeField1.copy();
        this.freeField2 = other.freeField2 == null ? null : other.freeField2.copy();
        this.freeField3 = other.freeField3 == null ? null : other.freeField3.copy();
        this.freeField4 = other.freeField4 == null ? null : other.freeField4.copy();
        this.lastModified = other.lastModified == null ? null : other.lastModified.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.districtId = other.districtId == null ? null : other.districtId.copy();
        this.productClientAgreementId = other.productClientAgreementId == null ? null : other.productClientAgreementId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ClientDetailsCriteria copy() {
        return new ClientDetailsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCustomerID() {
        return customerID;
    }

    public StringFilter customerID() {
        if (customerID == null) {
            customerID = new StringFilter();
        }
        return customerID;
    }

    public void setCustomerID(StringFilter customerID) {
        this.customerID = customerID;
    }

    public StringFilter getClientName() {
        return clientName;
    }

    public StringFilter clientName() {
        if (clientName == null) {
            clientName = new StringFilter();
        }
        return clientName;
    }

    public void setClientName(StringFilter clientName) {
        this.clientName = clientName;
    }

    public ClientTypeFilter getClientType() {
        return clientType;
    }

    public ClientTypeFilter clientType() {
        if (clientType == null) {
            clientType = new ClientTypeFilter();
        }
        return clientType;
    }

    public void setClientType(ClientTypeFilter clientType) {
        this.clientType = clientType;
    }

    public StringFilter getMobileNo() {
        return mobileNo;
    }

    public StringFilter mobileNo() {
        if (mobileNo == null) {
            mobileNo = new StringFilter();
        }
        return mobileNo;
    }

    public void setMobileNo(StringFilter mobileNo) {
        this.mobileNo = mobileNo;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public IntegerFilter getPincode() {
        return pincode;
    }

    public IntegerFilter pincode() {
        if (pincode == null) {
            pincode = new IntegerFilter();
        }
        return pincode;
    }

    public void setPincode(IntegerFilter pincode) {
        this.pincode = pincode;
    }

    public StringFilter getBillingAddress() {
        return billingAddress;
    }

    public StringFilter billingAddress() {
        if (billingAddress == null) {
            billingAddress = new StringFilter();
        }
        return billingAddress;
    }

    public void setBillingAddress(StringFilter billingAddress) {
        this.billingAddress = billingAddress;
    }

    public StringFilter getCompanyName() {
        return companyName;
    }

    public StringFilter companyName() {
        if (companyName == null) {
            companyName = new StringFilter();
        }
        return companyName;
    }

    public void setCompanyName(StringFilter companyName) {
        this.companyName = companyName;
    }

    public StringFilter getCompanyContactNo() {
        return companyContactNo;
    }

    public StringFilter companyContactNo() {
        if (companyContactNo == null) {
            companyContactNo = new StringFilter();
        }
        return companyContactNo;
    }

    public void setCompanyContactNo(StringFilter companyContactNo) {
        this.companyContactNo = companyContactNo;
    }

    public StringFilter getWebsite() {
        return website;
    }

    public StringFilter website() {
        if (website == null) {
            website = new StringFilter();
        }
        return website;
    }

    public void setWebsite(StringFilter website) {
        this.website = website;
    }

    public StringFilter getGstinNumber() {
        return gstinNumber;
    }

    public StringFilter gstinNumber() {
        if (gstinNumber == null) {
            gstinNumber = new StringFilter();
        }
        return gstinNumber;
    }

    public void setGstinNumber(StringFilter gstinNumber) {
        this.gstinNumber = gstinNumber;
    }

    public StringFilter getCompanyPan() {
        return companyPan;
    }

    public StringFilter companyPan() {
        if (companyPan == null) {
            companyPan = new StringFilter();
        }
        return companyPan;
    }

    public void setCompanyPan(StringFilter companyPan) {
        this.companyPan = companyPan;
    }

    public StringFilter getCompanyTan() {
        return companyTan;
    }

    public StringFilter companyTan() {
        if (companyTan == null) {
            companyTan = new StringFilter();
        }
        return companyTan;
    }

    public void setCompanyTan(StringFilter companyTan) {
        this.companyTan = companyTan;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getNameOfBeneficiary() {
        return nameOfBeneficiary;
    }

    public StringFilter nameOfBeneficiary() {
        if (nameOfBeneficiary == null) {
            nameOfBeneficiary = new StringFilter();
        }
        return nameOfBeneficiary;
    }

    public void setNameOfBeneficiary(StringFilter nameOfBeneficiary) {
        this.nameOfBeneficiary = nameOfBeneficiary;
    }

    public StringFilter getAccountNumber() {
        return accountNumber;
    }

    public StringFilter accountNumber() {
        if (accountNumber == null) {
            accountNumber = new StringFilter();
        }
        return accountNumber;
    }

    public void setAccountNumber(StringFilter accountNumber) {
        this.accountNumber = accountNumber;
    }

    public StringFilter getBankName() {
        return bankName;
    }

    public StringFilter bankName() {
        if (bankName == null) {
            bankName = new StringFilter();
        }
        return bankName;
    }

    public void setBankName(StringFilter bankName) {
        this.bankName = bankName;
    }

    public StringFilter getAccountType() {
        return accountType;
    }

    public StringFilter accountType() {
        if (accountType == null) {
            accountType = new StringFilter();
        }
        return accountType;
    }

    public void setAccountType(StringFilter accountType) {
        this.accountType = accountType;
    }

    public StringFilter getIfscCode() {
        return ifscCode;
    }

    public StringFilter ifscCode() {
        if (ifscCode == null) {
            ifscCode = new StringFilter();
        }
        return ifscCode;
    }

    public void setIfscCode(StringFilter ifscCode) {
        this.ifscCode = ifscCode;
    }

    public StringFilter getRegistrationCategory() {
        return registrationCategory;
    }

    public StringFilter registrationCategory() {
        if (registrationCategory == null) {
            registrationCategory = new StringFilter();
        }
        return registrationCategory;
    }

    public void setRegistrationCategory(StringFilter registrationCategory) {
        this.registrationCategory = registrationCategory;
    }

    public RegistrationLevelFilter getRegistrationLevel() {
        return registrationLevel;
    }

    public RegistrationLevelFilter registrationLevel() {
        if (registrationLevel == null) {
            registrationLevel = new RegistrationLevelFilter();
        }
        return registrationLevel;
    }

    public void setRegistrationLevel(RegistrationLevelFilter registrationLevel) {
        this.registrationLevel = registrationLevel;
    }

    public BooleanFilter getIsApproved() {
        return isApproved;
    }

    public BooleanFilter isApproved() {
        if (isApproved == null) {
            isApproved = new BooleanFilter();
        }
        return isApproved;
    }

    public void setIsApproved(BooleanFilter isApproved) {
        this.isApproved = isApproved;
    }

    public BooleanFilter getIsActivated() {
        return isActivated;
    }

    public BooleanFilter isActivated() {
        if (isActivated == null) {
            isActivated = new BooleanFilter();
        }
        return isActivated;
    }

    public void setIsActivated(BooleanFilter isActivated) {
        this.isActivated = isActivated;
    }

    public StringFilter getFreeField1() {
        return freeField1;
    }

    public StringFilter freeField1() {
        if (freeField1 == null) {
            freeField1 = new StringFilter();
        }
        return freeField1;
    }

    public void setFreeField1(StringFilter freeField1) {
        this.freeField1 = freeField1;
    }

    public StringFilter getFreeField2() {
        return freeField2;
    }

    public StringFilter freeField2() {
        if (freeField2 == null) {
            freeField2 = new StringFilter();
        }
        return freeField2;
    }

    public void setFreeField2(StringFilter freeField2) {
        this.freeField2 = freeField2;
    }

    public StringFilter getFreeField3() {
        return freeField3;
    }

    public StringFilter freeField3() {
        if (freeField3 == null) {
            freeField3 = new StringFilter();
        }
        return freeField3;
    }

    public void setFreeField3(StringFilter freeField3) {
        this.freeField3 = freeField3;
    }

    public StringFilter getFreeField4() {
        return freeField4;
    }

    public StringFilter freeField4() {
        if (freeField4 == null) {
            freeField4 = new StringFilter();
        }
        return freeField4;
    }

    public void setFreeField4(StringFilter freeField4) {
        this.freeField4 = freeField4;
    }

    public StringFilter getLastModified() {
        return lastModified;
    }

    public StringFilter lastModified() {
        if (lastModified == null) {
            lastModified = new StringFilter();
        }
        return lastModified;
    }

    public void setLastModified(StringFilter lastModified) {
        this.lastModified = lastModified;
    }

    public StringFilter getLastModifiedBy() {
        return lastModifiedBy;
    }

    public StringFilter lastModifiedBy() {
        if (lastModifiedBy == null) {
            lastModifiedBy = new StringFilter();
        }
        return lastModifiedBy;
    }

    public void setLastModifiedBy(StringFilter lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LongFilter getDistrictId() {
        return districtId;
    }

    public LongFilter districtId() {
        if (districtId == null) {
            districtId = new LongFilter();
        }
        return districtId;
    }

    public void setDistrictId(LongFilter districtId) {
        this.districtId = districtId;
    }

    public LongFilter getProductClientAgreementId() {
        return productClientAgreementId;
    }

    public LongFilter productClientAgreementId() {
        if (productClientAgreementId == null) {
            productClientAgreementId = new LongFilter();
        }
        return productClientAgreementId;
    }

    public void setProductClientAgreementId(LongFilter productClientAgreementId) {
        this.productClientAgreementId = productClientAgreementId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClientDetailsCriteria that = (ClientDetailsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(customerID, that.customerID) &&
            Objects.equals(clientName, that.clientName) &&
            Objects.equals(clientType, that.clientType) &&
            Objects.equals(mobileNo, that.mobileNo) &&
            Objects.equals(email, that.email) &&
            Objects.equals(pincode, that.pincode) &&
            Objects.equals(billingAddress, that.billingAddress) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(companyContactNo, that.companyContactNo) &&
            Objects.equals(website, that.website) &&
            Objects.equals(gstinNumber, that.gstinNumber) &&
            Objects.equals(companyPan, that.companyPan) &&
            Objects.equals(companyTan, that.companyTan) &&
            Objects.equals(description, that.description) &&
            Objects.equals(nameOfBeneficiary, that.nameOfBeneficiary) &&
            Objects.equals(accountNumber, that.accountNumber) &&
            Objects.equals(bankName, that.bankName) &&
            Objects.equals(accountType, that.accountType) &&
            Objects.equals(ifscCode, that.ifscCode) &&
            Objects.equals(registrationCategory, that.registrationCategory) &&
            Objects.equals(registrationLevel, that.registrationLevel) &&
            Objects.equals(isApproved, that.isApproved) &&
            Objects.equals(isActivated, that.isActivated) &&
            Objects.equals(freeField1, that.freeField1) &&
            Objects.equals(freeField2, that.freeField2) &&
            Objects.equals(freeField3, that.freeField3) &&
            Objects.equals(freeField4, that.freeField4) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(districtId, that.districtId) &&
            Objects.equals(productClientAgreementId, that.productClientAgreementId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            customerID,
            clientName,
            clientType,
            mobileNo,
            email,
            pincode,
            billingAddress,
            companyName,
            companyContactNo,
            website,
            gstinNumber,
            companyPan,
            companyTan,
            description,
            nameOfBeneficiary,
            accountNumber,
            bankName,
            accountType,
            ifscCode,
            registrationCategory,
            registrationLevel,
            isApproved,
            isActivated,
            freeField1,
            freeField2,
            freeField3,
            freeField4,
            lastModified,
            lastModifiedBy,
            districtId,
            productClientAgreementId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDetailsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (customerID != null ? "customerID=" + customerID + ", " : "") +
            (clientName != null ? "clientName=" + clientName + ", " : "") +
            (clientType != null ? "clientType=" + clientType + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (pincode != null ? "pincode=" + pincode + ", " : "") +
            (billingAddress != null ? "billingAddress=" + billingAddress + ", " : "") +
            (companyName != null ? "companyName=" + companyName + ", " : "") +
            (companyContactNo != null ? "companyContactNo=" + companyContactNo + ", " : "") +
            (website != null ? "website=" + website + ", " : "") +
            (gstinNumber != null ? "gstinNumber=" + gstinNumber + ", " : "") +
            (companyPan != null ? "companyPan=" + companyPan + ", " : "") +
            (companyTan != null ? "companyTan=" + companyTan + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (nameOfBeneficiary != null ? "nameOfBeneficiary=" + nameOfBeneficiary + ", " : "") +
            (accountNumber != null ? "accountNumber=" + accountNumber + ", " : "") +
            (bankName != null ? "bankName=" + bankName + ", " : "") +
            (accountType != null ? "accountType=" + accountType + ", " : "") +
            (ifscCode != null ? "ifscCode=" + ifscCode + ", " : "") +
            (registrationCategory != null ? "registrationCategory=" + registrationCategory + ", " : "") +
            (registrationLevel != null ? "registrationLevel=" + registrationLevel + ", " : "") +
            (isApproved != null ? "isApproved=" + isApproved + ", " : "") +
            (isActivated != null ? "isActivated=" + isActivated + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField3 != null ? "freeField3=" + freeField3 + ", " : "") +
            (freeField4 != null ? "freeField4=" + freeField4 + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (districtId != null ? "districtId=" + districtId + ", " : "") +
            (productClientAgreementId != null ? "productClientAgreementId=" + productClientAgreementId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
