package com.daicq.service.impl;

import com.daicq.service.DanhMucService;
import com.daicq.domain.DanhMuc;
import com.daicq.repository.DanhMucRepository;
import com.daicq.service.dto.DanhMucDTO;
import com.daicq.service.mapper.DanhMucMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing DanhMuc.
 */
@Service
public class DanhMucServiceImpl implements DanhMucService {

    private final Logger log = LoggerFactory.getLogger(DanhMucServiceImpl.class);

    private final DanhMucRepository danhMucRepository;

    private final DanhMucMapper danhMucMapper;

    public DanhMucServiceImpl(DanhMucRepository danhMucRepository, DanhMucMapper danhMucMapper) {
        this.danhMucRepository = danhMucRepository;
        this.danhMucMapper = danhMucMapper;
    }

    /**
     * Save a danhMuc.
     *
     * @param danhMucDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DanhMucDTO save(DanhMucDTO danhMucDTO) {
        log.debug("Request to save DanhMuc : {}", danhMucDTO);

        DanhMuc danhMuc = danhMucMapper.toEntity(danhMucDTO);
        danhMuc = danhMucRepository.save(danhMuc);
        return danhMucMapper.toDto(danhMuc);
    }

    /**
     * Get all the danhMucs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<DanhMucDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhMucs");
        return danhMucRepository.findAll(pageable)
            .map(danhMucMapper::toDto);
    }

    @Override
    public List<DanhMucDTO> findAll() {
        return danhMucMapper.toDto(danhMucRepository.findAll());
    }


    /**
     * Get one danhMuc by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<DanhMucDTO> findOne(String id) {
        log.debug("Request to get DanhMuc : {}", id);
        return danhMucRepository.findById(id)
            .map(danhMucMapper::toDto);
    }

    /**
     * Delete the danhMuc by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete DanhMuc : {}", id);
        danhMucRepository.deleteById(id);
    }
}
