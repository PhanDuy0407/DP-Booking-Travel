package com.group10.bookingtravel.controller;

import com.group10.bookingtravel.dto.TourInfoDTO;
import com.group10.bookingtravel.entity.Place;
import com.group10.bookingtravel.service.PlaceService;
import com.group10.bookingtravel.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping("/places")
    private ResponseEntity<List<Place>> getPlaceList(){
        return new ResponseEntity<>(placeService.getListPlace(), HttpStatus.OK);
    }
}
