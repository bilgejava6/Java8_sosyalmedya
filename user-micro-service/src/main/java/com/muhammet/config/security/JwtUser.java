package com.muhammet.config.security;

import com.muhammet.exceptions.ErrorType;
import com.muhammet.exceptions.UserException;
import com.muhammet.repository.IUserRepository;
import com.muhammet.repository.IUserRoleRepository;
import com.muhammet.repository.entity.User;
import com.muhammet.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUser implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails getUserByAuthId(Long authId){
        Optional<User> user = userRepository.findOptionalByAuthid(authId);
        if(user.isEmpty()) throw new UserException(ErrorType.INVALID_USER);
        // TODO: kullanıcıya ait rolleri userrole tablosundan çekeceğiz.
        List<GrantedAuthority> authorities = new ArrayList<>();
        userRoleService.findAllByAuthid(authId).forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });


        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.get().getUsername())
                .password("")
                .accountExpired(false)
                .accountLocked(false)
                .authorities(authorities)
                .build();
    }
}
