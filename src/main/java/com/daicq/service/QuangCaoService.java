package com.daicq.service;

import com.daicq.service.dto.QuangCaoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing QuangCao.
 */
public interface QuangCaoService {

    /**
     * Save a quangCao.
     *
     * @param quangCaoDTO the entity to save
     * @return the persisted entity
     */
    QuangCaoDTO save(QuangCaoDTO quangCaoDTO);

    /**
     * Get all the quangCaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuangCaoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" quangCao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<QuangCaoDTO> findOne(String id);

    /**
     * Delete the "id" quangCao.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
