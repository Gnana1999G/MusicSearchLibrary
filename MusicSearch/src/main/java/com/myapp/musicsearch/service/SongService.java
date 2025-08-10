package com.myapp.musicsearch.service;

import com.myapp.musicsearch.entity.Song;
import com.myapp.musicsearch.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ElasticsearchOperations operations;

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public List<Song> search(String value) {

        Criteria criteria = new Criteria()
                .or(new Criteria("songTitle").contains(value))
                .or(new Criteria("artist").contains(value))
                .or(new Criteria("language").contains(value))
                .or(new Criteria("album").contains(value))
                .or(new Criteria("year").contains(value));

        CriteriaQuery query = new CriteriaQuery(criteria);

        SearchHits<Song> hits = operations.search(query, Song.class);
        return hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }
}
