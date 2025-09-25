package com.example.geohub.repository;

import com.example.geohub.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityJpaRepository extends JpaRepository<City, Integer> {
}
