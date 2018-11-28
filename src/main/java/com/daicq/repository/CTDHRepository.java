package com.daicq.repository;

import com.daicq.domain.CTDH;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the CTDH entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CTDHRepository extends N1qlCouchbaseRepository<CTDH, String> {

}
