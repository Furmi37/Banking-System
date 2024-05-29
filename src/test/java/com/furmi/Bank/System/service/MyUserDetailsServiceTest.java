package com.furmi.Bank.System.service;

import com.furmi.Bank.System.model.MyUser;
import com.furmi.Bank.System.repository.MyUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {
    @Mock
    private MyUserRepository userRepository;
    @InjectMocks
    private MyUserDetailsService userDetailsService;

    @Test
    void shouldLoadUserByUsername() {
        //given
        String username = "monthy";
        MyUser user = new MyUser(1,username, "password", "ADMIN");
        Optional<MyUser> userOptional = Optional.of(user);
        when(userRepository.findByUsername(username)).thenReturn(userOptional);
        UserDetails userDetailsExpected = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        //when
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //then
        assertEquals(userDetailsExpected,userDetails);
        verify(userRepository, times(1)).findByUsername(username);
    }
}