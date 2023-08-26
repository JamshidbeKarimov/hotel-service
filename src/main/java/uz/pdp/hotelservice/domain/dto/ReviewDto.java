package uz.pdp.hotelservice.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReviewDto {
    private String username;
    private int rating;
    private HotelEntity hotel;
    private String comment;
}
