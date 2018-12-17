package com.daicq.repository;

import com.daicq.domain.DanhMuc;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the DanhMuc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucRepository extends N1qlCouchbaseRepository<DanhMuc, String> {
}
