package com.example.geohub.repository;

import com.example.geohub.model.Country;

import java.util.List;

public interface CountryRepository {

    List<Country> getCountries();

    Country addCountry(Country country);

    Country getCountryById(int countryId);

    Country updateCountry(int countryId, Country updates);

    void deleteCountry(int countryId);
}
