/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here

package com.example.geohub.service;

import com.example.geohub.model.Country;
import com.example.geohub.repository.CountryRepository;
import com.example.geohub.repository.CountryJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CountryJpaService implements CountryRepository {

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryJpaRepository.findAll();
    }

    @Override
    public Country getCountryById(int countryId) {
        return countryJpaRepository.findById(countryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
    }

    @Override
    public Country addCountry(Country country) {
        return countryJpaRepository.save(country);
    }

    @Override
    public Country updateCountry(int countryId, Country updatedCountry) {
        Country existingCountry = countryJpaRepository.findById(countryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        if (updatedCountry.getCountryName() != null) existingCountry.setCountryName(updatedCountry.getCountryName());
        if (updatedCountry.getCurrency() != null) existingCountry.setCurrency(updatedCountry.getCurrency());
        if (updatedCountry.getPopulation() > 0) existingCountry.setPopulation(updatedCountry.getPopulation());
        if (updatedCountry.getLatitude() != null) existingCountry.setLatitude(updatedCountry.getLatitude());
        if (updatedCountry.getLongitude() != null) existingCountry.setLongitude(updatedCountry.getLongitude());

        return countryJpaRepository.save(existingCountry);
    }

    @Override
    public void deleteCountry(int countryId) {
        Country country = countryJpaRepository.findById(countryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
        countryJpaRepository.delete(country);
    }
}
