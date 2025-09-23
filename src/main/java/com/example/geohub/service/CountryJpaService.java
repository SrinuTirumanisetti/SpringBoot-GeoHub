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
    public Country saveCountry(Country country) {
        return countryJpaRepository.save(country);
    }

    @Override
    public Country getCountryById(int countryId) {
        return countryJpaRepository.findById(countryId)
                .orElseThrow(() -> {
                    log.warn("Country with ID {} not found", countryId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
                });
    }


    @Override
    public Country updateCountry(int countryId, Country country) {
        Country existing = countryJpaRepository.findById(countryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        existing.setCountryName(country.getCountryName());
        existing.setPopulation(country.getPopulation());
        return countryJpaRepository.save(existing);
    }

    @Override
    public void deleteCountry(int countryId) {
        Country existing = countryJpaRepository.findById(countryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
        countryJpaRepository.delete(existing);
    }
}
