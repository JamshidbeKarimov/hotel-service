package uz.pdp.hotelservice.domain.entity.region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
@Entity(name = "cities")
public class City extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
