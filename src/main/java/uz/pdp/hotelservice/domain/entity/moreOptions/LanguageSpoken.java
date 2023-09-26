package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.*;
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
    @Column(unique = true, nullable = false)
    private String language;

    @ManyToMany
    @JoinTable(
            name = "hotel_language",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<HotelEntity> hotel;
}
