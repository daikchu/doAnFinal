package com.daicq.service.impl;

import com.daicq.service.UsersService;
import com.daicq.domain.Users;
import com.daicq.repository.UsersRepository;
import com.daicq.service.dto.UsersDTO;
import com.daicq.service.mapper.UsersMapper;
import com.daicq.service.util.EncrytedPasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Users.
 */
@Service
public class UsersServiceImpl implements UsersService {

    private final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

    private final UsersRepository usersRepository;

    private final UsersMapper usersMapper;



    public UsersServiceImpl(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    /**
     * Save a users.
     *
     * @param usersDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UsersDTO save(UsersDTO usersDTO) {
        log.debug("Request to save Users : {}", usersDTO);
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        usersDTO.setRoles(roles);
        usersDTO.setPassword(EncrytedPasswordUtils.encrytePassword(usersDTO.getPassword()));
        log.debug("password after encode: {}", usersDTO.getPassword());
        Users users = usersMapper.toEntity(usersDTO);
        users = usersRepository.save(users);
        return usersMapper.toDto(users);
    }

    /**
     * Get all the users.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<UsersDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Users");
        return usersRepository.findAll(pageable)
            .map(usersMapper::toDto);
    }


    /**
     * Get one users by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<UsersDTO> findOne(String id) {
        log.debug("Request to get Users : {}", id);
        return usersRepository.findById(id)
            .map(usersMapper::toDto);
    }

    /**
     * Delete the users by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Users : {}", id);
        usersRepository.deleteById(id);
    }

    @Override
    public UsersDTO findByUserName(String userName) {
        return usersMapper.toDto(usersRepository.findByUserName(userName));
    }
}
