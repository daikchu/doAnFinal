package com.daicq.service.mapper;

import com.daicq.domain.*;
import com.daicq.service.dto.HoaDonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity HoaDon and its DTO HoaDonDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HoaDonMapper extends EntityMapper<HoaDonDTO, HoaDon> {


}
