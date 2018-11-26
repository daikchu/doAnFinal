package com.daicq.repository;

import com.daicq.domain.Authority;


/**
 * Spring Data Couchbase repository for the Authority entity.
 */
public interface AuthorityRepository extends N1qlCouchbaseRepository<Authority, String> {
}
