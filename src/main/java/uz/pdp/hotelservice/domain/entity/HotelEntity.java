package uz.pdp.hotelservice.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.hotelservice.domain.entity.moreOptions.*;
import uz.pdp.hotelservice.domain.entity.region.City;

import java.util.List;
import java.util.UUID;

@Entity(name = "hotels")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HotelEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    private String locationOfGoogleMap;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany
    @JoinTable(
            name = "hotel_room_type",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "room_type_id")
    )
    private List<RoomType> roomTypes;

    @ManyToMany
    @JoinTable(
            name = "room_amenity",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "room_amenties_id")
    )
    private List<RoomAmenity> roomAmenities;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelFilesEntity> photos;

    private String description;
    private double priceRangeMin;
    private double priceRangeMax;

    @Column(nullable = false)
    private boolean availability=false;

    private String cancellationPolicy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private List<Review> reviews;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id")
    private ContactInfo contactInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "map_location_id")
    private MapLocation mapLocation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private List<PaymentMethod> paymentOptions;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private List<SpecialOffer> specialOffers;

    @ManyToMany(mappedBy = "hotel")
    private List<LanguageSpoken> languageSpokens;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hotel_events_conferences",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "event_conference_id")
    )
    private List<EventsAndConferencesEntity> eventsAndConferences;

    private boolean petFriendly=false;
    private boolean parkingAvailable=false;

    @JsonIgnore
    private UUID managerOfHotel;
}
