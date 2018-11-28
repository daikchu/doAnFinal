package com.daicq.service;

import com.daicq.service.dto.NhaXuatBanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NhaXuatBan.
 */
public interface NhaXuatBanService {

    /**
     * Save a nhaXuatBan.
     *
     * @param nhaXuatBanDTO the entity to save
     * @return the persisted entity
     */
    NhaXuatBanDTO save(NhaXuatBanDTO nhaXuatBanDTO);

    /**
     * Get all the nhaXuatBans.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NhaXuatBanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nhaXuatBan.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NhaXuatBanDTO> findOne(String id);

    /**
     * Delete the "id" nhaXuatBan.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
