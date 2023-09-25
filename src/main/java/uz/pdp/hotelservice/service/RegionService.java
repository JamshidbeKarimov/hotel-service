package uz.pdp.hotelservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.hotelservice.domain.dto.region.CityDto;
import uz.pdp.hotelservice.domain.entity.region.City;
import uz.pdp.hotelservice.domain.entity.region.Country;
import uz.pdp.hotelservice.exeption.DataNotFoundException;
import uz.pdp.hotelservice.repository.regionRepositories.CityRepository;
import uz.pdp.hotelservice.repository.regionRepositories.CountryRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    public Country createCountry(String country){
        Country country1 = Country.builder().nameCountry(country).build();
        return countryRepository.save(country1);
    }

    public Country updateCountry(String new_country, UUID countryId){
        Country countries = countryRepository.findById(countryId).orElseThrow(()->new DataNotFoundException("country not found by countryId"));
        countries.setNameCountry(new_country);
        countries.setUpdatedDate(LocalDateTime.now());
        return countryRepository.save(countries);
    }

    public void deleteCountryById(UUID id){
        countryRepository.deleteById(id);
    }

    public Country getCountryById(UUID id){
        return countryRepository.findCountriesById(id);
    }

    public City createCity(CityDto city){
        City mapped = modelMapper.map(city, City.class);
        return cityRepository.save(mapped);
    }

    public City updateCity(UUID uuid, CityDto cityDto){
        City city = cityRepository.findById(uuid).orElseThrow(() -> new DataNotFoundException("city not found by id"));
        if (cityDto.getName() != null && !cityDto.getName().isEmpty()) {
            city.setName(cityDto.getName());
        }
        if (cityDto.getCountry() != null) {
            city.setCountry(cityDto.getCountry());
        }
        if (cityDto.getHotel() != null && !cityDto.getHotel().isEmpty()) {
            city.setHotels(cityDto.getHotel());
        }
        city.setUpdatedDate(LocalDateTime.now());
        return cityRepository.save(city);

    }
}
