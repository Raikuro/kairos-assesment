package com.kairos.assessment.infrastructure.repository;

import com.kairos.assessment.infrastructure.model.PriceDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface H2JpaPriceRepository extends JpaRepository<PriceDbo, Long> {
    List<PriceDbo> findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            int productId, int brandId, OffsetDateTime startDate, OffsetDateTime endDate);
}
