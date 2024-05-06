package com.kairos.assessment.infrastructure.mapper;

import com.kairos.assessment.domain.model.Price;
import com.kairos.model.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceResponse toResponse(Price product);
}
