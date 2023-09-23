package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.List;

@Entity(name = "event_conferences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventsAndConferencesEntity extends BaseEntity {

    private String eventAndConference;

    @ManyToMany(mappedBy = "eventsAndConferences")
    private List<HotelEntity> hotels;
}
