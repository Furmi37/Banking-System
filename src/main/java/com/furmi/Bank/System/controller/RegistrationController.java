package com.furmi.Bank.System.controller;

import com.furmi.Bank.System.model.MyUser;
import com.furmi.Bank.System.repository.MyUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private MyUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register/user")
    public MyUser createUser (@RequestBody MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
