package com.qindel.test.repository;

import com.qindel.test.entities.Price;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    // Get the Prices filtered by productId, brandId and the date, ordered by priority
    @Query(value = """
            SELECT p FROM Price p
            WHERE p.product.id = :productId
            AND p.brand.id = :brandId
            AND :appDate BETWEEN p.startDate AND p.endDate
            ORDER BY p.priority DESC
            """)
    List<Price> findByProductAndBrandAndDate(@Param("productId") Long productId,
                                             @Param("brandId") Long brandId,
                                             @Param("appDate") LocalDateTime appDate,
                                             Pageable pageable);

    // Get the top priority Price filtered by product, brand and date (the use of pagination is similar to "LIMIT 1")
    default Optional<Price> findTopPriorityPrice(Long productId, Long brandId, LocalDateTime appDate) {
        return findByProductAndBrandAndDate(productId, brandId, appDate, PageRequest.of(0,1)).stream().findFirst();
    }

}
