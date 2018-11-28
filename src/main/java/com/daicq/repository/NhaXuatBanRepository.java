package com.daicq.repository;

import com.daicq.domain.NhaXuatBan;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the NhaXuatBan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhaXuatBanRepository extends N1qlCouchbaseRepository<NhaXuatBan, String> {

}
