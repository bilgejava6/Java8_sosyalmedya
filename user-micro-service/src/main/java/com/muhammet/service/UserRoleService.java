package com.muhammet.service;


import com.muhammet.repository.IUserRoleRepository;
import com.muhammet.repository.entity.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRoleService {
    private final IUserRoleRepository userRepository;

    public void save(UserRole userRole){
        userRepository.save(userRole);
    }

    public List<UserRole> findAllByAuthid(Long authId){
        return userRepository.findAllByAuthid(authId);
    }
}
