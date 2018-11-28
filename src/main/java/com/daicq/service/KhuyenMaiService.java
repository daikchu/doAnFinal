package com.daicq.service;

import com.daicq.service.dto.KhuyenMaiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing KhuyenMai.
 */
public interface KhuyenMaiService {

    /**
     * Save a khuyenMai.
     *
     * @param khuyenMaiDTO the entity to save
     * @return the persisted entity
     */
    KhuyenMaiDTO save(KhuyenMaiDTO khuyenMaiDTO);

    /**
     * Get all the khuyenMais.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<KhuyenMaiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" khuyenMai.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<KhuyenMaiDTO> findOne(String id);

    /**
     * Delete the "id" khuyenMai.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
