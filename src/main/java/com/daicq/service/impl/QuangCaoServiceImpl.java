package com.daicq.service.impl;

import com.daicq.service.QuangCaoService;
import com.daicq.domain.QuangCao;
import com.daicq.repository.QuangCaoRepository;
import com.daicq.service.dto.QuangCaoDTO;
import com.daicq.service.mapper.QuangCaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing QuangCao.
 */
@Service
public class QuangCaoServiceImpl implements QuangCaoService {

    private final Logger log = LoggerFactory.getLogger(QuangCaoServiceImpl.class);

    private final QuangCaoRepository quangCaoRepository;

    private final QuangCaoMapper quangCaoMapper;

    public QuangCaoServiceImpl(QuangCaoRepository quangCaoRepository, QuangCaoMapper quangCaoMapper) {
        this.quangCaoRepository = quangCaoRepository;
        this.quangCaoMapper = quangCaoMapper;
    }

    /**
     * Save a quangCao.
     *
     * @param quangCaoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuangCaoDTO save(QuangCaoDTO quangCaoDTO) {
        log.debug("Request to save QuangCao : {}", quangCaoDTO);

        QuangCao quangCao = quangCaoMapper.toEntity(quangCaoDTO);
        quangCao = quangCaoRepository.save(quangCao);
        return quangCaoMapper.toDto(quangCao);
    }

    /**
     * Get all the quangCaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<QuangCaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuangCaos");
        return quangCaoRepository.findAll(pageable)
            .map(quangCaoMapper::toDto);
    }


    /**
     * Get one quangCao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<QuangCaoDTO> findOne(String id) {
        log.debug("Request to get QuangCao : {}", id);
        return quangCaoRepository.findById(id)
            .map(quangCaoMapper::toDto);
    }

    /**
     * Delete the quangCao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete QuangCao : {}", id);
        quangCaoRepository.deleteById(id);
    }
}
