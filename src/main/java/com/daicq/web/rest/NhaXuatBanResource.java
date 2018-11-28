package com.daicq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.daicq.service.NhaXuatBanService;
import com.daicq.web.rest.errors.BadRequestAlertException;
import com.daicq.web.rest.util.HeaderUtil;
import com.daicq.web.rest.util.PaginationUtil;
import com.daicq.service.dto.NhaXuatBanDTO;
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
 * REST controller for managing NhaXuatBan.
 */
@RestController
@RequestMapping("/api")
public class NhaXuatBanResource {

    private final Logger log = LoggerFactory.getLogger(NhaXuatBanResource.class);

    private static final String ENTITY_NAME = "nhaXuatBan";

    private final NhaXuatBanService nhaXuatBanService;

    public NhaXuatBanResource(NhaXuatBanService nhaXuatBanService) {
        this.nhaXuatBanService = nhaXuatBanService;
    }

    /**
     * POST  /nha-xuat-bans : Create a new nhaXuatBan.
     *
     * @param nhaXuatBanDTO the nhaXuatBanDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nhaXuatBanDTO, or with status 400 (Bad Request) if the nhaXuatBan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nha-xuat-bans")
    @Timed
    public ResponseEntity<NhaXuatBanDTO> createNhaXuatBan(@Valid @RequestBody NhaXuatBanDTO nhaXuatBanDTO) throws URISyntaxException {
        log.debug("REST request to save NhaXuatBan : {}", nhaXuatBanDTO);
        if (nhaXuatBanDTO.getId() != null) {
            throw new BadRequestAlertException("A new nhaXuatBan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhaXuatBanDTO result = nhaXuatBanService.save(nhaXuatBanDTO);
        return ResponseEntity.created(new URI("/api/nha-xuat-bans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nha-xuat-bans : Updates an existing nhaXuatBan.
     *
     * @param nhaXuatBanDTO the nhaXuatBanDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nhaXuatBanDTO,
     * or with status 400 (Bad Request) if the nhaXuatBanDTO is not valid,
     * or with status 500 (Internal Server Error) if the nhaXuatBanDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nha-xuat-bans")
    @Timed
    public ResponseEntity<NhaXuatBanDTO> updateNhaXuatBan(@Valid @RequestBody NhaXuatBanDTO nhaXuatBanDTO) throws URISyntaxException {
        log.debug("REST request to update NhaXuatBan : {}", nhaXuatBanDTO);
        if (nhaXuatBanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhaXuatBanDTO result = nhaXuatBanService.save(nhaXuatBanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nhaXuatBanDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nha-xuat-bans : get all the nhaXuatBans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of nhaXuatBans in body
     */
    @GetMapping("/nha-xuat-bans")
    @Timed
    public ResponseEntity<List<NhaXuatBanDTO>> getAllNhaXuatBans(Pageable pageable) {
        log.debug("REST request to get a page of NhaXuatBans");
        Page<NhaXuatBanDTO> page = nhaXuatBanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nha-xuat-bans");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /nha-xuat-bans/:id : get the "id" nhaXuatBan.
     *
     * @param id the id of the nhaXuatBanDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nhaXuatBanDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nha-xuat-bans/{id}")
    @Timed
    public ResponseEntity<NhaXuatBanDTO> getNhaXuatBan(@PathVariable String id) {
        log.debug("REST request to get NhaXuatBan : {}", id);
        Optional<NhaXuatBanDTO> nhaXuatBanDTO = nhaXuatBanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nhaXuatBanDTO);
    }

    /**
     * DELETE  /nha-xuat-bans/:id : delete the "id" nhaXuatBan.
     *
     * @param id the id of the nhaXuatBanDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nha-xuat-bans/{id}")
    @Timed
    public ResponseEntity<Void> deleteNhaXuatBan(@PathVariable String id) {
        log.debug("REST request to delete NhaXuatBan : {}", id);
        nhaXuatBanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
