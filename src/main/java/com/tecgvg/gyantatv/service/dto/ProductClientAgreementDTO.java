package com.tecgvg.gyantatv.service.dto;

import com.tecgvg.gyantatv.domain.enumeration.PenaltyTerms;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.tecgvg.gyantatv.domain.ProductClientAgreement} entity.
 */
public class ProductClientAgreementDTO implements Serializable {

    private Long id;

    private Long price;

    private Double creditPeriod;

    private Long penalty;

    private PenaltyTerms penaltyTerms;

    private String notes;

    private String image;

    private Long agreementPeriod;

    private String termAndConditions;

    private String freeField1;

    private String freeField2;

    private String freeField3;

    private String freeField4;

    private String lastModified;

    private String lastModifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Double getCreditPeriod() {
        return creditPeriod;
    }

    public void setCreditPeriod(Double creditPeriod) {
        this.creditPeriod = creditPeriod;
    }

    public Long getPenalty() {
        return penalty;
    }

    public void setPenalty(Long penalty) {
        this.penalty = penalty;
    }

    public PenaltyTerms getPenaltyTerms() {
        return penaltyTerms;
    }

    public void setPenaltyTerms(PenaltyTerms penaltyTerms) {
        this.penaltyTerms = penaltyTerms;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getAgreementPeriod() {
        return agreementPeriod;
    }

    public void setAgreementPeriod(Long agreementPeriod) {
        this.agreementPeriod = agreementPeriod;
    }

    public String getTermAndConditions() {
        return termAndConditions;
    }

    public void setTermAndConditions(String termAndConditions) {
        this.termAndConditions = termAndConditions;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductClientAgreementDTO)) {
            return false;
        }

        ProductClientAgreementDTO productClientAgreementDTO = (ProductClientAgreementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productClientAgreementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductClientAgreementDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", creditPeriod=" + getCreditPeriod() +
            ", penalty=" + getPenalty() +
            ", penaltyTerms='" + getPenaltyTerms() + "'" +
            ", notes='" + getNotes() + "'" +
            ", image='" + getImage() + "'" +
            ", agreementPeriod=" + getAgreementPeriod() +
            ", termAndConditions='" + getTermAndConditions() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
