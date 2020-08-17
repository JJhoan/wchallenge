package com.wolox.wchallenge.security;

import com.wolox.wchallenge.dto.UserDto;
import com.wolox.wchallenge.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetail implements UserDetailsService {

    private final UserService UserService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetail(UserService UserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.UserService = UserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserDto user = UserService.findUserByUsername(username);
        if(user == null ){
            throw new UsernameNotFoundException("User does not exist");
        }
        return User.builder()
                .username(user.getUsername())
                .password(bCryptPasswordEncoder.encode(user.getUsername()))
                .roles("USER")
                .build();
    }
}
