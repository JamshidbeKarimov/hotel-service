package uz.pdp.hotelservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelservice.domain.entity.HotelEntity;
import uz.pdp.hotelservice.domain.entity.moreOptions.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {

    Optional<HotelEntity> findByName(String name);
    Page<HotelEntity> findHotelEntitiesByParkingAvailableAndAvailabilityAndPetFriendly(boolean parking, boolean available, boolean petFriendly, Pageable pageable);

    @Query("SELECT h from hotels h where h.priceRangeMin >= :minPrice and h.priceRangeMax <= :maxPrice")
    Page<HotelEntity> findHotelEntitiesByPriceRange(@Param("minPrice") double minPrice,@Param("maxPrice") double maxPrice, Pageable pageable);
    @Query("select h from hotels h where h.city.name= :cityName and h.city.country.nameCountry = :countryName")
    Page<HotelEntity> findHotelEntitiesByRegionCityAndCountry(@Param("cityName") String cityName, @Param("countryName") String countryName, Pageable pageable);
    @Query("select h from hotels h where h.city.name= :city")
    Page<HotelEntity> findByCityName(@Param("city") String city, Pageable pageable);
    @Query("select h from hotels h where h.city.country.nameCountry= :country")
    Page<HotelEntity> findByCountryName(@Param("country") String country, Pageable pageable);

    @Query("select h from hotels h join h.reviews r where h.id= :hotelId")
    Page<Review> findHotelEntitiesByReviews(@Param("hotelId") UUID hotelId, Pageable pageable);

    @Query("SELECT h FROM hotels h " +
            "JOIN h.city c " +
            "WHERE h.parkingAvailable = :parking " +
            "AND h.availability = :available " +
            "AND h.petFriendly = :petFriendly " +
            "AND h.priceRangeMin >= :minPrice " +
            "AND h.priceRangeMax <= :maxPrice " +
            "AND c.name = :cityName " +
            "AND c.country.nameCountry = :countryName")
    Page<HotelEntity> findHotelsWithFilters(
            @Param("parking") boolean parking,
            @Param("available") boolean available,
            @Param("petFriendly") boolean petFriendly,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("cityName") String cityName,
            @Param("countryName") String countryName,
            Pageable pageable);





}
