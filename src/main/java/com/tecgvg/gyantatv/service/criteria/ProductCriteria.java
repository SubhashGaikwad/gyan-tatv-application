package com.tecgvg.gyantatv.service.criteria;

import com.tecgvg.gyantatv.domain.enumeration.ProductType;
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
 * Criteria class for the {@link com.tecgvg.gyantatv.domain.Product} entity. This class is used
 * in {@link com.tecgvg.gyantatv.web.rest.ProductResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /products?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ProductCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ProductType
     */
    public static class ProductTypeFilter extends Filter<ProductType> {

        public ProductTypeFilter() {}

        public ProductTypeFilter(ProductTypeFilter filter) {
            super(filter);
        }

        @Override
        public ProductTypeFilter copy() {
            return new ProductTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter shortName;

    private StringFilter hsnNo;

    private BooleanFilter isDeleted;

    private BooleanFilter isActive;

    private StringFilter productName;

    private StringFilter alertUnits;

    private StringFilter description;

    private DoubleFilter basePrice;

    private StringFilter quality;

    private StringFilter qrCode;

    private StringFilter barCode;

    private DoubleFilter gstPercentage;

    private ProductTypeFilter productType;

    private StringFilter lastModified;

    private StringFilter lastModifiedBy;

    private StringFilter freeField1;

    private StringFilter freeField2;

    private StringFilter freeField3;

    private StringFilter freeField4;

    private LongFilter transferDetailsId;

    private LongFilter productClientAgreementId;

    private LongFilter categoriesId;

    private LongFilter unitId;

    private LongFilter securityUserId;

    private Boolean distinct;

    public ProductCriteria() {}

    public ProductCriteria(ProductCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.shortName = other.shortName == null ? null : other.shortName.copy();
        this.hsnNo = other.hsnNo == null ? null : other.hsnNo.copy();
        this.isDeleted = other.isDeleted == null ? null : other.isDeleted.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.productName = other.productName == null ? null : other.productName.copy();
        this.alertUnits = other.alertUnits == null ? null : other.alertUnits.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.basePrice = other.basePrice == null ? null : other.basePrice.copy();
        this.quality = other.quality == null ? null : other.quality.copy();
        this.qrCode = other.qrCode == null ? null : other.qrCode.copy();
        this.barCode = other.barCode == null ? null : other.barCode.copy();
        this.gstPercentage = other.gstPercentage == null ? null : other.gstPercentage.copy();
        this.productType = other.productType == null ? null : other.productType.copy();
        this.lastModified = other.lastModified == null ? null : other.lastModified.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.freeField1 = other.freeField1 == null ? null : other.freeField1.copy();
        this.freeField2 = other.freeField2 == null ? null : other.freeField2.copy();
        this.freeField3 = other.freeField3 == null ? null : other.freeField3.copy();
        this.freeField4 = other.freeField4 == null ? null : other.freeField4.copy();
        this.transferDetailsId = other.transferDetailsId == null ? null : other.transferDetailsId.copy();
        this.productClientAgreementId = other.productClientAgreementId == null ? null : other.productClientAgreementId.copy();
        this.categoriesId = other.categoriesId == null ? null : other.categoriesId.copy();
        this.unitId = other.unitId == null ? null : other.unitId.copy();
        this.securityUserId = other.securityUserId == null ? null : other.securityUserId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProductCriteria copy() {
        return new ProductCriteria(this);
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

    public StringFilter getShortName() {
        return shortName;
    }

    public StringFilter shortName() {
        if (shortName == null) {
            shortName = new StringFilter();
        }
        return shortName;
    }

    public void setShortName(StringFilter shortName) {
        this.shortName = shortName;
    }

    public StringFilter getHsnNo() {
        return hsnNo;
    }

    public StringFilter hsnNo() {
        if (hsnNo == null) {
            hsnNo = new StringFilter();
        }
        return hsnNo;
    }

    public void setHsnNo(StringFilter hsnNo) {
        this.hsnNo = hsnNo;
    }

    public BooleanFilter getIsDeleted() {
        return isDeleted;
    }

    public BooleanFilter isDeleted() {
        if (isDeleted == null) {
            isDeleted = new BooleanFilter();
        }
        return isDeleted;
    }

    public void setIsDeleted(BooleanFilter isDeleted) {
        this.isDeleted = isDeleted;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public BooleanFilter isActive() {
        if (isActive == null) {
            isActive = new BooleanFilter();
        }
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public StringFilter getProductName() {
        return productName;
    }

    public StringFilter productName() {
        if (productName == null) {
            productName = new StringFilter();
        }
        return productName;
    }

    public void setProductName(StringFilter productName) {
        this.productName = productName;
    }

    public StringFilter getAlertUnits() {
        return alertUnits;
    }

    public StringFilter alertUnits() {
        if (alertUnits == null) {
            alertUnits = new StringFilter();
        }
        return alertUnits;
    }

    public void setAlertUnits(StringFilter alertUnits) {
        this.alertUnits = alertUnits;
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

    public DoubleFilter getBasePrice() {
        return basePrice;
    }

    public DoubleFilter basePrice() {
        if (basePrice == null) {
            basePrice = new DoubleFilter();
        }
        return basePrice;
    }

    public void setBasePrice(DoubleFilter basePrice) {
        this.basePrice = basePrice;
    }

    public StringFilter getQuality() {
        return quality;
    }

    public StringFilter quality() {
        if (quality == null) {
            quality = new StringFilter();
        }
        return quality;
    }

    public void setQuality(StringFilter quality) {
        this.quality = quality;
    }

    public StringFilter getQrCode() {
        return qrCode;
    }

    public StringFilter qrCode() {
        if (qrCode == null) {
            qrCode = new StringFilter();
        }
        return qrCode;
    }

    public void setQrCode(StringFilter qrCode) {
        this.qrCode = qrCode;
    }

    public StringFilter getBarCode() {
        return barCode;
    }

    public StringFilter barCode() {
        if (barCode == null) {
            barCode = new StringFilter();
        }
        return barCode;
    }

    public void setBarCode(StringFilter barCode) {
        this.barCode = barCode;
    }

    public DoubleFilter getGstPercentage() {
        return gstPercentage;
    }

    public DoubleFilter gstPercentage() {
        if (gstPercentage == null) {
            gstPercentage = new DoubleFilter();
        }
        return gstPercentage;
    }

    public void setGstPercentage(DoubleFilter gstPercentage) {
        this.gstPercentage = gstPercentage;
    }

    public ProductTypeFilter getProductType() {
        return productType;
    }

    public ProductTypeFilter productType() {
        if (productType == null) {
            productType = new ProductTypeFilter();
        }
        return productType;
    }

    public void setProductType(ProductTypeFilter productType) {
        this.productType = productType;
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

    public LongFilter getTransferDetailsId() {
        return transferDetailsId;
    }

    public LongFilter transferDetailsId() {
        if (transferDetailsId == null) {
            transferDetailsId = new LongFilter();
        }
        return transferDetailsId;
    }

    public void setTransferDetailsId(LongFilter transferDetailsId) {
        this.transferDetailsId = transferDetailsId;
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

    public LongFilter getCategoriesId() {
        return categoriesId;
    }

    public LongFilter categoriesId() {
        if (categoriesId == null) {
            categoriesId = new LongFilter();
        }
        return categoriesId;
    }

    public void setCategoriesId(LongFilter categoriesId) {
        this.categoriesId = categoriesId;
    }

    public LongFilter getUnitId() {
        return unitId;
    }

    public LongFilter unitId() {
        if (unitId == null) {
            unitId = new LongFilter();
        }
        return unitId;
    }

    public void setUnitId(LongFilter unitId) {
        this.unitId = unitId;
    }

    public LongFilter getSecurityUserId() {
        return securityUserId;
    }

    public LongFilter securityUserId() {
        if (securityUserId == null) {
            securityUserId = new LongFilter();
        }
        return securityUserId;
    }

    public void setSecurityUserId(LongFilter securityUserId) {
        this.securityUserId = securityUserId;
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
        final ProductCriteria that = (ProductCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(shortName, that.shortName) &&
            Objects.equals(hsnNo, that.hsnNo) &&
            Objects.equals(isDeleted, that.isDeleted) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(productName, that.productName) &&
            Objects.equals(alertUnits, that.alertUnits) &&
            Objects.equals(description, that.description) &&
            Objects.equals(basePrice, that.basePrice) &&
            Objects.equals(quality, that.quality) &&
            Objects.equals(qrCode, that.qrCode) &&
            Objects.equals(barCode, that.barCode) &&
            Objects.equals(gstPercentage, that.gstPercentage) &&
            Objects.equals(productType, that.productType) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(freeField1, that.freeField1) &&
            Objects.equals(freeField2, that.freeField2) &&
            Objects.equals(freeField3, that.freeField3) &&
            Objects.equals(freeField4, that.freeField4) &&
            Objects.equals(transferDetailsId, that.transferDetailsId) &&
            Objects.equals(productClientAgreementId, that.productClientAgreementId) &&
            Objects.equals(categoriesId, that.categoriesId) &&
            Objects.equals(unitId, that.unitId) &&
            Objects.equals(securityUserId, that.securityUserId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            shortName,
            hsnNo,
            isDeleted,
            isActive,
            productName,
            alertUnits,
            description,
            basePrice,
            quality,
            qrCode,
            barCode,
            gstPercentage,
            productType,
            lastModified,
            lastModifiedBy,
            freeField1,
            freeField2,
            freeField3,
            freeField4,
            transferDetailsId,
            productClientAgreementId,
            categoriesId,
            unitId,
            securityUserId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (shortName != null ? "shortName=" + shortName + ", " : "") +
            (hsnNo != null ? "hsnNo=" + hsnNo + ", " : "") +
            (isDeleted != null ? "isDeleted=" + isDeleted + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (productName != null ? "productName=" + productName + ", " : "") +
            (alertUnits != null ? "alertUnits=" + alertUnits + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (basePrice != null ? "basePrice=" + basePrice + ", " : "") +
            (quality != null ? "quality=" + quality + ", " : "") +
            (qrCode != null ? "qrCode=" + qrCode + ", " : "") +
            (barCode != null ? "barCode=" + barCode + ", " : "") +
            (gstPercentage != null ? "gstPercentage=" + gstPercentage + ", " : "") +
            (productType != null ? "productType=" + productType + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField3 != null ? "freeField3=" + freeField3 + ", " : "") +
            (freeField4 != null ? "freeField4=" + freeField4 + ", " : "") +
            (transferDetailsId != null ? "transferDetailsId=" + transferDetailsId + ", " : "") +
            (productClientAgreementId != null ? "productClientAgreementId=" + productClientAgreementId + ", " : "") +
            (categoriesId != null ? "categoriesId=" + categoriesId + ", " : "") +
            (unitId != null ? "unitId=" + unitId + ", " : "") +
            (securityUserId != null ? "securityUserId=" + securityUserId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
