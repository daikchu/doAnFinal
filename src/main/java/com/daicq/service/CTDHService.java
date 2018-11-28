package com.daicq.service;

import com.daicq.service.dto.CTDHDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CTDH.
 */
public interface CTDHService {

    /**
     * Save a cTDH.
     *
     * @param cTDHDTO the entity to save
     * @return the persisted entity
     */
    CTDHDTO save(CTDHDTO cTDHDTO);

    /**
     * Get all the cTDHS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CTDHDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cTDH.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CTDHDTO> findOne(String id);

    /**
     * Delete the "id" cTDH.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
