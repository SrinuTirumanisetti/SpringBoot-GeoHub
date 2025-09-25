package com.example.geohub.service;

import com.example.geohub.model.Country;
import com.example.geohub.repository.CountryJpaRepository;
import com.example.geohub.repository.CountryRepository;
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
    public List<Country> getCountries() {
        return countryJpaRepository.findAll();
    }

    @Override
    public Country addCountry(Country country) {
        country.setCountryId(0); // ensure auto-increment
        return countryJpaRepository.save(country);
    }

    @Override
    public Country getCountryById(int countryId) {
        return countryJpaRepository.findById(countryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Country updateCountry(int countryId, Country updates) {
        Country existing = countryJpaRepository.findById(countryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getCountryName() != null) existing.setCountryName(updates.getCountryName());
        if (updates.getCurrency() != null) existing.setCurrency(updates.getCurrency());
        if (updates.getPopulation() != null) existing.setPopulation(updates.getPopulation());
        if (updates.getLatitude() != null) existing.setLatitude(updates.getLatitude());
        if (updates.getLongitude() != null) existing.setLongitude(updates.getLongitude());

        return countryJpaRepository.save(existing);
    }

    @Override
    public void deleteCountry(int countryId) {
        if (!countryJpaRepository.existsById(countryId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        countryJpaRepository.deleteById(countryId);
    }
}
