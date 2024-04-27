package com.example.efabackend.service.impl;

import com.example.efabackend.entity.Rating;
import com.example.efabackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<Rating> getAllRatings();
    List<Rating> getByid(Long id);
    Rating createRating(Rating rating);




}
