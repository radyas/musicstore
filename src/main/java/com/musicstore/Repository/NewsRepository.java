package com.musicstore.Repository;

import com.musicstore.Model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    News findNewsById(Long id);
}
