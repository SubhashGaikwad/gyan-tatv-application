package com.tecgvg.gyantatv.service;

import com.tecgvg.gyantatv.domain.ProductClientAgreement;
import com.tecgvg.gyantatv.repository.ProductClientAgreementRepository;
import com.tecgvg.gyantatv.service.dto.ProductClientAgreementDTO;
import com.tecgvg.gyantatv.service.mapper.ProductClientAgreementMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProductClientAgreement}.
 */
@Service
@Transactional
public class ProductClientAgreementService {

    private final Logger log = LoggerFactory.getLogger(ProductClientAgreementService.class);

    private final ProductClientAgreementRepository productClientAgreementRepository;

    private final ProductClientAgreementMapper productClientAgreementMapper;

    public ProductClientAgreementService(
        ProductClientAgreementRepository productClientAgreementRepository,
        ProductClientAgreementMapper productClientAgreementMapper
    ) {
        this.productClientAgreementRepository = productClientAgreementRepository;
        this.productClientAgreementMapper = productClientAgreementMapper;
    }

    /**
     * Save a productClientAgreement.
     *
     * @param productClientAgreementDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductClientAgreementDTO save(ProductClientAgreementDTO productClientAgreementDTO) {
        log.debug("Request to save ProductClientAgreement : {}", productClientAgreementDTO);
        ProductClientAgreement productClientAgreement = productClientAgreementMapper.toEntity(productClientAgreementDTO);
        productClientAgreement = productClientAgreementRepository.save(productClientAgreement);
        return productClientAgreementMapper.toDto(productClientAgreement);
    }

    /**
     * Update a productClientAgreement.
     *
     * @param productClientAgreementDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductClientAgreementDTO update(ProductClientAgreementDTO productClientAgreementDTO) {
        log.debug("Request to save ProductClientAgreement : {}", productClientAgreementDTO);
        ProductClientAgreement productClientAgreement = productClientAgreementMapper.toEntity(productClientAgreementDTO);
        productClientAgreement = productClientAgreementRepository.save(productClientAgreement);
        return productClientAgreementMapper.toDto(productClientAgreement);
    }

    /**
     * Partially update a productClientAgreement.
     *
     * @param productClientAgreementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProductClientAgreementDTO> partialUpdate(ProductClientAgreementDTO productClientAgreementDTO) {
        log.debug("Request to partially update ProductClientAgreement : {}", productClientAgreementDTO);

        return productClientAgreementRepository
            .findById(productClientAgreementDTO.getId())
            .map(existingProductClientAgreement -> {
                productClientAgreementMapper.partialUpdate(existingProductClientAgreement, productClientAgreementDTO);

                return existingProductClientAgreement;
            })
            .map(productClientAgreementRepository::save)
            .map(productClientAgreementMapper::toDto);
    }

    /**
     * Get all the productClientAgreements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductClientAgreementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductClientAgreements");
        return productClientAgreementRepository.findAll(pageable).map(productClientAgreementMapper::toDto);
    }

    /**
     * Get one productClientAgreement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductClientAgreementDTO> findOne(Long id) {
        log.debug("Request to get ProductClientAgreement : {}", id);
        return productClientAgreementRepository.findById(id).map(productClientAgreementMapper::toDto);
    }

    /**
     * Delete the productClientAgreement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProductClientAgreement : {}", id);
        productClientAgreementRepository.deleteById(id);
    }
}
