package com.example.geohub.repository;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import java.util.List;

public interface CityRepository {
    List<City> getAllCities();
    City saveCity(City city);
    City getCityById(int cityId);
    City updateCity(int cityId, City city);
    void deleteCity(int cityId);
    Country getCountryByCityId(int cityId);
}
