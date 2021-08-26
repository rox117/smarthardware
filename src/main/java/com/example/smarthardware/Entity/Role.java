package com.example.smarthardware.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {

    @Id
    private String name;
    private String description;

    @ManyToMany
    Set<SmartUser> smartUsers = new HashSet();
}
