package uz.pdp.hotelservice.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.hotelservice.domain.entity.region.Country;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HotelDto {
    private String name;
    private String location;
    private Country country;
    private List<String> roomTypes;
    private List<String> roomAmenities;
    private List<String> photos;
    private String description;
    private double priceRangeMin;
    private double priceRangeMax;
    private boolean availability;
    private String cancellationPolicy;
}
