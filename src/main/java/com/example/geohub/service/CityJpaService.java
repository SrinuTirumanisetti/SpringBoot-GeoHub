package com.example.geohub.service;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CityJpaRepository;
import com.example.geohub.repository.CityRepository;
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
    public List<City> getCities() {
        return cityJpaRepository.findAll();
    }

    @Override
    public City addCity(City city) {
        // Validate country reference
        if (city.getCountry() == null || city.getCountry().getCountryId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "country.countryId is required");
        }
        Country country = countryJpaRepository.findById(city.getCountry().getCountryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        city.setCountry(country);
        city.setCityId(0); // ensure auto-increment
        return cityJpaRepository.save(city);
    }

    @Override
    public City getCityById(int cityId) {
        return cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public City updateCity(int cityId, City updates) {
        City existing = cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getCityName() != null) existing.setCityName(updates.getCityName());
        if (updates.getPopulation() != null) existing.setPopulation(updates.getPopulation());
        if (updates.getLatitude() != null) existing.setLatitude(updates.getLatitude());
        if (updates.getLongitude() != null) existing.setLongitude(updates.getLongitude());

        if (updates.getCountry() != null && updates.getCountry().getCountryId() != 0) {
            Country c = countryJpaRepository.findById(updates.getCountry().getCountryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            existing.setCountry(c);
        }

        return cityJpaRepository.save(existing);
    }

    @Override
    public void deleteCity(int cityId) {
        if (!cityJpaRepository.existsById(cityId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cityJpaRepository.deleteById(cityId);
    }

    @Override
    public Country getCountryOfCity(int cityId) {
        City city = cityJpaRepository.findById(cityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return city.getCountry();
    }
}
