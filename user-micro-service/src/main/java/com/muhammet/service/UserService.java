package com.muhammet.service;

import com.muhammet.dto.request.UserSaveRequestDto;
import com.muhammet.repository.IUserRepository;
import com.muhammet.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository repository;

    public void save(UserSaveRequestDto dto){
        repository.save(User.builder()
                        .authid(dto.getAuthid())
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                .build());
    }

    public List<User> findAll(){
        return repository.findAll();
    }
}
