package com.example.smarthardware.Service;

import com.example.smarthardware.Respository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }
}
