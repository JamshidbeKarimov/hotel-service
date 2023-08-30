package uz.pdp.hotelservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelservice.domain.dto.HotelDto;
import uz.pdp.hotelservice.domain.entity.HotelEntity;
import uz.pdp.hotelservice.service.HotelService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/hotel/api/v1")
@RequiredArgsConstructor
@RestController
public class HotelController {
    private final HotelService hotelService;

    //ToDo: getHotelsByAvailablesController
    //ToDo: getOneByName
    //ToDo: getHotelById
    //ToDo: getHotelsByAvailables
    //ToDo: getHotelsByPriceMinAndMax
    //ToDo: getHotelsByCityAndCountry
    //ToDo: getHotelsByCityName
    //ToDo: getHotelsByCountryName
    //ToDo: getReviewsByHotelsId

    @PostMapping("/add")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<HotelEntity> addHotel(
            @RequestBody HotelDto hotelDto
    ) {
        return ResponseEntity.ok(hotelService.save(hotelDto));
    }

    @GetMapping("/{id}/hotel")
    public ResponseEntity<HotelEntity> getOne(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PutMapping("/{id}/update")
    @PreAuthorize( value = "hasAnyRole('SUPER_ADMIN','MANAGER','ADMIN')")
    public ResponseEntity<HotelEntity> updateHotel(
            @PathVariable UUID id,
            @RequestBody HotelDto hotelDto
    ) {
        return ResponseEntity.ok(hotelService.updateHotel(hotelDto, id));
    }
    @GetMapping("/{deletedId}/delete")
    @PreAuthorize( value = "hasAnyRole('SUPER_ADMIN','MANAGER')")
    public ResponseEntity<String> deleteHotel(
            @PathVariable UUID deletedId
    ){
        hotelService.deleteById(deletedId);
        return ResponseEntity.ok("hotel delete");
    }
}
