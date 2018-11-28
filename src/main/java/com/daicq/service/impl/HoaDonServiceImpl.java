package com.daicq.service.impl;

import com.daicq.service.HoaDonService;
import com.daicq.domain.HoaDon;
import com.daicq.repository.HoaDonRepository;
import com.daicq.service.dto.HoaDonDTO;
import com.daicq.service.mapper.HoaDonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing HoaDon.
 */
@Service
public class HoaDonServiceImpl implements HoaDonService {

    private final Logger log = LoggerFactory.getLogger(HoaDonServiceImpl.class);

    private final HoaDonRepository hoaDonRepository;

    private final HoaDonMapper hoaDonMapper;

    public HoaDonServiceImpl(HoaDonRepository hoaDonRepository, HoaDonMapper hoaDonMapper) {
        this.hoaDonRepository = hoaDonRepository;
        this.hoaDonMapper = hoaDonMapper;
    }

    /**
     * Save a hoaDon.
     *
     * @param hoaDonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HoaDonDTO save(HoaDonDTO hoaDonDTO) {
        log.debug("Request to save HoaDon : {}", hoaDonDTO);

        HoaDon hoaDon = hoaDonMapper.toEntity(hoaDonDTO);
        hoaDon = hoaDonRepository.save(hoaDon);
        return hoaDonMapper.toDto(hoaDon);
    }

    /**
     * Get all the hoaDons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<HoaDonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HoaDons");
        return hoaDonRepository.findAll(pageable)
            .map(hoaDonMapper::toDto);
    }


    /**
     * Get one hoaDon by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<HoaDonDTO> findOne(String id) {
        log.debug("Request to get HoaDon : {}", id);
        return hoaDonRepository.findById(id)
            .map(hoaDonMapper::toDto);
    }

    /**
     * Delete the hoaDon by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete HoaDon : {}", id);
        hoaDonRepository.deleteById(id);
    }
}
