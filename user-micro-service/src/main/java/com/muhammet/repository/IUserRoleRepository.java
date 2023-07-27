package com.muhammet.repository;

import com.muhammet.repository.entity.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepository extends MongoRepository<UserRole,String> {

}
