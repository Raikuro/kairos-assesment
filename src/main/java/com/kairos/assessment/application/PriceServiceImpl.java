package com.kairos.assessment.application;

import com.kairos.assessment.domain.model.Price;
import com.kairos.assessment.application.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

import static java.util.Comparator.comparing;

@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    private PriceRepository priceRepository;

    public Price findApplicablePrice(OffsetDateTime applicationDate, int productId, int brandId) throws NotFoundException {
        return priceRepository
                .findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, applicationDate, applicationDate)
                .stream()
                .max(comparing(Price::getStartDate))
                .orElseThrow(NotFoundException::new);
    }
}
