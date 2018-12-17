package com.daicq.repository;

import com.daicq.domain.Book;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data Couchbase repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends N1qlCouchbaseRepository<Book, String> {
    List<Book> findAllByDanhMucId(String danhMucId);
    List<Book> findAllByNhaXB(String nhaXB);
    Book findBookById(String id);
    List<Book> findBooksByDanhMucId(String danhMucId);
}
