package com.daicq.service.mapper;

import com.daicq.domain.*;
import com.daicq.service.dto.CTDHDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CTDH and its DTO CTDHDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CTDHMapper extends EntityMapper<CTDHDTO, CTDH> {


}
