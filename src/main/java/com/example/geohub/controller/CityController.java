/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here

package controller;

import model.City;
import service.CityJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityJpaService cityService;

    // Get all cities
    @GetMapping
    public ArrayList<City> getAllCities() {
        return cityService.getAllCities();
    }

    // Get city by ID
    @GetMapping("/{id}")
    public City getCityById(@PathVariable int id) {
        return cityService.getCityById(id);
    }

    // Create new city
    @PostMapping
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    // Update city
    @PutMapping("/{id}")
    public City updateCity(@PathVariable int id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }

    // Delete city
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable int id) {
        cityService.deleteCity(id);
    }
}
