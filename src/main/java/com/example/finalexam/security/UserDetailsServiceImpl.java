package com.example.finalexam.security;

import com.example.finalexam.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService { // возвращает сразу юзера с security
    private final UserRepository userRepository;

//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //достает юзера из бд
        return SecurityUser
                .fromUserEntity(userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Такого юзера в базе нет")));
    }
}
