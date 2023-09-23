package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.List;

@Entity(name = "room_amenities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomAmenity extends BaseEntity {
    private String amenity;

    @ManyToMany(mappedBy = "roomAmenities")
    private List<HotelEntity> hotelEntities;

}
