package com.kairos.assessment.infrastructure.repository;

import com.kairos.assessment.application.repository.PriceRepository;
import com.kairos.assessment.domain.model.Price;
import com.kairos.assessment.infrastructure.model.mapper.DbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    @Autowired
    private DbMapper mapper;

    @Autowired
    private H2JpaPriceRepository repository;
    @Override
    public List<Price> findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            int productId, int brandId, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository
                .findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, startDate, endDate)
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }
}
