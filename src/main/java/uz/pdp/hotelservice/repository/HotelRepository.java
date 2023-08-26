package uz.pdp.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {
    public Optional<HotelEntity> findByName(String name);
}
