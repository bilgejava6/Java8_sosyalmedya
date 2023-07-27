package com.muhammet.repository;

import com.muhammet.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User,String> {

    Optional<User> findOptionalByAuthid(Long authId);
}
