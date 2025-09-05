package com.example.geohub.repository;

import com.example.geohub.model.Country;
import java.util.List;

public interface CountryRepository {
    List<Country> findAllCountries();
    Country findCountryById(int countryId);
    Country saveCountry(Country country);
    Country updateCountry(int countryId, Country country);
    void deleteCountry(int countryId);
}
