package com.daicq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.daicq.service.KhuyenMaiService;
import com.daicq.web.rest.errors.BadRequestAlertException;
import com.daicq.web.rest.util.HeaderUtil;
import com.daicq.web.rest.util.PaginationUtil;
import com.daicq.service.dto.KhuyenMaiDTO;
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
 * REST controller for managing KhuyenMai.
 */
@RestController
@RequestMapping("/api")
public class KhuyenMaiResource {

    private final Logger log = LoggerFactory.getLogger(KhuyenMaiResource.class);

    private static final String ENTITY_NAME = "khuyenMai";

    private final KhuyenMaiService khuyenMaiService;

    public KhuyenMaiResource(KhuyenMaiService khuyenMaiService) {
        this.khuyenMaiService = khuyenMaiService;
    }

    /**
     * POST  /khuyen-mais : Create a new khuyenMai.
     *
     * @param khuyenMaiDTO the khuyenMaiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new khuyenMaiDTO, or with status 400 (Bad Request) if the khuyenMai has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/khuyen-mais")
    @Timed
    public ResponseEntity<KhuyenMaiDTO> createKhuyenMai(@Valid @RequestBody KhuyenMaiDTO khuyenMaiDTO) throws URISyntaxException {
        log.debug("REST request to save KhuyenMai : {}", khuyenMaiDTO);
        if (khuyenMaiDTO.getId() != null) {
            throw new BadRequestAlertException("A new khuyenMai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KhuyenMaiDTO result = khuyenMaiService.save(khuyenMaiDTO);
        return ResponseEntity.created(new URI("/api/khuyen-mais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /khuyen-mais : Updates an existing khuyenMai.
     *
     * @param khuyenMaiDTO the khuyenMaiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated khuyenMaiDTO,
     * or with status 400 (Bad Request) if the khuyenMaiDTO is not valid,
     * or with status 500 (Internal Server Error) if the khuyenMaiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/khuyen-mais")
    @Timed
    public ResponseEntity<KhuyenMaiDTO> updateKhuyenMai(@Valid @RequestBody KhuyenMaiDTO khuyenMaiDTO) throws URISyntaxException {
        log.debug("REST request to update KhuyenMai : {}", khuyenMaiDTO);
        if (khuyenMaiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KhuyenMaiDTO result = khuyenMaiService.save(khuyenMaiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, khuyenMaiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /khuyen-mais : get all the khuyenMais.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of khuyenMais in body
     */
    @GetMapping("/khuyen-mais")
    @Timed
    public ResponseEntity<List<KhuyenMaiDTO>> getAllKhuyenMais(Pageable pageable) {
        log.debug("REST request to get a page of KhuyenMais");
        Page<KhuyenMaiDTO> page = khuyenMaiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/khuyen-mais");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /khuyen-mais/:id : get the "id" khuyenMai.
     *
     * @param id the id of the khuyenMaiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the khuyenMaiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/khuyen-mais/{id}")
    @Timed
    public ResponseEntity<KhuyenMaiDTO> getKhuyenMai(@PathVariable String id) {
        log.debug("REST request to get KhuyenMai : {}", id);
        Optional<KhuyenMaiDTO> khuyenMaiDTO = khuyenMaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(khuyenMaiDTO);
    }

    /**
     * DELETE  /khuyen-mais/:id : delete the "id" khuyenMai.
     *
     * @param id the id of the khuyenMaiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/khuyen-mais/{id}")
    @Timed
    public ResponseEntity<Void> deleteKhuyenMai(@PathVariable String id) {
        log.debug("REST request to delete KhuyenMai : {}", id);
        khuyenMaiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
