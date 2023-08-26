package uz.pdp.hotelservice.repository.regionRepositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelservice.domain.entity.moreOptions.Review;

import java.util.UUID;
@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
