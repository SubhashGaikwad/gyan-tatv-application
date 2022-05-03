package com.tecgvg.gyantatv.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tecgvg.gyantatv.domain.enumeration.PenaltyTerms;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProductClientAgreement.
 */
@Entity
@Table(name = "product_client_agreement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductClientAgreement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "credit_period")
    private Double creditPeriod;

    @Column(name = "penalty")
    private Double penalty;

    @Enumerated(EnumType.STRING)
    @Column(name = "penalty_terms")
    private PenaltyTerms penaltyTerms;

    @Column(name = "notes")
    private String notes;

    @Column(name = "image")
    private String image;

    @Column(name = "agreement_period")
    private Double agreementPeriod;

    @Column(name = "term_and_conditions")
    private String termAndConditions;

    @Column(name = "description")
    private String description;

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

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "transferDetails", "productClientAgreements", "categories", "unit", "securityUser" },
        allowSetters = true
    )
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties(value = { "district", "productClientAgreements" }, allowSetters = true)
    private ClientDetails clientDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProductClientAgreement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return this.price;
    }

    public ProductClientAgreement price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCreditPeriod() {
        return this.creditPeriod;
    }

    public ProductClientAgreement creditPeriod(Double creditPeriod) {
        this.setCreditPeriod(creditPeriod);
        return this;
    }

    public void setCreditPeriod(Double creditPeriod) {
        this.creditPeriod = creditPeriod;
    }

    public Double getPenalty() {
        return this.penalty;
    }

    public ProductClientAgreement penalty(Double penalty) {
        this.setPenalty(penalty);
        return this;
    }

    public void setPenalty(Double penalty) {
        this.penalty = penalty;
    }

    public PenaltyTerms getPenaltyTerms() {
        return this.penaltyTerms;
    }

    public ProductClientAgreement penaltyTerms(PenaltyTerms penaltyTerms) {
        this.setPenaltyTerms(penaltyTerms);
        return this;
    }

    public void setPenaltyTerms(PenaltyTerms penaltyTerms) {
        this.penaltyTerms = penaltyTerms;
    }

    public String getNotes() {
        return this.notes;
    }

    public ProductClientAgreement notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImage() {
        return this.image;
    }

    public ProductClientAgreement image(String image) {
        this.setImage(image);
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getAgreementPeriod() {
        return this.agreementPeriod;
    }

    public ProductClientAgreement agreementPeriod(Double agreementPeriod) {
        this.setAgreementPeriod(agreementPeriod);
        return this;
    }

    public void setAgreementPeriod(Double agreementPeriod) {
        this.agreementPeriod = agreementPeriod;
    }

    public String getTermAndConditions() {
        return this.termAndConditions;
    }

    public ProductClientAgreement termAndConditions(String termAndConditions) {
        this.setTermAndConditions(termAndConditions);
        return this;
    }

    public void setTermAndConditions(String termAndConditions) {
        this.termAndConditions = termAndConditions;
    }

    public String getDescription() {
        return this.description;
    }

    public ProductClientAgreement description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public ProductClientAgreement freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public ProductClientAgreement freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public ProductClientAgreement freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public ProductClientAgreement freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getLastModified() {
        return this.lastModified;
    }

    public ProductClientAgreement lastModified(String lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public ProductClientAgreement lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductClientAgreement product(Product product) {
        this.setProduct(product);
        return this;
    }

    public ClientDetails getClientDetails() {
        return this.clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public ProductClientAgreement clientDetails(ClientDetails clientDetails) {
        this.setClientDetails(clientDetails);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductClientAgreement)) {
            return false;
        }
        return id != null && id.equals(((ProductClientAgreement) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductClientAgreement{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", creditPeriod=" + getCreditPeriod() +
            ", penalty=" + getPenalty() +
            ", penaltyTerms='" + getPenaltyTerms() + "'" +
            ", notes='" + getNotes() + "'" +
            ", image='" + getImage() + "'" +
            ", agreementPeriod=" + getAgreementPeriod() +
            ", termAndConditions='" + getTermAndConditions() + "'" +
            ", description='" + getDescription() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
