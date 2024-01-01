package com.vtys.serverhealthapi.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vtys.serverhealthapi.entity.Roles;
import com.vtys.serverhealthapi.entity.Users;
import com.vtys.serverhealthapi.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

 
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        return new User(user.getUsername(), user.getUserpassword(), mapRolesToAuthorities(user.getRolesList()));
       
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Roles> roles) {
        return  roles.stream().map(role-> new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
    }



  
}
