package com.tecgvg.gyantatv.domain.enumeration;

/**
 * The PenaltyTerms enumeration.
 */
public enum PenaltyTerms {
    DAILY("Daily"),
    MONTHLY("Monthly"),
    YEARLY("Yearly");

    private final String value;

    PenaltyTerms(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
