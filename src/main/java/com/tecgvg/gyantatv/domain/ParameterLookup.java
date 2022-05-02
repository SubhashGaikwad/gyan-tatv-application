package com.tecgvg.gyantatv.domain;

import com.tecgvg.gyantatv.domain.enumeration.ParameterType;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ParameterLookup.
 */
@Entity
@Table(name = "parameter_lookup")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParameterLookup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ParameterType type;

    @Column(name = "value")
    private String value;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "last_modified")
    private String lastModified;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ParameterLookup id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public ParameterLookup name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParameterType getType() {
        return this.type;
    }

    public ParameterLookup type(ParameterType type) {
        this.setType(type);
        return this;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public String getValue() {
        return this.value;
    }

    public ParameterLookup value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescriptions() {
        return this.descriptions;
    }

    public ParameterLookup descriptions(String descriptions) {
        this.setDescriptions(descriptions);
        return this;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public ParameterLookup isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getLastModified() {
        return this.lastModified;
    }

    public ParameterLookup lastModified(String lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public ParameterLookup lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParameterLookup)) {
            return false;
        }
        return id != null && id.equals(((ParameterLookup) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParameterLookup{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", value='" + getValue() + "'" +
            ", descriptions='" + getDescriptions() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
