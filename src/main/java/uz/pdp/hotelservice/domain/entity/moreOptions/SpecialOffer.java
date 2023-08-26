package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;

@Entity(name="special_offer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecialOffer extends BaseEntity {
    private String offer;
}
