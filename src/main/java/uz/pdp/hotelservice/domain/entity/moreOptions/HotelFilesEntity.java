package uz.pdp.hotelservice.domain.entity.moreOptions;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.hotelservice.domain.entity.BaseEntity;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

@Entity(name = "files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelFilesEntity extends BaseEntity {
    private String fileName;
    private String downloadUrl;
    private String fileType;
    private long size;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
}
