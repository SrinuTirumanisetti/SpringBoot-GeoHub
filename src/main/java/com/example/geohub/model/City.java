package com.example.geohub.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityId")
    private int cityId;

    @Column(name = "cityName")
    private String cityName;

    @Column(name = "population")
    private Long population;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "countryId")   // matches column in your schema.sql
    private Country country;

    public City() {}

    public City(int cityId, String cityName, Long population,
                String latitude, String longitude, Country country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    public int getCityId() { return cityId; }
    public void setCityId(int cityId) { this.cityId = cityId; }

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public Long getPopulation() { return population; }
    public void setPopulation(Long population) { this.population = population; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public Country getCountry() { return country; }
    public void setCountry(Country country) { this.country = country; }
}
