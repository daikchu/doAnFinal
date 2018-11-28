package com.daicq.service.impl;

import com.daicq.service.KhuyenMaiService;
import com.daicq.domain.KhuyenMai;
import com.daicq.repository.KhuyenMaiRepository;
import com.daicq.service.dto.KhuyenMaiDTO;
import com.daicq.service.mapper.KhuyenMaiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing KhuyenMai.
 */
@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    private final Logger log = LoggerFactory.getLogger(KhuyenMaiServiceImpl.class);

    private final KhuyenMaiRepository khuyenMaiRepository;

    private final KhuyenMaiMapper khuyenMaiMapper;

    public KhuyenMaiServiceImpl(KhuyenMaiRepository khuyenMaiRepository, KhuyenMaiMapper khuyenMaiMapper) {
        this.khuyenMaiRepository = khuyenMaiRepository;
        this.khuyenMaiMapper = khuyenMaiMapper;
    }

    /**
     * Save a khuyenMai.
     *
     * @param khuyenMaiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public KhuyenMaiDTO save(KhuyenMaiDTO khuyenMaiDTO) {
        log.debug("Request to save KhuyenMai : {}", khuyenMaiDTO);

        KhuyenMai khuyenMai = khuyenMaiMapper.toEntity(khuyenMaiDTO);
        khuyenMai = khuyenMaiRepository.save(khuyenMai);
        return khuyenMaiMapper.toDto(khuyenMai);
    }

    /**
     * Get all the khuyenMais.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<KhuyenMaiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KhuyenMais");
        return khuyenMaiRepository.findAll(pageable)
            .map(khuyenMaiMapper::toDto);
    }


    /**
     * Get one khuyenMai by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<KhuyenMaiDTO> findOne(String id) {
        log.debug("Request to get KhuyenMai : {}", id);
        return khuyenMaiRepository.findById(id)
            .map(khuyenMaiMapper::toDto);
    }

    /**
     * Delete the khuyenMai by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete KhuyenMai : {}", id);
        khuyenMaiRepository.deleteById(id);
    }
}
