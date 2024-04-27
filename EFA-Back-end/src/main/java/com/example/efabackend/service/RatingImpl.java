package com.example.efabackend.service;

import com.example.efabackend.Repo.RatingRepository;
import com.example.efabackend.entity.Rating;
import com.example.efabackend.service.impl.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getByid(Long id){
        return ratingRepository.findRatingByIdIs(id);
    }

    @Override
    public Rating createRating(Rating rating) {
        Rating savedRating = ratingRepository.save(rating);
        return savedRating;
    }
}
