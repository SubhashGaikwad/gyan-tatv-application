package com.tecgvg.gyantatv.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.tecgvg.gyantatv.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductClientAgreementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductClientAgreementDTO.class);
        ProductClientAgreementDTO productClientAgreementDTO1 = new ProductClientAgreementDTO();
        productClientAgreementDTO1.setId(1L);
        ProductClientAgreementDTO productClientAgreementDTO2 = new ProductClientAgreementDTO();
        assertThat(productClientAgreementDTO1).isNotEqualTo(productClientAgreementDTO2);
        productClientAgreementDTO2.setId(productClientAgreementDTO1.getId());
        assertThat(productClientAgreementDTO1).isEqualTo(productClientAgreementDTO2);
        productClientAgreementDTO2.setId(2L);
        assertThat(productClientAgreementDTO1).isNotEqualTo(productClientAgreementDTO2);
        productClientAgreementDTO1.setId(null);
        assertThat(productClientAgreementDTO1).isNotEqualTo(productClientAgreementDTO2);
    }
}
