package uz.pdp.hotelservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelservice.domain.dto.HotelDto;
import uz.pdp.hotelservice.domain.entity.HotelEntity;
import uz.pdp.hotelservice.service.HotelService;

import java.util.UUID;

@RequestMapping("/hotel/api/v1")
@RequiredArgsConstructor
@RestController
public class HotelController {
    private final HotelService hotelService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public ResponseEntity<HotelEntity> addHotel(
            @RequestBody HotelDto hotelDto
    ) {
        return ResponseEntity.status(200).body(hotelService.save(hotelDto));
    }

    @GetMapping("/{id}/hotel")
    public ResponseEntity<HotelEntity> getOne(
            @PathVariable UUID id
    ) {
        try {
            return ResponseEntity.ok(hotelService.getHotelById(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/update")
    @PreAuthorize( value = "hasAnyRole('SUPER_ADMIN','MANAGER','ADMIN')")
    public ResponseEntity<HotelEntity> updateHotel(
            @PathVariable UUID id,
            @RequestBody HotelDto hotelDto
    ) {
        return ResponseEntity.ok(hotelService.updateHotel(hotelDto, id));
    }
    @DeleteMapping("/{deletedId}/delete")
    @PreAuthorize( value = "hasAnyRole('SUPER_ADMIN','MANAGER')")
    public ResponseEntity<String> deleteHotel(
            @PathVariable UUID deletedId
    ){
        hotelService.deleteById(deletedId);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/test")
    public ResponseEntity<String> check(){
        return ResponseEntity.ok("work!!!");
    }
}
