package com.daicq.repository;

import com.daicq.domain.Book;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends N1qlCouchbaseRepository<Book, String> {

}
