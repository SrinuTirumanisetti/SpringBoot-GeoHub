package com.example.geohub.controller;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CityController {

    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // API 6: GET /countries/cities
    @GetMapping("/countries/cities")
    public List<City> getCities() {
        return cityRepository.getCities();
    }

    // API 7: POST /countries/cities
    @PostMapping("/countries/cities")
    public City addCity(@RequestBody City city) {
        return cityRepository.addCity(city);
    }

    // API 8: GET /countries/cities/{cityId}
    @GetMapping("/countries/cities/{cityId}")
    public City getCity(@PathVariable int cityId) {
        return cityRepository.getCityById(cityId);
    }

    // API 9: PUT /countries/cities/{cityId}
    @PutMapping("/countries/cities/{cityId}")
    public City updateCity(@PathVariable int cityId, @RequestBody City updates) {
        return cityRepository.updateCity(cityId, updates);
    }

    // API 10: DELETE /countries/cities/{cityId}
    @DeleteMapping("/countries/cities/{cityId}")
    public void deleteCity(@PathVariable int cityId) {
        cityRepository.deleteCity(cityId);
        // As per spec: return 204 by raising NO_CONTENT
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    // API 11: GET /cities/{cityId}/country
    @GetMapping("/cities/{cityId}/country")
    public Country getCountryOfCity(@PathVariable int cityId) {
        return cityRepository.getCountryOfCity(cityId);
    }
}
