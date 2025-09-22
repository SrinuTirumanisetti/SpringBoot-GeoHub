package com.example.geohub.controller;

import com.example.geohub.model.Country;
import com.example.geohub.service.CountryJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryJpaService countryService;

    // 1. GET /countries
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    // 2. POST /countries
    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    // 3. GET /countries/{countryId}
    @GetMapping("/countries/{countryId}")
    public Country getCountryById(@PathVariable int countryId) {
        return countryService.getCountryById(countryId);
    }

    // 4. PUT /countries/{countryId}
    @PutMapping("/countries/{countryId}")
    public Country updateCountry(@PathVariable int countryId, @RequestBody Country country) {
        return countryService.updateCountry(countryId, country);
    }

    // 5. DELETE /countries/{countryId}
    @DeleteMapping("/countries/{countryId}")
    public void deleteCountry(@PathVariable int countryId) {
        countryService.deleteCountry(countryId);
    }
}
