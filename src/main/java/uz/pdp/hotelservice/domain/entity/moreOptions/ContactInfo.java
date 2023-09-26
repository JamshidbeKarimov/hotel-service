package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
@Entity(name = "contact_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactInfo extends BaseEntity {
    private String phoneNumber=null, email=null, instagram=null, facebook=null, youtube=null, twitter=null, telegram=null;
    //TODO connection
}
