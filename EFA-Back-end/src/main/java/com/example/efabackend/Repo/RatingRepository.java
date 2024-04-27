package com.example.efabackend.Repo;

import com.example.efabackend.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> findAll();
    List<Rating> findRatingByIdIs(Long fileId);


}
