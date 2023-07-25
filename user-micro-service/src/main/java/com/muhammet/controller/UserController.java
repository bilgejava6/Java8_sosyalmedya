package com.muhammet.controller;

import com.muhammet.dto.request.UserSaveRequestDto;
import com.muhammet.dto.response.UserSaveResponseDto;
import com.muhammet.repository.entity.User;
import com.muhammet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muhammet.constants.RestApis.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
    private final UserService userService;


    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Merhaba bu servis USER servisidir.");
    }

    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok(UserSaveResponseDto.builder()
                .status(200)
                .result("Başarılı bir şekilde kayıt edildi.")
                .build());
    }

    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
