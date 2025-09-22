package com.example.geohub.controller;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.service.CityJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityJpaService cityService;

    // 6. GET /cities
    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    // 7. POST /cities
    @PostMapping("/cities")
    public City addCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    // 8. GET /cities/{cityId}
    @GetMapping("/cities/{cityId}")
    public City getCityById(@PathVariable int cityId) {
        return cityService.getCityById(cityId);
    }

    // 9. PUT /cities/{cityId}
    @PutMapping("/cities/{cityId}")
    public City updateCity(@PathVariable int cityId, @RequestBody City city) {
        return cityService.updateCity(cityId, city);
    }

    // 10. DELETE /cities/{cityId}
    @DeleteMapping("/cities/{cityId}")
    public void deleteCity(@PathVariable int cityId) {
        cityService.deleteCity(cityId);
    }

    // 11. GET /cities/{cityId}/country
    @GetMapping("/cities/{cityId}/country")
    public Country getCountryByCity(@PathVariable int cityId) {
        return cityService.getCountryByCityId(cityId);
    }
}
