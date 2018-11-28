package com.daicq.service.mapper;

import com.daicq.domain.*;
import com.daicq.service.dto.KhuyenMaiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity KhuyenMai and its DTO KhuyenMaiDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KhuyenMaiMapper extends EntityMapper<KhuyenMaiDTO, KhuyenMai> {


}
