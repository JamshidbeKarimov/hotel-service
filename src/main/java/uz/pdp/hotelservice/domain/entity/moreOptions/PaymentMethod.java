package uz.pdp.hotelservice.domain.entity.moreOptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.List;

@Entity(name = "payment_method")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentMethod extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "paymentOptions")
    private List<HotelEntity> hotel;
}
