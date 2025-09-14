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

import model.Country;
import service.CountryJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryJpaService countryService;

    // Get all countries
    @GetMapping
    public ArrayList<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    // Get country by ID
    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable int id) {
        return countryService.getCountryById(id);
    }

    // Create new country
    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    // Update country
    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable int id, @RequestBody Country country) {
        return countryService.updateCountry(id, country);
    }

    // Delete country
    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable int id) {
        countryService.deleteCountry(id);
    }
}
