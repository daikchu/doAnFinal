package com.daicq.repository;

import com.daicq.domain.GioHang;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the GioHang entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GioHangRepository extends N1qlCouchbaseRepository<GioHang, String> {

}
