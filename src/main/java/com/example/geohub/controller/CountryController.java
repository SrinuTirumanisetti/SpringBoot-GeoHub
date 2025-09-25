package com.example.geohub.controller;

import com.example.geohub.model.Country;
import com.example.geohub.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CountryController {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // API 1: GET /countries
    @GetMapping("/countries")
    public List<Country> getCountries() {
        return countryRepository.getCountries();
    }

    // API 2: POST /countries
    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return countryRepository.addCountry(country);
    }

    // API 3: GET /countries/{countryId}
    @GetMapping("/countries/{countryId}")
    public Country getCountry(@PathVariable int countryId) {
        return countryRepository.getCountryById(countryId);
    }

    // API 4: PUT /countries/{countryId}
    @PutMapping("/countries/{countryId}")
    public Country updateCountry(@PathVariable int countryId,
                                 @RequestBody Country updates) {
        return countryRepository.updateCountry(countryId, updates);
    }

    // API 5: DELETE /countries/{countryId}
    @DeleteMapping("/countries/{countryId}")
    public void deleteCountry(@PathVariable int countryId) {
        countryRepository.deleteCountry(countryId);
        // As per spec: return 204 by raising NO_CONTENT
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}
