package com.daicq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.daicq.service.QuangCaoService;
import com.daicq.web.rest.errors.BadRequestAlertException;
import com.daicq.web.rest.util.HeaderUtil;
import com.daicq.web.rest.util.PaginationUtil;
import com.daicq.service.dto.QuangCaoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing QuangCao.
 */
@RestController
@RequestMapping("/api")
public class QuangCaoResource {

    private final Logger log = LoggerFactory.getLogger(QuangCaoResource.class);

    private static final String ENTITY_NAME = "quangCao";

    private final QuangCaoService quangCaoService;

    public QuangCaoResource(QuangCaoService quangCaoService) {
        this.quangCaoService = quangCaoService;
    }

    /**
     * POST  /quang-caos : Create a new quangCao.
     *
     * @param quangCaoDTO the quangCaoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quangCaoDTO, or with status 400 (Bad Request) if the quangCao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quang-caos")
    @Timed
    public ResponseEntity<QuangCaoDTO> createQuangCao(@Valid @RequestBody QuangCaoDTO quangCaoDTO) throws URISyntaxException {
        log.debug("REST request to save QuangCao : {}", quangCaoDTO);
        if (quangCaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new quangCao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuangCaoDTO result = quangCaoService.save(quangCaoDTO);
        return ResponseEntity.created(new URI("/api/quang-caos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quang-caos : Updates an existing quangCao.
     *
     * @param quangCaoDTO the quangCaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quangCaoDTO,
     * or with status 400 (Bad Request) if the quangCaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the quangCaoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quang-caos")
    @Timed
    public ResponseEntity<QuangCaoDTO> updateQuangCao(@Valid @RequestBody QuangCaoDTO quangCaoDTO) throws URISyntaxException {
        log.debug("REST request to update QuangCao : {}", quangCaoDTO);
        if (quangCaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuangCaoDTO result = quangCaoService.save(quangCaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quangCaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quang-caos : get all the quangCaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quangCaos in body
     */
    @GetMapping("/quang-caos")
    @Timed
    public ResponseEntity<List<QuangCaoDTO>> getAllQuangCaos(Pageable pageable) {
        log.debug("REST request to get a page of QuangCaos");
        Page<QuangCaoDTO> page = quangCaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quang-caos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /quang-caos/:id : get the "id" quangCao.
     *
     * @param id the id of the quangCaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quangCaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quang-caos/{id}")
    @Timed
    public ResponseEntity<QuangCaoDTO> getQuangCao(@PathVariable String id) {
        log.debug("REST request to get QuangCao : {}", id);
        Optional<QuangCaoDTO> quangCaoDTO = quangCaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quangCaoDTO);
    }

    /**
     * DELETE  /quang-caos/:id : delete the "id" quangCao.
     *
     * @param id the id of the quangCaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quang-caos/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuangCao(@PathVariable String id) {
        log.debug("REST request to delete QuangCao : {}", id);
        quangCaoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
