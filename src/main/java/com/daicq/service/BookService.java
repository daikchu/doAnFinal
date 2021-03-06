package com.daicq.service;

import com.daicq.service.dto.BookDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Book.
 */
public interface BookService {

    /**
     * Save a book.
     *
     * @param bookDTO the entity to save
     * @return the persisted entity
     */
    BookDTO save(BookDTO bookDTO);

    /**
     * Get all the books.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BookDTO> findAll(Pageable pageable);

    List<BookDTO> findAll();

    /**
     * Get the "id" book.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BookDTO> findOne(String id);

    List<BookDTO> findByDanhMucId(String danhMucId);

    List<BookDTO> findByNhaXB(String nhaXB);

    BookDTO findById(String id);

    /**
     * Delete the "id" book.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
