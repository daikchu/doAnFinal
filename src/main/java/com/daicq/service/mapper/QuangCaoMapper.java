package com.daicq.service.mapper;

import com.daicq.domain.*;
import com.daicq.service.dto.QuangCaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuangCao and its DTO QuangCaoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuangCaoMapper extends EntityMapper<QuangCaoDTO, QuangCao> {


}
