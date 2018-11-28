package com.daicq.service.impl;

import com.daicq.service.NhaXuatBanService;
import com.daicq.domain.NhaXuatBan;
import com.daicq.repository.NhaXuatBanRepository;
import com.daicq.service.dto.NhaXuatBanDTO;
import com.daicq.service.mapper.NhaXuatBanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing NhaXuatBan.
 */
@Service
public class NhaXuatBanServiceImpl implements NhaXuatBanService {

    private final Logger log = LoggerFactory.getLogger(NhaXuatBanServiceImpl.class);

    private final NhaXuatBanRepository nhaXuatBanRepository;

    private final NhaXuatBanMapper nhaXuatBanMapper;

    public NhaXuatBanServiceImpl(NhaXuatBanRepository nhaXuatBanRepository, NhaXuatBanMapper nhaXuatBanMapper) {
        this.nhaXuatBanRepository = nhaXuatBanRepository;
        this.nhaXuatBanMapper = nhaXuatBanMapper;
    }

    /**
     * Save a nhaXuatBan.
     *
     * @param nhaXuatBanDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NhaXuatBanDTO save(NhaXuatBanDTO nhaXuatBanDTO) {
        log.debug("Request to save NhaXuatBan : {}", nhaXuatBanDTO);

        NhaXuatBan nhaXuatBan = nhaXuatBanMapper.toEntity(nhaXuatBanDTO);
        nhaXuatBan = nhaXuatBanRepository.save(nhaXuatBan);
        return nhaXuatBanMapper.toDto(nhaXuatBan);
    }

    /**
     * Get all the nhaXuatBans.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<NhaXuatBanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NhaXuatBans");
        return nhaXuatBanRepository.findAll(pageable)
            .map(nhaXuatBanMapper::toDto);
    }


    /**
     * Get one nhaXuatBan by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<NhaXuatBanDTO> findOne(String id) {
        log.debug("Request to get NhaXuatBan : {}", id);
        return nhaXuatBanRepository.findById(id)
            .map(nhaXuatBanMapper::toDto);
    }

    /**
     * Delete the nhaXuatBan by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete NhaXuatBan : {}", id);
        nhaXuatBanRepository.deleteById(id);
    }
}
