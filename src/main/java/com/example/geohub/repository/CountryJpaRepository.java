package com.example.geohub.repository;

import com.example.geohub.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryJpaRepository extends JpaRepository<Country, Integer> {
}
