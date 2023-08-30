package uz.pdp.hotelservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.hotelservice.domain.entity.HotelEntity;
import uz.pdp.hotelservice.service.HotelService;

@RestController
@RequestMapping("hotel/api/v1/filter")
@RequiredArgsConstructor
public class HotelFilterController {

    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<Page<HotelEntity>> get(@RequestParam(defaultValue = "false") boolean parking,
                                                 @RequestParam(defaultValue = "false") boolean available,
                                                 @RequestParam(defaultValue = "false") boolean petFriendly,
                                                 @RequestParam(defaultValue = "0") double minPrice,
                                                 @RequestParam(defaultValue = "99999999") double maxPrice,
                                                 @RequestParam(defaultValue = "") String cityName,
                                                 @RequestParam(defaultValue = "") String countryName,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(defaultValue = "name") String sortField,
                                                 @RequestParam(defaultValue = "asc") Sort.Direction sortDirection
    ){
        return ResponseEntity.ok(hotelService.filter(parking,available,petFriendly,minPrice,maxPrice,cityName,countryName,page,size,sortField,sortDirection));
    }
}
