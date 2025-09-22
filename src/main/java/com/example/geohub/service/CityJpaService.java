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
    public City saveCity(City city) {
        // validate country before saving
        int countryId = city.getCountry().getCountryId();
        countryJpaRepository.findById(countryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        return cityJpaRepository.save(city);
    }

    @Override
    public City getCityById(int cityId) {
        return cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
    }

    @Override
    public City updateCity(int cityId, City city) {
        City existing = cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));

        existing.setCityName(city.getCityName());
        existing.setPopulation(city.getPopulation());
        existing.setCountry(city.getCountry());
        return cityJpaRepository.save(existing);
    }

    @Override
    public void deleteCity(int cityId) {
        City existing = cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
        cityJpaRepository.delete(existing);
    }

    @Override
    public Country getCountryByCityId(int cityId) {
        City city = cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found"));
        return city.getCountry();
    }
}
