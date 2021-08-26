package com.example.smarthardware.Controller;

import com.example.smarthardware.Entity.News;
import com.example.smarthardware.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;


    @GetMapping(path = "/{id}")
    public ResponseEntity<News> getnewss(@PathVariable Long id){
        News news=this.newsService.findById(id);
        return ResponseEntity.ok(news);

    }

    @PostMapping(path = "")
    public ResponseEntity<News> create(@RequestBody News news){
        News news1=this.newsService.create(news);
        return ResponseEntity.ok(news1);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.newsService.delete(id);
        return ResponseEntity.ok("news deleted");
    }
}
