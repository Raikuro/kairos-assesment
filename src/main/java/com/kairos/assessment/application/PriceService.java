package com.kairos.assessment.application;

import com.kairos.assessment.domain.model.Price;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public interface PriceService {

    Price findApplicablePrice(OffsetDateTime applicationDate, int productId, int brandId)  throws NotFoundException;
}
