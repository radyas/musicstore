package com.musicstore.Service;

import com.musicstore.Model.News;
import com.musicstore.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News findNewsById(Long id){
        return newsRepository.findNewsById(id);
    }
}
