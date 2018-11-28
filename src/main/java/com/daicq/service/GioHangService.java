package com.daicq.service;

import com.daicq.service.dto.GioHangDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GioHang.
 */
public interface GioHangService {

    /**
     * Save a gioHang.
     *
     * @param gioHangDTO the entity to save
     * @return the persisted entity
     */
    GioHangDTO save(GioHangDTO gioHangDTO);

    /**
     * Get all the gioHangs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GioHangDTO> findAll(Pageable pageable);


    /**
     * Get the "id" gioHang.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GioHangDTO> findOne(String id);

    /**
     * Delete the "id" gioHang.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
