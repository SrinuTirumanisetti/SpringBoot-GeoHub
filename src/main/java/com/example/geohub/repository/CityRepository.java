package com.example.geohub.repository;

import com.example.geohub.model.City;
import java.util.List;

public interface CityRepository {
    List<City> findAllCities();
    City findCityById(int cityId);
    City saveCity(City city);
    City updateCity(int cityId, City city);
    void deleteCity(int cityId);
}
