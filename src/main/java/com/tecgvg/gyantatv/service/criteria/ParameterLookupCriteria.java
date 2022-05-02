package com.tecgvg.gyantatv.service.criteria;

import com.tecgvg.gyantatv.domain.enumeration.ParameterType;
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
 * Criteria class for the {@link com.tecgvg.gyantatv.domain.ParameterLookup} entity. This class is used
 * in {@link com.tecgvg.gyantatv.web.rest.ParameterLookupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /parameter-lookups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ParameterLookupCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ParameterType
     */
    public static class ParameterTypeFilter extends Filter<ParameterType> {

        public ParameterTypeFilter() {}

        public ParameterTypeFilter(ParameterTypeFilter filter) {
            super(filter);
        }

        @Override
        public ParameterTypeFilter copy() {
            return new ParameterTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private ParameterTypeFilter type;

    private StringFilter value;

    private StringFilter descriptions;

    private BooleanFilter isActive;

    private StringFilter lastModified;

    private StringFilter lastModifiedBy;

    private Boolean distinct;

    public ParameterLookupCriteria() {}

    public ParameterLookupCriteria(ParameterLookupCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.value = other.value == null ? null : other.value.copy();
        this.descriptions = other.descriptions == null ? null : other.descriptions.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.lastModified = other.lastModified == null ? null : other.lastModified.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ParameterLookupCriteria copy() {
        return new ParameterLookupCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public ParameterTypeFilter getType() {
        return type;
    }

    public ParameterTypeFilter type() {
        if (type == null) {
            type = new ParameterTypeFilter();
        }
        return type;
    }

    public void setType(ParameterTypeFilter type) {
        this.type = type;
    }

    public StringFilter getValue() {
        return value;
    }

    public StringFilter value() {
        if (value == null) {
            value = new StringFilter();
        }
        return value;
    }

    public void setValue(StringFilter value) {
        this.value = value;
    }

    public StringFilter getDescriptions() {
        return descriptions;
    }

    public StringFilter descriptions() {
        if (descriptions == null) {
            descriptions = new StringFilter();
        }
        return descriptions;
    }

    public void setDescriptions(StringFilter descriptions) {
        this.descriptions = descriptions;
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
        final ParameterLookupCriteria that = (ParameterLookupCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(type, that.type) &&
            Objects.equals(value, that.value) &&
            Objects.equals(descriptions, that.descriptions) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(lastModified, that.lastModified) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, value, descriptions, isActive, lastModified, lastModifiedBy, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParameterLookupCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (value != null ? "value=" + value + ", " : "") +
            (descriptions != null ? "descriptions=" + descriptions + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
