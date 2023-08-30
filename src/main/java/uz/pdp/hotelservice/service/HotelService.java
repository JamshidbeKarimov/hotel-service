package uz.pdp.hotelservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.hotelservice.domain.dto.HotelDto;
import uz.pdp.hotelservice.domain.entity.HotelEntity;
import uz.pdp.hotelservice.domain.entity.moreOptions.Review;
import uz.pdp.hotelservice.exeption.DataNotFoundException;
import uz.pdp.hotelservice.exeption.DuplicateDataException;
import uz.pdp.hotelservice.repository.HotelRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    @Transactional
    public HotelEntity save(HotelDto hotelDto){
       HotelEntity map = modelMapper.map(hotelDto, HotelEntity.class);
       map.setLocation(hotelDto.getLocation());
       return hotelRepository.save(map);
    }

    public HotelEntity getHotelById(UUID id){
        return hotelRepository.findById(id).orElseThrow(()-> new DataNotFoundException("hotel not found"));
    }

    public HotelEntity getOneByName(String name){
        HotelEntity hotel = hotelRepository.findByName(name).orElseThrow(
                ()-> new DataNotFoundException("hotel not found")
        );
        return hotel;
    }

    public void deleteById(UUID hotelId){
        try{
        HotelEntity hotel = getHotelById(hotelId);
        hotelRepository.delete(hotel);
        }catch (EntityNotFoundException e){
            throw new DataNotFoundException("Hotel with ID " + hotelId + " not found");
        }
    }

    public HotelEntity updateHotel(HotelDto update, UUID hotelId){
        HotelEntity hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new DataNotFoundException("Hotel not found"));
        HotelEntity newHotel = modelMapper.map(update,HotelEntity.class);
        newHotel.setId(hotel.getId());
        if (newHotel.getName().isEmpty())
            newHotel.setName(hotel.getName());
        if (newHotel.getLocation() == null)
            newHotel.setLocation(hotel.getLocation());
        modelMapper.map(newHotel,HotelEntity.class);
       return hotelRepository.save(newHotel);
    }

    public Page<HotelEntity> getHotelsByAvailables(boolean parking, boolean available, boolean petFriendly, int page, int size,String sortField, Sort.Direction sortDirection){
        return hotelRepository.findHotelEntitiesByParkingAvailableAndAvailabilityAndPetFriendly(parking,available,petFriendly,
                pageableInShort(page,size,sortField, sortDirection));
    }

    public Page<HotelEntity> getHotelsByPriceMinAndMax(double minPrice, double maxPrice, int page, int size, String sortField, Sort.Direction sortDirection){
        return hotelRepository.findHotelEntitiesByPriceRange(minPrice, maxPrice, pageableInShort(page,size,sortField, sortDirection));
    }

    public Page<HotelEntity> getHotelsByCityAndCountry(String cityName, String countryName, int page, int size, String sortField, Sort.Direction sortDirection){
        return hotelRepository.findHotelEntitiesByRegionCityAndCountry(cityName,countryName,pageableInShort(page,size,sortField, sortDirection));
    }

    public Page<HotelEntity> getHotelsByCityName(String cityName, int page, int size, String sortField, Sort.Direction sortDirection){
        return hotelRepository.findByCityName(cityName, pageableInShort(page,size,sortField, sortDirection));
    }

    public Page<HotelEntity> getHotelsByCountryName(String countryName, int page, int size, String sortField, Sort.Direction sortDirection){
        return hotelRepository.findByCountryName(countryName,pageableInShort(page,size,sortField, sortDirection));
    }

    public Page<Review> getReviewsByHotelsId(UUID hotelId, int page, int size, String sortField, Sort.Direction sortDirection){
        return hotelRepository.findHotelEntitiesByReviews(hotelId,pageableInShort(page,size,sortField, sortDirection));
    }

    public Page<HotelEntity> filter(boolean parking, boolean available, boolean petFriendly, double minPrice, double maxPrice, String cityName, String countryName, int page, int size,String sortField, Sort.Direction sortDirection){
        return hotelRepository.findHotelsWithFilters(parking,available,petFriendly,minPrice,maxPrice,cityName,countryName,pageableInShort(page, size,sortField,sortDirection));
    }
    private Pageable pageableInShort(int page, int size, String sortField, Sort.Direction sortDirection){
        Sort sort = Sort.by(sortDirection, sortField);
        return PageRequest.of(page, size, sort);
    }


}
