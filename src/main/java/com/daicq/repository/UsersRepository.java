package com.daicq.repository;

import com.daicq.domain.Users;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Couchbase repository for the Users entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsersRepository extends N1qlCouchbaseRepository<Users, String> {
    Users findByUserName(String userName);
}
