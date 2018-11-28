package com.daicq.repository;

import com.daicq.domain.KhuyenMai;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the KhuyenMai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KhuyenMaiRepository extends N1qlCouchbaseRepository<KhuyenMai, String> {

}
