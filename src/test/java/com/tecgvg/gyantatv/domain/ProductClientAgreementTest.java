package com.tecgvg.gyantatv.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.tecgvg.gyantatv.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductClientAgreementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductClientAgreement.class);
        ProductClientAgreement productClientAgreement1 = new ProductClientAgreement();
        productClientAgreement1.setId(1L);
        ProductClientAgreement productClientAgreement2 = new ProductClientAgreement();
        productClientAgreement2.setId(productClientAgreement1.getId());
        assertThat(productClientAgreement1).isEqualTo(productClientAgreement2);
        productClientAgreement2.setId(2L);
        assertThat(productClientAgreement1).isNotEqualTo(productClientAgreement2);
        productClientAgreement1.setId(null);
        assertThat(productClientAgreement1).isNotEqualTo(productClientAgreement2);
    }
}
