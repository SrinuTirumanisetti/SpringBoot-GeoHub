package com.example.geohub.repository;

import com.example.geohub.model.Country;
import java.util.List;

public interface CountryRepository {
    List<Country> getAllCountries();
    Country saveCountry(Country country);
    Country getCountryById(int countryId);
    Country updateCountry(int countryId, Country country);
    void deleteCountry(int countryId);
}
