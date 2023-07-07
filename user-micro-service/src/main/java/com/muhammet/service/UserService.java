package com.muhammet.service;

import com.muhammet.repository.IUserRepository;
import com.muhammet.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository repository;

    public User save(User user){
        return repository.save(user);
    }

    public List<User> findAll(){
        return repository.findAll();
    }
}
