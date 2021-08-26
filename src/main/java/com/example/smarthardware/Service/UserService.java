package com.example.smarthardware.Service;

import com.example.smarthardware.Entity.Role;
import com.example.smarthardware.Entity.SmartUser;

import com.example.smarthardware.Respository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SmartUser smartUser =userRepository.findByUserName(s);
        if (smartUser ==null)
            throw new EntityNotFoundException("user not found");
        String [] roles=smartUser.getRoles().stream().map(Role::getName).toArray(String[]::new);
        UserDetails userDetails = User.withUsername(smartUser.getUserName()).password(smartUser.getPassword()).authorities(roles).build();
        return userDetails;

    }
}
