package uz.pdp.hotelservice.domain.entity.region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;

@Entity(name = "countries")
@Getter
@Setter
public class Country extends BaseEntity {
    @Column(nullable = false)
    private String name;
}
