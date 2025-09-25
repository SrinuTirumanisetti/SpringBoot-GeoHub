package com.example.geohub.repository;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;

import java.util.List;

public interface CityRepository {

    List<City> getCities();

    City addCity(City city);

    City getCityById(int cityId);

    City updateCity(int cityId, City updates);

    void deleteCity(int cityId);

    Country getCountryOfCity(int cityId);
}
