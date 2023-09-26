package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;

@Entity(name = "map_location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MapLocation extends BaseEntity{
    private double latitude;
    private double longitude;

    //TODO connection
}
