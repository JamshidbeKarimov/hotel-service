package uz.pdp.hotelservice.domain.dto.region;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.HotelEntity;
import uz.pdp.hotelservice.domain.entity.region.Country;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class CityDto {
    private String name;
    private Country country;
    private List<HotelEntity> hotel;
}
