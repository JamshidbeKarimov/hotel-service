package uz.pdp.hotelservice.repository.regionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelservice.domain.entity.region.Country;

import java.util.UUID;
@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

}
