package com.tecgvg.gyantatv.service.mapper;

import com.tecgvg.gyantatv.domain.Project;
import com.tecgvg.gyantatv.service.dto.ProjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {}
