package com.tecgvg.gyantatv.service.criteria;

import com.tecgvg.gyantatv.domain.enumeration.PenaltyTerms;
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
 * Criteria class for the {@link com.tecgvg.gyantatv.domain.ProductClientAgreement} entity. This class is used
 * in {@link com.tecgvg.gyantatv.web.rest.ProductClientAgreementResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /product-client-agreements?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ProductClientAgreementCriteria implements Serializable, Criteria {

    /**
     * Class for filtering PenaltyTerms
     */
    public static class PenaltyTermsFilter extends Filter<PenaltyTerms> {

        public PenaltyTermsFilter() {}

        public PenaltyTermsFilter(PenaltyTermsFilter filter) {
            super(filter);
        }

        @Override
        public PenaltyTermsFilter copy() {
            return new PenaltyTermsFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter price;

    private DoubleFilter creditPeriod;

    private DoubleFilter penalty;

    private PenaltyTermsFilter penaltyTerms;

    private StringFilter notes;

    private StringFilter image;

    private DoubleFilter agreementPeriod;

    private StringFilter termAndConditions;

    private StringFilter description;

    private StringFilter freeField1;

    private StringFilter freeField2;

    private StringFilter freeField3;

    private StringFilter freeField4;

    private StringFilter lastModified;

    private StringFilter lastModifiedBy;

    private LongFilter productId;

    private LongFilter clientDetailsId;

    private Boolean distinct;

    public ProductClientAgreementCriteria() {}

    public ProductClientAgreementCriteria(ProductClientAgreementCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.creditPeriod = other.creditPeriod == null ? null : other.creditPeriod.copy();
        this.penalty = other.penalty == null ? null : other.penalty.copy();
        this.penaltyTerms = other.penaltyTerms == null ? null : other.penaltyTerms.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.image = other.image == null ? null : other.image.copy();
        this.agreementPeriod = other.agreementPeriod == null ? null : other.agreementPeriod.copy();
        this.termAndConditions = other.termAndConditions == null ? null : other.termAndConditions.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.freeField1 = other.freeField1 == null ? null : other.freeField1.copy();
        this.freeField2 = other.freeField2 == null ? null : other.freeField2.copy();
        this.freeField3 = other.freeField3 == null ? null : other.freeField3.copy();
        this.freeField4 = other.freeField4 == null ? null : other.freeField4.copy();
        this.lastModified = other.lastModified == null ? null : other.lastModified.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.productId = other.productId == null ? null : other.productId.copy();
        this.clientDetailsId = other.clientDetailsId == null ? null : other.clientDetailsId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProductClientAgreementCriteria copy() {
        return new ProductClientAgreementCriteria(this);
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

    public DoubleFilter getPrice() {
        return price;
    }

    public DoubleFilter price() {
        if (price == null) {
            price = new DoubleFilter();
        }
        return price;
    }

    public void setPrice(DoubleFilter price) {
        this.price = price;
    }

    public DoubleFilter getCreditPeriod() {
        return creditPeriod;
    }

    public DoubleFilter creditPeriod() {
        if (creditPeriod == null) {
            creditPeriod = new DoubleFilter();
        }
        return creditPeriod;
    }

    public void setCreditPeriod(DoubleFilter creditPeriod) {
        this.creditPeriod = creditPeriod;
    }

    public DoubleFilter getPenalty() {
        return penalty;
    }

    public DoubleFilter penalty() {
        if (penalty == null) {
            penalty = new DoubleFilter();
        }
        return penalty;
    }

    public void setPenalty(DoubleFilter penalty) {
        this.penalty = penalty;
    }

    public PenaltyTermsFilter getPenaltyTerms() {
        return penaltyTerms;
    }

    public PenaltyTermsFilter penaltyTerms() {
        if (penaltyTerms == null) {
            penaltyTerms = new PenaltyTermsFilter();
        }
        return penaltyTerms;
    }

    public void setPenaltyTerms(PenaltyTermsFilter penaltyTerms) {
        this.penaltyTerms = penaltyTerms;
    }

    public StringFilter getNotes() {
        return notes;
    }

    public StringFilter notes() {
        if (notes == null) {
            notes = new StringFilter();
        }
        return notes;
    }

    public void setNotes(StringFilter notes) {
        this.notes = notes;
    }

    public StringFilter getImage() {
        return image;
    }

    public StringFilter image() {
        if (image == null) {
            image = new StringFilter();
        }
        return image;
    }

    public void setImage(StringFilter image) {
        this.image = image;
    }

    public DoubleFilter getAgreementPeriod() {
        return agreementPeriod;
    }

    public DoubleFilter agreementPeriod() {
        if (agreementPeriod == null) {
            agreementPeriod = new DoubleFilter();
        }
        return agreementPeriod;
    }

    public void setAgreementPeriod(DoubleFilter agreementPeriod) {
        this.agreementPeriod = agreementPeriod;
    }

    public StringFilter getTermAndConditions() {
        return termAndConditions;
    }

    public StringFilter termAndConditions() {
        if (termAndConditions == null) {
            termAndConditions = new StringFilter();
        }
        return termAndConditions;
    }

    public void setTermAndConditions(StringFilter termAndConditions) {
        this.termAndConditions = termAndConditions;
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

    public LongFilter getProductId() {
        return productId;
    }

    public LongFilter productId() {
        if (productId == null) {
            productId = new LongFilter();
        }
        return productId;
    }

    public void setProductId(LongFilter productId) {
        this.productId = productId;
    }

    public LongFilter getClientDetailsId() {
        return clientDetailsId;
    }

    public LongFilter clientDetailsId() {
        if (clientDetailsId == null) {
            clientDetailsId = new LongFilter();
        }
        return clientDetailsId;
    }

    public void setClientDetailsId(LongFilter clientDetailsId) {
        this.clientDetailsId = clientDetailsId;
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
        final ProductClientAgreementCriteria that = (ProductClientAgreementCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(price, that.price) &&
            Objects.equals(creditPeriod, that.creditPeriod) &&
            Objects.equals(penalty, that.penalty) &&
            Objects.equals(penaltyTerms, that.penaltyTerms) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(image, that.image) &&
            Objects.equals(agreementPeriod, that.agreementPeriod) &&
            Objects.equals(termAndConditions, that.termAndConditions) &&
            Objects.equals(description, that.description) &&
            Objects.equals(freeField1, that.freeField1) &&
            Objects.equals(freeField2, that.freeField2) &&
            Objects.equals(freeField3, that.freeField3) &&
            Objects.equals(freeField4, that.freeField4) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(productId, that.productId) &&
            Objects.equals(clientDetailsId, that.clientDetailsId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            price,
            creditPeriod,
            penalty,
            penaltyTerms,
            notes,
            image,
            agreementPeriod,
            termAndConditions,
            description,
            freeField1,
            freeField2,
            freeField3,
            freeField4,
            lastModified,
            lastModifiedBy,
            productId,
            clientDetailsId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductClientAgreementCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            (creditPeriod != null ? "creditPeriod=" + creditPeriod + ", " : "") +
            (penalty != null ? "penalty=" + penalty + ", " : "") +
            (penaltyTerms != null ? "penaltyTerms=" + penaltyTerms + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (image != null ? "image=" + image + ", " : "") +
            (agreementPeriod != null ? "agreementPeriod=" + agreementPeriod + ", " : "") +
            (termAndConditions != null ? "termAndConditions=" + termAndConditions + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField3 != null ? "freeField3=" + freeField3 + ", " : "") +
            (freeField4 != null ? "freeField4=" + freeField4 + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (productId != null ? "productId=" + productId + ", " : "") +
            (clientDetailsId != null ? "clientDetailsId=" + clientDetailsId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
