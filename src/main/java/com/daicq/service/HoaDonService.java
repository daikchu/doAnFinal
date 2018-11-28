package com.daicq.service;

import com.daicq.service.dto.HoaDonDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing HoaDon.
 */
public interface HoaDonService {

    /**
     * Save a hoaDon.
     *
     * @param hoaDonDTO the entity to save
     * @return the persisted entity
     */
    HoaDonDTO save(HoaDonDTO hoaDonDTO);

    /**
     * Get all the hoaDons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HoaDonDTO> findAll(Pageable pageable);


    /**
     * Get the "id" hoaDon.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HoaDonDTO> findOne(String id);

    /**
     * Delete the "id" hoaDon.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
