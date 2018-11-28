package com.daicq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.daicq.service.CTDHService;
import com.daicq.web.rest.errors.BadRequestAlertException;
import com.daicq.web.rest.util.HeaderUtil;
import com.daicq.web.rest.util.PaginationUtil;
import com.daicq.service.dto.CTDHDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CTDH.
 */
@RestController
@RequestMapping("/api")
public class CTDHResource {

    private final Logger log = LoggerFactory.getLogger(CTDHResource.class);

    private static final String ENTITY_NAME = "cTDH";

    private final CTDHService cTDHService;

    public CTDHResource(CTDHService cTDHService) {
        this.cTDHService = cTDHService;
    }

    /**
     * POST  /ctdhs : Create a new cTDH.
     *
     * @param cTDHDTO the cTDHDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cTDHDTO, or with status 400 (Bad Request) if the cTDH has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ctdhs")
    @Timed
    public ResponseEntity<CTDHDTO> createCTDH(@RequestBody CTDHDTO cTDHDTO) throws URISyntaxException {
        log.debug("REST request to save CTDH : {}", cTDHDTO);
        if (cTDHDTO.getId() != null) {
            throw new BadRequestAlertException("A new cTDH cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CTDHDTO result = cTDHService.save(cTDHDTO);
        return ResponseEntity.created(new URI("/api/ctdhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ctdhs : Updates an existing cTDH.
     *
     * @param cTDHDTO the cTDHDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cTDHDTO,
     * or with status 400 (Bad Request) if the cTDHDTO is not valid,
     * or with status 500 (Internal Server Error) if the cTDHDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ctdhs")
    @Timed
    public ResponseEntity<CTDHDTO> updateCTDH(@RequestBody CTDHDTO cTDHDTO) throws URISyntaxException {
        log.debug("REST request to update CTDH : {}", cTDHDTO);
        if (cTDHDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CTDHDTO result = cTDHService.save(cTDHDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cTDHDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ctdhs : get all the cTDHS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cTDHS in body
     */
    @GetMapping("/ctdhs")
    @Timed
    public ResponseEntity<List<CTDHDTO>> getAllCTDHS(Pageable pageable) {
        log.debug("REST request to get a page of CTDHS");
        Page<CTDHDTO> page = cTDHService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ctdhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ctdhs/:id : get the "id" cTDH.
     *
     * @param id the id of the cTDHDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cTDHDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ctdhs/{id}")
    @Timed
    public ResponseEntity<CTDHDTO> getCTDH(@PathVariable String id) {
        log.debug("REST request to get CTDH : {}", id);
        Optional<CTDHDTO> cTDHDTO = cTDHService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cTDHDTO);
    }

    /**
     * DELETE  /ctdhs/:id : delete the "id" cTDH.
     *
     * @param id the id of the cTDHDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ctdhs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCTDH(@PathVariable String id) {
        log.debug("REST request to delete CTDH : {}", id);
        cTDHService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
