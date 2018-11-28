package com.daicq.service.mapper;

import com.daicq.domain.*;
import com.daicq.service.dto.NhaXuatBanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NhaXuatBan and its DTO NhaXuatBanDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NhaXuatBanMapper extends EntityMapper<NhaXuatBanDTO, NhaXuatBan> {


}
