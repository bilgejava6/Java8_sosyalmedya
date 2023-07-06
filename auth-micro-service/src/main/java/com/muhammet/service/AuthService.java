package com.muhammet.service;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.DoRegisterRequestDto;
import com.muhammet.exceptions.AuthException;
import com.muhammet.exceptions.ErrorType;
import com.muhammet.mapper.IAuthMapper;
import com.muhammet.repository.IAuthRepository;
import com.muhammet.repository.entity.Auth;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    public AuthService(IAuthRepository repository) {
        super(repository);
        this.repository = repository;
    }
    /**
     * Register a new user
     * Login
     * Not:
     * - Kayıt olurken eğer aynı kullanıcı adı var ise hata döndürülecek
     * - Kayıt başarılı ise olumsu sonuç dönülecek
     * - Giriş yapancı kullanıcıya bir JWT token döndürülecek
     * - Giriş tesorun olursa sorun bilgisi DTO olarak dönülecek.
     */
    public Boolean register(DoRegisterRequestDto dto){
       if(!dto.getPassword().equals(dto.getPasswordConfirm())) // eğer şifre ile şifre doğrulama eşit değiş ise hata fırlat
         throw new AuthException(ErrorType.REGISTER_PASSWORDS_NOT_MATCH);
       repository.findOptionalByUsername(dto.getUsername()) // eğer kullanıcı adı var ise hata fırlat
               .ifPresent(auth -> {
                   throw new AuthException(ErrorType.REGISTER_KULLANICIADI_KAYITLI);
               });
//       Auth auth = Auth.builder()
//               .email(dto.getEmail())
//               .username(dto.getUsername())
//               .password(dto.getPassword())
//               .build();
        Auth auth = IAuthMapper.INSTANCE.authFromDto(dto);
       repository.save(auth);
       return true;
    }
    public Boolean login(DoLoginRequestDto dto){
      Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        /**
         * DİKKAT!!! burada iki yolumuz var;
         * 1- Auth bilgisini sorgulayarak kullanıcı yok ise false dönebiliriz.
         *     if(auth.isEmpty())
         *             return false;
         *      return auth.isPresent();
         * 2- Auth bilgisini sorgulayarak kullabıcı yok ise ya da bilgileri yanlış ise exception fırlatabiliriz.
         */
      if(auth.isEmpty()) throw new AuthException(ErrorType.DOLOGIN_INVALID_USERNAME_PASSWORD);
      return true;
    }

    public Optional<Auth> loginAlternatif(DoLoginRequestDto dto){
       return repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
    }


}
