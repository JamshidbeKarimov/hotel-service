package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
@Entity(name = "room_types")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomType extends BaseEntity {
    private String typeName, description;
}
