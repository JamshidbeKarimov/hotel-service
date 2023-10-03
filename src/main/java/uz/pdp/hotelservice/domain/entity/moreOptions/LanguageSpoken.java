package uz.pdp.hotelservice.domain.entity.moreOptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToMany(mappedBy = "languageSpokens")
    private List<HotelEntity> hotel;
}
