package com.tecgvg.gyantatv.domain.enumeration;

/**
 * The RegistrationLevel enumeration.
 */
public enum RegistrationLevel {
    STATE("State"),
    APMC;

    private final String value;

    RegistrationLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
