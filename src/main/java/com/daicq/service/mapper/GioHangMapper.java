package com.daicq.service.mapper;

import com.daicq.domain.*;
import com.daicq.service.dto.GioHangDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GioHang and its DTO GioHangDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GioHangMapper extends EntityMapper<GioHangDTO, GioHang> {


}
