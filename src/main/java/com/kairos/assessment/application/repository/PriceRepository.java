package com.kairos.assessment.application.repository;

import com.kairos.assessment.domain.model.Price;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            int productId, int brandId, OffsetDateTime startDate, OffsetDateTime endDate);
}
