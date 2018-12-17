package com.daicq.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.daicq.service.UsersService;
import com.daicq.web.rest.errors.BadRequestAlertException;
import com.daicq.web.rest.util.HeaderUtil;
import com.daicq.web.rest.util.PaginationUtil;
import com.daicq.service.dto.UsersDTO;
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
 * REST controller for managing Users.
 */
@RestController
@RequestMapping("/api")
public class UsersResource {

    private final Logger log = LoggerFactory.getLogger(UsersResource.class);

    private static final String ENTITY_NAME = "users";

    private final UsersService usersService;

    public UsersResource(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * POST  /users : Create a new users.
     *
     * @param usersDTO the usersDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usersDTO, or with status 400 (Bad Request) if the users has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/users")
    @Timed
    public ResponseEntity<UsersDTO> createUsers(@Valid @RequestBody UsersDTO usersDTO) throws URISyntaxException {
        log.debug("REST request to save Users : {}", usersDTO);
        if (usersDTO.getId() != null) {
            throw new BadRequestAlertException("A new users cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsersDTO result = usersService.save(usersDTO);
        return ResponseEntity.created(new URI("/api/users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /users : Updates an existing users.
     *
     * @param usersDTO the usersDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usersDTO,
     * or with status 400 (Bad Request) if the usersDTO is not valid,
     * or with status 500 (Internal Server Error) if the usersDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/users")
    @Timed
    public ResponseEntity<UsersDTO> updateUsers(@Valid @RequestBody UsersDTO usersDTO) throws URISyntaxException {
        log.debug("REST request to update Users : {}", usersDTO);
        if (usersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UsersDTO result = usersService.save(usersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usersDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /users : get all the users.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of users in body
     */
    @GetMapping("/users")
    @Timed
    public ResponseEntity<List<UsersDTO>> getAllUsers(Pageable pageable) {
        log.debug("REST request to get a page of Users");
        Page<UsersDTO> page = usersService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /users/:id : get the "id" users.
     *
     * @param id the id of the usersDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usersDTO, or with status 404 (Not Found)
     */
    @GetMapping("/users/{id}")
    @Timed
    public ResponseEntity<UsersDTO> getUsers(@PathVariable String id) {
        log.debug("REST request to get Users : {}", id);
        Optional<UsersDTO> usersDTO = usersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(usersDTO);
    }

    /**
     * DELETE  /users/:id : delete the "id" users.
     *
     * @param id the id of the usersDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/users/{id}")
    @Timed
    public ResponseEntity<Void> deleteUsers(@PathVariable String id) {
        log.debug("REST request to delete Users : {}", id);
        usersService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    @GetMapping("/users/find-by-username/{userName}")
    @Timed
    public UsersDTO getUsersByUserName(@PathVariable String userName) {
        log.debug("REST request to get Users : {}", userName);
        UsersDTO usersDTO = usersService.findByUserName(userName);
        return  usersDTO;
    }
}
