package com.muhammet.repository;

import com.muhammet.repository.entity.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRoleRepository extends MongoRepository<UserRole,String> {
    List<UserRole> findAllByAuthid(Long authId);
}
