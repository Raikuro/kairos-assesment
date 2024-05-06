package com.kairos.assessment.infrastructure.repository;

import com.kairos.assessment.domain.model.Price;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface H2JpaPriceRepository extends PriceRepository {
    Optional<Price> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByStartDateDesc(
            int productId, int brandId, OffsetDateTime startDate, OffsetDateTime endDate);
}
