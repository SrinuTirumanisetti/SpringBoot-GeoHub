DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS country;

CREATE TABLE country (
    countryId   INTEGER PRIMARY KEY AUTO_INCREMENT,
    countryName TEXT,
    currency    TEXT,
    population  BIGINT,
    latitude    TEXT,
    longitude   TEXT
);

CREATE TABLE city (
    cityId      INTEGER PRIMARY KEY AUTO_INCREMENT,
    cityName    TEXT,
    population  BIGINT,
    latitude    TEXT,
    longitude   TEXT,
    countryId   INTEGER,
    CONSTRAINT fk_city_country
        FOREIGN KEY (countryId) REFERENCES country(countryId)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
