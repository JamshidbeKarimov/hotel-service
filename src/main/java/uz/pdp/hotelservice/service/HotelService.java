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
import uz.pdp.hotelservice.domain.entity.moreOptions.*;
import uz.pdp.hotelservice.exeption.DataNotFoundException;
import uz.pdp.hotelservice.repository.HotelRepository;
import uz.pdp.hotelservice.repository.regionRepositories.LanguageSpokenRepository;
import uz.pdp.hotelservice.repository.regionRepositories.PaymentMethodRepository;
import uz.pdp.hotelservice.repository.regionRepositories.RoomAmenityRepository;
import uz.pdp.hotelservice.repository.regionRepositories.RoomTypesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final RoomTypesRepository roomTypesRepository;
    private final RoomAmenityRepository roomAmenityRepository;
    private final LanguageSpokenRepository languageSpokenRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    @Transactional
    public HotelEntity save(HotelDto hotelDto){
       HotelEntity map = modelMapper.map(hotelDto, HotelEntity.class);
        map.setPaymentOptions(checkPaymentMethodAndCreatePayment(hotelDto.getPaymentOptions()));
        map.setLanguageSpokens(checkAndCreateLanguage(hotelDto.getLanguageSpokens()));
        map.setRoomTypes(checkRoomTypeAndCreateType(hotelDto.getRoomTypes()));
        map.setRoomAmenities(checkRoomAmenitiesAndSaCreateAmenity(hotelDto.getRoomAmenities()));
       return hotelRepository.save(map);
    }


    public HotelEntity getHotelById(UUID id){
        return hotelRepository.findById(id).orElseThrow(()-> new DataNotFoundException("hotel not found"));
    }

    public HotelEntity getOneByName(String name){
        return hotelRepository.findByName(name).orElseThrow(
                ()-> new DataNotFoundException("hotel not found")
        );
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
        if (newHotel.getLocationOfGoogleMap() == null)
            newHotel.setLocationOfGoogleMap(hotel.getLocationOfGoogleMap());
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

    private static <T> List<T> convertFromStrToClass(List<String> list, Class<T> clazz) {
        List<T> convertedList = new ArrayList<>();

        try {
            for (String str : list) {
                T obj = clazz.getDeclaredConstructor(String.class).newInstance(str);
                convertedList.add(obj);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return convertedList;
    }

    private List<RoomType> roomTypesToEntity(List<RoomType> roomList){
        List<RoomType> roomTypes = new ArrayList<>();
        for (RoomType type: roomList){
            if(roomTypesRepository.findRoomTypeByTypeName(type.getTypeName()).isEmpty()){
              roomTypes.add(roomTypesRepository.save(type));
            }else{
                roomTypes.add(roomTypesRepository.findRoomTypeByTypeName(type.getTypeName()).orElseThrow(()-> new DataNotFoundException("Not found")));
            }
            }
        return roomTypes;
    }

    private List<LanguageSpoken> checkAndCreateLanguage(List<LanguageSpoken> languages) {
        List<LanguageSpoken> languageSpokenList = new ArrayList<>();
        for (LanguageSpoken language : languages) {
            LanguageSpoken existingLanguage = languageSpokenRepository.findLanguageSpokenByLanguage(language.getLanguage());
            if (existingLanguage == null) {
                LanguageSpoken savedLanguage = languageSpokenRepository.save(language);
                languageSpokenList.add(savedLanguage);
            } else {
                languageSpokenList.add(existingLanguage);
            }
        }
        return languageSpokenList;
    }

    private List<RoomType> checkRoomTypeAndCreateType(List<RoomType> roomTypes){
        List<RoomType> roomTypeList = new ArrayList<>();
        for (RoomType type : roomTypes) {
            Optional<RoomType> found = roomTypesRepository.findRoomTypeByTypeName(type.getTypeName());
            if (found.isEmpty()) {
                RoomType rt = roomTypesRepository.save(type);
                roomTypeList.add(rt);
            } else {
                roomTypeList.add(found.orElseThrow(()-> new DataNotFoundException("RoomType not found")));
            }
        }
        return roomTypeList;
    }

    private List<RoomAmenity> checkRoomAmenitiesAndSaCreateAmenity(List<RoomAmenity> roomAmenities) {
        List<RoomAmenity> roomAmenitiesList = new ArrayList<>();
        for (RoomAmenity amenity : roomAmenities) {
            Optional<RoomAmenity> found = roomAmenityRepository.findRoomAmenitiesByAmenity(amenity.getAmenity());
            if (found.isEmpty()) {
                RoomAmenity rm = roomAmenityRepository.save(amenity);
                roomAmenitiesList.add(rm);
            } else {
                roomAmenitiesList.add(found.orElseThrow(()-> new DataNotFoundException("Amenity not found")));
            }
        }
        return roomAmenitiesList;
    }


    private List<PaymentMethod> checkPaymentMethodAndCreatePayment(List<String> paymentOptions) {
        List<PaymentMethod> paymentMethodsList = new ArrayList<>();
        for (PaymentMethod amenity : convertFromStrToClass(paymentOptions, PaymentMethod.class)) {
            Optional<PaymentMethod> found = paymentMethodRepository.findPaymentMethodByName(amenity.getName());
            if (found.isEmpty()) {
                PaymentMethod rm = paymentMethodRepository.save(amenity);
                paymentMethodsList.add(rm);
            } else {
                paymentMethodsList.add(found.orElseThrow(()-> new DataNotFoundException("Payment_method not found")));
            }
        }
        return paymentMethodsList;
    }
}
