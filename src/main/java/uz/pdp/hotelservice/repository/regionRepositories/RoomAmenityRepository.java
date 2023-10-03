package uz.pdp.hotelservice.repository.regionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelservice.domain.entity.moreOptions.RoomAmenity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomAmenityRepository extends JpaRepository<RoomAmenity,UUID> {
    Optional<RoomAmenity> findRoomAmenitiesByAmenity(String amenity);
}
