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

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CityRepository;
import com.example.geohub.repository.CityJpaRepository;
import com.example.geohub.repository.CountryJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CityJpaService implements CityRepository {

    @Autowired
    private CityJpaRepository cityJpaRepository;

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public List<City> getAllCities() {
        return cityJpaRepository.findAll();
    }

    @Override
    public City getCityById(int cityId) {
        return cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
    }

    @Override
    public City addCity(City city) {
        if (city.getCountry() != null && city.getCountry().getCountryId() != 0) {
            Country country = countryJpaRepository.findById(city.getCountry().getCountryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
            city.setCountry(country);
        }
        return cityJpaRepository.save(city);
    }

    @Override
    public City updateCity(int cityId, City updatedCity) {
        City existingCity = cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));

        if (updatedCity.getCityName() != null) existingCity.setCityName(updatedCity.getCityName());
        if (updatedCity.getPopulation() > 0) existingCity.setPopulation(updatedCity.getPopulation());
        if (updatedCity.getLatitude() != null) existingCity.setLatitude(updatedCity.getLatitude());
        if (updatedCity.getLongitude() != null) existingCity.setLongitude(updatedCity.getLongitude());

        if (updatedCity.getCountry() != null && updatedCity.getCountry().getCountryId() != 0) {
            Country country = countryJpaRepository.findById(updatedCity.getCountry().getCountryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
            existingCity.setCountry(country);
        }

        return cityJpaRepository.save(existingCity);
    }

    @Override
    public void deleteCity(int cityId) {
        City city = cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
        cityJpaRepository.delete(city);
    }

    @Override
    public Country getCountryOfCity(int cityId) {
        City city = cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
        return city.getCountry();
    }
}
