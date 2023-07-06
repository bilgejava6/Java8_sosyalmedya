package com.muhammet.controller;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.DoRegisterRequestDto;
import com.muhammet.dto.response.DoLoginResponseDto;
import com.muhammet.dto.response.DoRegisterResponseDto;
import com.muhammet.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.muhammet.constants.RestApis.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(LOGIN)
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
       Boolean isLogin = authService.login(dto);
       if(isLogin)
           return ResponseEntity.ok(DoLoginResponseDto.builder()
                           .status(200)
                           .result("Giriş İşlemi Başarılı")
                   .build());
       return ResponseEntity.badRequest().body(
               DoLoginResponseDto.builder()
                       .status(400)
                       .result("Giriş İşlemi Başarısız oldu. Lütfen bilgilerinizi kontrol ederek tekrar deneyiniz.")
                       .build()
       );
    }

    @PostMapping(REGISTER)
    public ResponseEntity<DoRegisterResponseDto> doRegister(@RequestBody @Valid DoRegisterRequestDto dto){
        Boolean isRegister = authService.register(dto);
        if(isRegister)
            return ResponseEntity.ok(DoRegisterResponseDto.builder()
                    .status(200)
                    .result("Kayıt İşlemi Başarılı")
                    .build());
        return ResponseEntity.badRequest().body(
                DoRegisterResponseDto.builder()
                        .status(400)
                        .result("Kayıt İşlemi Başarısız oldu. Lütfen tekrar deneyiniz.")
                        .build()
        );
    }
}
