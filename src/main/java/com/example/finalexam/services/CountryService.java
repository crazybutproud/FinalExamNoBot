package com.example.finalexam.services;

import com.example.finalexam.dto.TestPageDTO;
import com.example.finalexam.model.Country;
import com.example.finalexam.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

//    @Autowired
//    public CountryService(CountryRepository countryRepository) {
//        this.countryRepository = countryRepository;
//    }

    public List<Country> findAll() { //вывод всех стран
        return (List<Country>) countryRepository.findAll();
    }

    public Country findOne(int id) { //вывод страны по айди
        return countryRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Country country) {//добавление страны
        countryRepository.save(country);
    }

    @Transactional
    public void update(int id, Country country) {
        country.setId(id);
        countryRepository.save(country);
    }

    @Transactional
    public void delete(int id) {
        countryRepository.deleteById(id);
    }

    public List<Country> findCountriesByBestTimeAndSeaAndMountain
            (String bestTime, boolean sea, boolean mountain) {
        List<Country> bestByNature =
                countryRepository
                        .findByBestTimeAndSeaIsAndMountainIs(bestTime, sea, mountain);
        return bestByNature;
    }

    public List<Country> findCountriesByTemAndSeaTem(int tem, int tempSea) {
        List<Country> bestByTem =
                countryRepository
                        .findByTemGreaterThanEqualAndSeaTemGreaterThanEqual(tem, tempSea);
        return bestByTem;
    }

    public List<Country> findCountriesByBestTime(String season) {
        List<Country> bestBySeason = countryRepository.findByBestTime(season);
        return bestBySeason;
    }

    public List<Country> findCountriesBySeaIs(boolean sea) {
        List<Country> bestBySea = countryRepository.findBySeaIs(sea);
        return bestBySea;
    }

    public List<Country> findCountriesByMountainIs(boolean mountain) {
        List<Country> bestByMountain = countryRepository.findByMountainIs(mountain);
        return bestByMountain;
    }

    public List<Country> findCountriesByTemBeforeAndTemContaining(int tem) {
        List<Country> bestByTem = countryRepository.findByTemGreaterThanEqual(tem);
        return bestByTem;
    }

    public List<Country> findCountriesBySeaTemBeforeAndSeaTemContaining(int temSea) {
        List<Country> bestBySeaTem = countryRepository.findBySeaTemGreaterThanEqual(temSea);
        return bestBySeaTem;
    }

    public Country searchBest(TestPageDTO testPageDTO) { //метод поиска лучшей страны.
        // получаем параметры через контроллер и кастомным jpa ищем самую лучшую страну,
        // если не находим ищем по параметрам море/горы/сезон
        System.out.println("U ur here");
        String season = testPageDTO.getSeason();
        boolean sea = testPageDTO.isSea();
        boolean mountain = testPageDTO.isMountain();
        int tem = testPageDTO.getTem();
        int temSea = testPageDTO.getTemSea();
        System.out.println("Параметры собраны");
        List<Country> bestBySeason = findCountriesByBestTime(season);
        System.out.println("This is best by season");
        List<Country> bestByTem = findCountriesByTemBeforeAndTemContaining(tem);
        System.out.println("This is best by tem");
        List<Country> bestBySeaTem = findCountriesBySeaTemBeforeAndSeaTemContaining(temSea);
        System.out.println("This is best by temSea");
        List<Country> bestBySea = findCountriesBySeaIs(sea);
        System.out.println("This is best by sea");
        List<Country> bestByMountain = findCountriesByMountainIs(mountain);
        System.out.println("This is best by mountain");

        Set<Country> commonCountries = new HashSet<>(bestBySeason);
        commonCountries.retainAll(new HashSet<>(bestBySea));
        commonCountries.retainAll(new HashSet<>(bestByMountain));
        commonCountries.retainAll(new HashSet<>(bestByTem));
        commonCountries.retainAll(new HashSet<>(bestBySeaTem));
        ////////////////////////////////////////////////////////
        List<Country> bestByNature2 = findCountriesByBestTimeAndSeaAndMountain(season, sea, mountain);
//        List<Country> bestByTem2 = findCountriesByTemAndSeaTem(tem, temSea);
        Set<Country> commonCountries2 = new HashSet<>(bestByNature2);
//        commonCountries.retainAll(new HashSet<>(bestByTem2));

        Country theBest = null;
        if (commonCountries.isEmpty()) {
            theBest = commonCountries2.iterator().next();
            System.out.println("Нужной страны не найдено");
        } else {
            theBest = commonCountries.iterator().next();
            System.out.println("Подходящая страна: " + theBest);
        }
        return theBest;
    }
}
