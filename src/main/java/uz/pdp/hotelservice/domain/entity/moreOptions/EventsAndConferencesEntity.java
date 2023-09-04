package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.List;

@Entity(name = "event_conferences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventsAndConferencesEntity extends BaseEntity {

    private String eventAndConference;

    @ManyToMany(mappedBy = "eventsAndConferences")
    private List<HotelEntity> hotels;
}
