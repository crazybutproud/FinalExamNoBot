package com.example.finalexam.repositories;

import com.example.finalexam.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
    // кастомные jpa запросы на поиск лучшей страны из бд по данным из контроллера
    //поиск по параметрам-лучшее время,наличие моря и гор
    List<Country> findByBestTimeAndSeaIsAndMountainIs(String bestTime, boolean sea, boolean mountain);

    //поиск по параметрам температуры
    List<Country> findByTemGreaterThanEqualAndSeaTemGreaterThanEqual(int tem, int tempSea);


    List<Country> findByBestTime(String season);
    List<Country> findBySeaIs(boolean sea);
    List<Country> findByMountainIs(boolean mountain);
    List<Country> findByTemGreaterThanEqual(int tem);
    List<Country> findBySeaTemGreaterThanEqual(int seaTem);
}
