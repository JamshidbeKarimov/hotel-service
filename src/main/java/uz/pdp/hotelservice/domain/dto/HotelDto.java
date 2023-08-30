package uz.pdp.hotelservice.domain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.hotelservice.domain.entity.moreOptions.*;
import uz.pdp.hotelservice.domain.entity.region.City;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HotelDto {
    private String name;
    private String location;
    private City city;
    private List<RoomType> roomTypes;
    private List<String> roomAmenities;
    private List<MultipartFile> photos;
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
    private List<LanguageSpoken> languageSpokes;
    private boolean petFriendly;
    private boolean parkingAvailable;
}
