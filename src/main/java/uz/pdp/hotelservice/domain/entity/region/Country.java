package uz.pdp.hotelservice.domain.entity.region;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.List;

@Entity(name = "countries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Country extends BaseEntity {
    @Column(nullable = true, unique = true)
    private String nameCountry;

    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
    private List<City> cities;
}
