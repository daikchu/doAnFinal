package com.daicq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.daicq.service.HoaDonService;
import com.daicq.web.rest.errors.BadRequestAlertException;
import com.daicq.web.rest.util.HeaderUtil;
import com.daicq.web.rest.util.PaginationUtil;
import com.daicq.service.dto.HoaDonDTO;
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
 * REST controller for managing HoaDon.
 */
@RestController
@RequestMapping("/api")
public class HoaDonResource {

    private final Logger log = LoggerFactory.getLogger(HoaDonResource.class);

    private static final String ENTITY_NAME = "hoaDon";

    private final HoaDonService hoaDonService;

    public HoaDonResource(HoaDonService hoaDonService) {
        this.hoaDonService = hoaDonService;
    }

    /**
     * POST  /hoa-dons : Create a new hoaDon.
     *
     * @param hoaDonDTO the hoaDonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hoaDonDTO, or with status 400 (Bad Request) if the hoaDon has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hoa-dons")
    @Timed
    public ResponseEntity<HoaDonDTO> createHoaDon(@RequestBody HoaDonDTO hoaDonDTO) throws URISyntaxException {
        log.debug("REST request to save HoaDon : {}", hoaDonDTO);
        if (hoaDonDTO.getId() != null) {
            throw new BadRequestAlertException("A new hoaDon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoaDonDTO result = hoaDonService.save(hoaDonDTO);
        return ResponseEntity.created(new URI("/api/hoa-dons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hoa-dons : Updates an existing hoaDon.
     *
     * @param hoaDonDTO the hoaDonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hoaDonDTO,
     * or with status 400 (Bad Request) if the hoaDonDTO is not valid,
     * or with status 500 (Internal Server Error) if the hoaDonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hoa-dons")
    @Timed
    public ResponseEntity<HoaDonDTO> updateHoaDon(@RequestBody HoaDonDTO hoaDonDTO) throws URISyntaxException {
        log.debug("REST request to update HoaDon : {}", hoaDonDTO);
        if (hoaDonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HoaDonDTO result = hoaDonService.save(hoaDonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hoaDonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hoa-dons : get all the hoaDons.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of hoaDons in body
     */
    @GetMapping("/hoa-dons")
    @Timed
    public ResponseEntity<List<HoaDonDTO>> getAllHoaDons(Pageable pageable) {
        log.debug("REST request to get a page of HoaDons");
        Page<HoaDonDTO> page = hoaDonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hoa-dons");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /hoa-dons/:id : get the "id" hoaDon.
     *
     * @param id the id of the hoaDonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hoaDonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/hoa-dons/{id}")
    @Timed
    public ResponseEntity<HoaDonDTO> getHoaDon(@PathVariable String id) {
        log.debug("REST request to get HoaDon : {}", id);
        Optional<HoaDonDTO> hoaDonDTO = hoaDonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hoaDonDTO);
    }

    /**
     * DELETE  /hoa-dons/:id : delete the "id" hoaDon.
     *
     * @param id the id of the hoaDonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hoa-dons/{id}")
    @Timed
    public ResponseEntity<Void> deleteHoaDon(@PathVariable String id) {
        log.debug("REST request to delete HoaDon : {}", id);
        hoaDonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
