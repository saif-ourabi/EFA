package com.example.efabackend.Controller.filleController;

import com.example.efabackend.Dto.RatingDto;
import com.example.efabackend.entity.File;
import com.example.efabackend.entity.Rating;
import com.example.efabackend.entity.User;
import com.example.efabackend.service.impl.RatingService;
import com.example.efabackend.service.impl.fileService;
import com.example.efabackend.service.impl.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/files/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private userService userService;
    @Autowired
    private fileService fileService;


    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<Rating>> getByid(@PathVariable long id) {
        List<Rating> ratings = ratingService.getByid(id);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody RatingDto ratingDto) {
        File f=fileService.getFileById(ratingDto.getFileId());
        User u=userService.findbyEmail(ratingDto.getEmail());
        Rating rating=new Rating(u,f,ratingDto.getStars(),ratingDto.getDescription());
        Rating createdRating = ratingService.createRating(rating);
        return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
    }
}
