package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.Categories;
import com.tecgvg.gyantatv.service.dto.CategoriesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Categories} and its DTO {@link CategoriesDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoriesMapper extends EntityMapper<CategoriesDTO, Categories> {}
