package com.example.smarthardware.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Permission {

    @Id
    private String permission;
}
