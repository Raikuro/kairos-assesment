package com.kairos.assessment.infrastructure.api.mapper;

import com.kairos.assessment.domain.model.Price;
import com.kairos.model.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiMapper {
    PriceResponse toResponse(Price product);
}
