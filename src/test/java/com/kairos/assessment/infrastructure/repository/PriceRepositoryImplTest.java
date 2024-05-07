package com.kairos.assessment.infrastructure.repository;

import com.kairos.assessment.domain.model.Price;
import com.kairos.assessment.infrastructure.model.PriceDbo;
import com.kairos.assessment.infrastructure.model.mapper.DbMapperImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PriceRepositoryImplTest {

    @InjectMocks
    private PriceRepositoryImpl repository;

    @Mock
    private H2JpaPriceRepository h2JpaPriceRepository;

    @Spy
    private DbMapperImpl mapper;

    @Test
    public void findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(){
        OffsetDateTime now = OffsetDateTime.now();
        PriceDbo priceDbo = new PriceDbo();
        priceDbo.setBrandId(1);
        priceDbo.setStartDate(now);
        priceDbo.setEndDate(now);
        priceDbo.setPriceList(1);
        priceDbo.setProductId(1);
        priceDbo.setPriority(0);
        priceDbo.setPrice(BigDecimal.ONE);
        priceDbo.setCurrency("EUR");

        when(h2JpaPriceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 1, now, now))
                .thenReturn(Collections.singletonList(priceDbo));
        List<Price> prices = repository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 1, now, now);

        assertEquals(1, prices.size());
        Price result = prices.get(0);
        assertEquals(priceDbo.getBrandId(), result.getBrandId());
        assertEquals(priceDbo.getStartDate(), result.getStartDate());
        assertEquals(priceDbo.getEndDate(), result.getEndDate());
        assertEquals(priceDbo.getPriceList(), result.getPriceList());
        assertEquals(priceDbo.getProductId(), result.getProductId());
        assertEquals(priceDbo.getPriority(), result.getPriority());
        assertEquals(priceDbo.getPrice(), result.getPrice());
        assertEquals(priceDbo.getCurrency(), result.getCurrency());
    }

}