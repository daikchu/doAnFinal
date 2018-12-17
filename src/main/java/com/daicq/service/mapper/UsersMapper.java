package com.daicq.service.mapper;

import com.daicq.domain.*;
import com.daicq.service.dto.UsersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Users and its DTO UsersDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsersMapper extends EntityMapper<UsersDTO, Users> {


}
