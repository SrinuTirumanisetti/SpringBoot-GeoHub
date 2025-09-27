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
s
    @GetMapping("/countries/cities")
    public List<City> getCities() {
        return cityRepository.getCities();
    }

    @PostMapping("/countries/cities")
    public City addCity(@RequestBody City city) {
        return cityRepository.addCity(city);
    }

    @GetMapping("/countries/cities/{cityId}")
    public City getCity(@PathVariable int cityId) {
        return cityRepository.getCityById(cityId);
    }

    @PutMapping("/countries/cities/{cityId}")
    public City updateCity(@PathVariable int cityId, @RequestBody City updates) {
        return cityRepository.updateCity(cityId, updates);
    }

    @DeleteMapping("/countries/cities/{cityId}")
    public void deleteCity(@PathVariable int cityId) {
        cityRepository.deleteCity(cityId);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cities/{cityId}/country")
    public Country getCountryOfCity(@PathVariable int cityId) {
        return cityRepository.getCountryOfCity(cityId);
    }
}
