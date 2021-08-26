package com.example.smarthardware.Respository;

import com.example.smarthardware.Entity.SmartUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SmartUser,Long> {

    public SmartUser findByUserName(String username);
}
