package uz.pdp.hotelservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelservice.domain.entity.region.Country;
import uz.pdp.hotelservice.repository.regionRepositories.CountryRepository;
import uz.pdp.hotelservice.service.RegionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel/api/v1")
public class OptionsController {
    private final RegionService regionService;
    @PostMapping("/add/region/coutry")
    public ResponseEntity<Country> addCountry(
            @RequestParam String country
    ){
        return ResponseEntity.ok(regionService.createCountry(country));
    }
}
