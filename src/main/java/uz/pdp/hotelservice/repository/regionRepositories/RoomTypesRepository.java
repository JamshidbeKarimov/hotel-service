package uz.pdp.hotelservice.repository.regionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelservice.domain.entity.moreOptions.RoomType;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface RoomTypesRepository extends JpaRepository<RoomType, UUID> {

    Optional<RoomType> findRoomTypeById(UUID id);
    Optional<RoomType> findRoomTypeByTypeName(String name);
}
