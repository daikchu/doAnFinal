package com.daicq.service.impl;

import com.daicq.service.CTDHService;
import com.daicq.domain.CTDH;
import com.daicq.repository.CTDHRepository;
import com.daicq.service.dto.CTDHDTO;
import com.daicq.service.mapper.CTDHMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing CTDH.
 */
@Service
public class CTDHServiceImpl implements CTDHService {

    private final Logger log = LoggerFactory.getLogger(CTDHServiceImpl.class);

    private final CTDHRepository cTDHRepository;

    private final CTDHMapper cTDHMapper;

    public CTDHServiceImpl(CTDHRepository cTDHRepository, CTDHMapper cTDHMapper) {
        this.cTDHRepository = cTDHRepository;
        this.cTDHMapper = cTDHMapper;
    }

    /**
     * Save a cTDH.
     *
     * @param cTDHDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CTDHDTO save(CTDHDTO cTDHDTO) {
        log.debug("Request to save CTDH : {}", cTDHDTO);

        CTDH cTDH = cTDHMapper.toEntity(cTDHDTO);
        cTDH = cTDHRepository.save(cTDH);
        return cTDHMapper.toDto(cTDH);
    }

    /**
     * Get all the cTDHS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<CTDHDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CTDHS");
        return cTDHRepository.findAll(pageable)
            .map(cTDHMapper::toDto);
    }


    /**
     * Get one cTDH by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<CTDHDTO> findOne(String id) {
        log.debug("Request to get CTDH : {}", id);
        return cTDHRepository.findById(id)
            .map(cTDHMapper::toDto);
    }

    /**
     * Delete the cTDH by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete CTDH : {}", id);
        cTDHRepository.deleteById(id);
    }
}
