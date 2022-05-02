package com.tecgvg.gyantatv.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductClientAgreementMapperTest {

    private ProductClientAgreementMapper productClientAgreementMapper;

    @BeforeEach
    public void setUp() {
        productClientAgreementMapper = new ProductClientAgreementMapperImpl();
    }
}
