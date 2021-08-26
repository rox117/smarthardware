package com.example.smarthardware.Service;

import com.example.smarthardware.Entity.News;
import com.example.smarthardware.Respository.NewsRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository){
        this.newsRepository=newsRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public News create(News news){
        return this.newsRepository.save(news);
    }

    public void delete (Long id){
        Optional<News> news=this.newsRepository.findById(id);
        if (news.isPresent())
            this.newsRepository.delete(news.get());
    }

    public News findById(Long id){
        return this.newsRepository.findById(id).get();
    }
}
