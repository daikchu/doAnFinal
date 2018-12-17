package com.daicq.service;

import com.daicq.service.dto.UsersDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Users.
 */
public interface UsersService {

    /**
     * Save a users.
     *
     * @param usersDTO the entity to save
     * @return the persisted entity
     */
    UsersDTO save(UsersDTO usersDTO);

    /**
     * Get all the users.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UsersDTO> findAll(Pageable pageable);


    /**
     * Get the "id" users.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UsersDTO> findOne(String id);

    /**
     * Delete the "id" users.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    UsersDTO findByUserName(String userName);
}
