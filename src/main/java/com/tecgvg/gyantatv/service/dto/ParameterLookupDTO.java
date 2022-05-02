package com.tecgvg.gyantatv.service.dto;

import com.tecgvg.gyantatv.domain.enumeration.ParameterType;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.tecgvg.gyantatv.domain.ParameterLookup} entity.
 */
public class ParameterLookupDTO implements Serializable {

    private Long id;

    private String name;

    private ParameterType type;

    private String value;

    private String descriptions;

    private Boolean isActive;

    private String lastModified;

    private String lastModifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParameterType getType() {
        return type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
        if (!(o instanceof ParameterLookupDTO)) {
            return false;
        }

        ParameterLookupDTO parameterLookupDTO = (ParameterLookupDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, parameterLookupDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParameterLookupDTO{" +
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
