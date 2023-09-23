package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.List;

@Entity(name = "room_types")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomType extends BaseEntity {
    private String typeName, description;

    @ManyToMany(mappedBy = "roomTypes")
    private List<HotelEntity> hotel;
}
