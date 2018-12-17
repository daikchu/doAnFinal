package com.daicq.service.mapper;

import com.daicq.domain.*;
import com.daicq.service.dto.DanhMucDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DanhMuc and its DTO DanhMucDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DanhMucMapper extends EntityMapper<DanhMucDTO, DanhMuc> {


}
