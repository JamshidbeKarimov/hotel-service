package uz.pdp.hotelservice.domain.entity.region;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.List;

@Entity(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<HotelEntity> hotels;

}
