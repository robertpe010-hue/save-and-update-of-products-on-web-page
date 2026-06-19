package com.farmacia.empresa.auth.security;

import com.farmacia.empresa.auth.entity.UserWb;
import com.farmacia.empresa.auth.repository.UserWbRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserWbRepository userRepository;

    public UserDetailsServiceImpl(UserWbRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserWb user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        return new UserDetailsImpl(user);
    }

}
