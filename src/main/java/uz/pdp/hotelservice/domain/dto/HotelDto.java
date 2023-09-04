package uz.pdp.hotelservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.hotelservice.domain.entity.moreOptions.*;
import uz.pdp.hotelservice.domain.entity.region.City;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HotelDto {
    private String name;
    private String locationOfGoogleMap;
    private City city;
    private List<RoomType> roomTypes;
    private List<String> roomAmenities;
    private List<MultipartFile > photos;

    private String description;
    private double priceRangeMin;
    private double priceRangeMax;
    private boolean availability;

    private String cancellationPolicy;

    private List<Review> reviews;

    private ContactInfo contactInfo;


    private MapLocation mapLocation;


    private List<PaymentMethod> paymentOptions;


    private List<SpecialOffer> specialOffers;

    private List<LanguageSpoken> languageSpokens;
    private List<EventsAndConferencesEntity> eventsAndConferences;
    private boolean petFriendly=false;
    private boolean parkingAvailable=false;

    private UUID managerOfHotel;
}
