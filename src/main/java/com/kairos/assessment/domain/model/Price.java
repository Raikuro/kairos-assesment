package com.kairos.assessment.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class Price {
    private int brandId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private int priceList;
    private int productId;
    private int priority;
    private BigDecimal price;
    private String currency;
}
