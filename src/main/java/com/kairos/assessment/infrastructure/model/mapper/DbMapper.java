package com.kairos.assessment.infrastructure.model.mapper;

import com.kairos.assessment.domain.model.Price;
import com.kairos.assessment.infrastructure.model.PriceDbo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DbMapper {
    Price toModel(PriceDbo product);
}
