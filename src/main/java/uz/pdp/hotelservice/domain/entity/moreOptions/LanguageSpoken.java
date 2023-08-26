package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
@Entity(name = "language_spoken")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LanguageSpoken extends BaseEntity {
    private String language;
}
