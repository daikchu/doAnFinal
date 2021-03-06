package com.daicq.service;

import com.daicq.service.dto.DanhMucDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DanhMuc.
 */
public interface DanhMucService {

    /**
     * Save a danhMuc.
     *
     * @param danhMucDTO the entity to save
     * @return the persisted entity
     */
    DanhMucDTO save(DanhMucDTO danhMucDTO);

    /**
     * Get all the danhMucs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DanhMucDTO> findAll(Pageable pageable);

    List<DanhMucDTO> findAll();


    /**
     * Get the "id" danhMuc.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DanhMucDTO> findOne(String id);

    /**
     * Delete the "id" danhMuc.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
