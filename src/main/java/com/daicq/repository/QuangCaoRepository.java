package com.daicq.repository;

import com.daicq.domain.QuangCao;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the QuangCao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuangCaoRepository extends N1qlCouchbaseRepository<QuangCao, String> {

}
