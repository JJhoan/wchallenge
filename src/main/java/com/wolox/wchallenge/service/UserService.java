package com.wolox.wchallenge.service;

import com.wolox.wchallenge.model.Usersito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserPlaceHolder userPlaceHolder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usersito usersito = userPlaceHolder.findByUser(username);

        List<GrantedAuthority> roll = new ArrayList<>();
        roll.add(new SimpleGrantedAuthority("ADMIN"));

        return new User(usersito.getUsername(), usersito.getUsername(), roll);
    }
}
