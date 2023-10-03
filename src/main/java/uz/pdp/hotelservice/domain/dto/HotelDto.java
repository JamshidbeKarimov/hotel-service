package uz.pdp.hotelservice.domain.dto;


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
    private List<RoomAmenity> roomAmenities;
    private List<MultipartFile> photos; // TODO not work

    private String description;
    private double priceRangeMin;
    private double priceRangeMax;
    private boolean availability;
    private boolean cancellationPolicy;

    private List<Review> reviews; // TODO not work

    private ContactInfo contactInfo;

    private MapLocation mapLocation;

    private List<String> paymentOptions;

    private List<SpecialOffer> specialOffers;

    private List<LanguageSpoken> languageSpokens; // TODO not work

    private List<EventsAndConferencesEntity> eventsAndConferences;

    private boolean petFriendly=false;

    private boolean parkingAvailable=false;

    private UUID managerOfHotel;
}
