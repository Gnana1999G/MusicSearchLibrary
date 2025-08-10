package com.myapp.musicsearch.repository;

import com.myapp.musicsearch.entity.Song;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SongRepository extends ElasticsearchRepository<Song, String> {
}
