package com.tecgvg.gyantatv.domain.enumeration;

/**
 * The ParameterType enumeration.
 */
public enum ParameterType {
    REGISTRATION_CATEGORY("Registration Category"),
    OTHER("Other");

    private final String value;

    ParameterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
