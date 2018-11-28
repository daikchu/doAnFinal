package com.daicq.repository;

import com.daicq.domain.HoaDon;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the HoaDon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoaDonRepository extends N1qlCouchbaseRepository<HoaDon, String> {

}
