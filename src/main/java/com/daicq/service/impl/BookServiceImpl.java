package com.daicq.service.impl;

import com.daicq.service.BookService;
import com.daicq.domain.Book;
import com.daicq.repository.BookRepository;
import com.daicq.service.dto.BookDTO;
import com.daicq.service.dto.DanhMucDTO;
import com.daicq.service.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Book.
 */
@Service
public class BookServiceImpl implements BookService {

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    /**
     * Save a book.
     *
     * @param bookDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BookDTO save(BookDTO bookDTO) {
        log.debug("Request to save Book : {}", bookDTO);

        Book book = bookMapper.toEntity(bookDTO);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    /**
     * Get all the books.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<BookDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Books");
        return bookRepository.findAll(pageable)
            .map(bookMapper::toDto);
    }

    @Override
    public List<BookDTO> findAll() {
        return bookMapper.toDto(bookRepository.findAll());
    }


    /**
     * Get one book by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<BookDTO> findOne(String id) {
        log.debug("Request to get Book : {}", id);
        return bookRepository.findById(id)
            .map(bookMapper::toDto);
    }

    @Override
    public List<BookDTO> findByDanhMucId(String danhMucId) {
        log.debug("Request to get all Book by danh muc: {}", danhMucId);
        List<Book> books = bookRepository.findAllByDanhMucId(danhMucId);
        return bookMapper.toDto(books);
    }

    @Override
    public List<BookDTO> findByNhaXB(String nhaXB) {
        log.debug("Request to get all Book by nhaXB: {}", nhaXB);
        List<Book> books = bookRepository.findAllByNhaXB(nhaXB);
        return bookMapper.toDto(books);
    }

    @Override
    public BookDTO findById(String id) {
        Book book = bookRepository.findBookById(id);
        return bookMapper.toDto(book);
    }

    /**
     * Delete the book by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Book : {}", id);
        bookRepository.deleteById(id);
    }
}
