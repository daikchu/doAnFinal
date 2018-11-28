package com.daicq.service.impl;

import com.daicq.service.GioHangService;
import com.daicq.domain.GioHang;
import com.daicq.repository.GioHangRepository;
import com.daicq.service.dto.GioHangDTO;
import com.daicq.service.mapper.GioHangMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing GioHang.
 */
@Service
public class GioHangServiceImpl implements GioHangService {

    private final Logger log = LoggerFactory.getLogger(GioHangServiceImpl.class);

    private final GioHangRepository gioHangRepository;

    private final GioHangMapper gioHangMapper;

    public GioHangServiceImpl(GioHangRepository gioHangRepository, GioHangMapper gioHangMapper) {
        this.gioHangRepository = gioHangRepository;
        this.gioHangMapper = gioHangMapper;
    }

    /**
     * Save a gioHang.
     *
     * @param gioHangDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GioHangDTO save(GioHangDTO gioHangDTO) {
        log.debug("Request to save GioHang : {}", gioHangDTO);

        GioHang gioHang = gioHangMapper.toEntity(gioHangDTO);
        gioHang = gioHangRepository.save(gioHang);
        return gioHangMapper.toDto(gioHang);
    }

    /**
     * Get all the gioHangs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<GioHangDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GioHangs");
        return gioHangRepository.findAll(pageable)
            .map(gioHangMapper::toDto);
    }


    /**
     * Get one gioHang by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<GioHangDTO> findOne(String id) {
        log.debug("Request to get GioHang : {}", id);
        return gioHangRepository.findById(id)
            .map(gioHangMapper::toDto);
    }

    /**
     * Delete the gioHang by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete GioHang : {}", id);
        gioHangRepository.deleteById(id);
    }
}
