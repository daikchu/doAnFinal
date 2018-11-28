package com.daicq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.daicq.service.GioHangService;
import com.daicq.web.rest.errors.BadRequestAlertException;
import com.daicq.web.rest.util.HeaderUtil;
import com.daicq.web.rest.util.PaginationUtil;
import com.daicq.service.dto.GioHangDTO;
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
 * REST controller for managing GioHang.
 */
@RestController
@RequestMapping("/api")
public class GioHangResource {

    private final Logger log = LoggerFactory.getLogger(GioHangResource.class);

    private static final String ENTITY_NAME = "gioHang";

    private final GioHangService gioHangService;

    public GioHangResource(GioHangService gioHangService) {
        this.gioHangService = gioHangService;
    }

    /**
     * POST  /gio-hangs : Create a new gioHang.
     *
     * @param gioHangDTO the gioHangDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gioHangDTO, or with status 400 (Bad Request) if the gioHang has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gio-hangs")
    @Timed
    public ResponseEntity<GioHangDTO> createGioHang(@RequestBody GioHangDTO gioHangDTO) throws URISyntaxException {
        log.debug("REST request to save GioHang : {}", gioHangDTO);
        if (gioHangDTO.getId() != null) {
            throw new BadRequestAlertException("A new gioHang cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GioHangDTO result = gioHangService.save(gioHangDTO);
        return ResponseEntity.created(new URI("/api/gio-hangs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gio-hangs : Updates an existing gioHang.
     *
     * @param gioHangDTO the gioHangDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gioHangDTO,
     * or with status 400 (Bad Request) if the gioHangDTO is not valid,
     * or with status 500 (Internal Server Error) if the gioHangDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gio-hangs")
    @Timed
    public ResponseEntity<GioHangDTO> updateGioHang(@RequestBody GioHangDTO gioHangDTO) throws URISyntaxException {
        log.debug("REST request to update GioHang : {}", gioHangDTO);
        if (gioHangDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GioHangDTO result = gioHangService.save(gioHangDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gioHangDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gio-hangs : get all the gioHangs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gioHangs in body
     */
    @GetMapping("/gio-hangs")
    @Timed
    public ResponseEntity<List<GioHangDTO>> getAllGioHangs(Pageable pageable) {
        log.debug("REST request to get a page of GioHangs");
        Page<GioHangDTO> page = gioHangService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/gio-hangs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /gio-hangs/:id : get the "id" gioHang.
     *
     * @param id the id of the gioHangDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gioHangDTO, or with status 404 (Not Found)
     */
    @GetMapping("/gio-hangs/{id}")
    @Timed
    public ResponseEntity<GioHangDTO> getGioHang(@PathVariable String id) {
        log.debug("REST request to get GioHang : {}", id);
        Optional<GioHangDTO> gioHangDTO = gioHangService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gioHangDTO);
    }

    /**
     * DELETE  /gio-hangs/:id : delete the "id" gioHang.
     *
     * @param id the id of the gioHangDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gio-hangs/{id}")
    @Timed
    public ResponseEntity<Void> deleteGioHang(@PathVariable String id) {
        log.debug("REST request to delete GioHang : {}", id);
        gioHangService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
