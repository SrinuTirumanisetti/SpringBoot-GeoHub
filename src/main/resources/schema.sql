DROP TABLE IF EXISTS City;
DROP TABLE IF EXISTS Country;

CREATE TABLE Country (
    countryId INTEGER PRIMARY KEY AUTOINCREMENT,
    countryName TEXT NOT NULL,
    currency TEXT,
    population INTEGER,
    latitude TEXT,
    longitude TEXT
);

CREATE TABLE City (
    cityId INTEGER PRIMARY KEY AUTOINCREMENT,
    cityName TEXT NOT NULL,
    population INTEGER,
    latitude TEXT,
    longitude TEXT,
    countryId INTEGER,
    FOREIGN KEY (countryId) REFERENCES Country(countryId) ON DELETE CASCADE ON UPDATE CASCADE
);
