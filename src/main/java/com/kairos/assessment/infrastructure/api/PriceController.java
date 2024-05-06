package com.kairos.assessment.infrastructure.api;

import com.kairos.assessment.domain.model.Price;
import com.kairos.assessment.application.PriceService;
import com.kairos.assessment.infrastructure.mapper.PriceMapper;
import com.kairos.controller.PricesApi;
import com.kairos.model.PriceResponse;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@Log4j2
@RestController
public class PriceController implements PricesApi {

    @Autowired
    private PriceService priceService;

    @Autowired
    private PriceMapper mapper;

    @SneakyThrows
    @Override
    public ResponseEntity<PriceResponse> getApplicablePrice(OffsetDateTime date, Integer productId, Integer brandId) {
        log.info("date: {} productId: {} brandId {}", date, productId, brandId);
        Price applicablePrice = priceService.findApplicablePrice(date, productId, brandId);

        return new ResponseEntity<>(mapper.toResponse(applicablePrice), HttpStatus.OK);
    }
}
