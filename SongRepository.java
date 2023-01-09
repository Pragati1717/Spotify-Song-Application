package com.niit.Spotify.repository;

import com.niit.Spotify.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<User,String> {
}
