package com.example.smarthardware.Respository;

import com.example.smarthardware.Entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Long> {
}
