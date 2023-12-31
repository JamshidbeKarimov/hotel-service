package uz.pdp.hotelservice.repository.regionRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelservice.domain.entity.moreOptions.PaymentMethod;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {
    Optional<PaymentMethod> findPaymentMethodByName(String name);
}
