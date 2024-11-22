package com.luciano.auth.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.luciano.auth.entity.UserCredential;
import com.luciano.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(userRepository.count());

        UserCredential user = userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .disabled(user.getIsAccountDisabled())
                        .authorities(AuthorityUtils.createAuthorityList(user.getAuthority()))
                    .build();
    
    }


}
