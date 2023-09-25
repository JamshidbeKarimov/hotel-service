package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.*;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.List;

@Entity(name = "language_spoken")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LanguageSpoken extends BaseEntity {
    private String language;

    @ManyToMany
    @JoinTable(
            name = "hotel_language",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<HotelEntity> hotel;
}
