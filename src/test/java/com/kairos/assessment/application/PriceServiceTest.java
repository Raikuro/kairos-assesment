package com.kairos.assessment.application;

import com.kairos.assessment.domain.model.Price;
import com.kairos.assessment.infrastructure.model.PriceDbo;
import com.kairos.assessment.infrastructure.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
class PriceServiceTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @Test
    public void findApplicablePrice_butNotFoundAnyPrice() {
        when(priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyInt(), anyInt(), any(), any()))
                .thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class,
                () -> priceService.findApplicablePrice(OffsetDateTime.now(), 0, 0));
    }

    @Test
    public void findApplicablePrice() throws NotFoundException {
        Price expected = new Price();
        expected.setBrandId(1);
        expected.setStartDate(OffsetDateTime.now());
        expected.setEndDate(OffsetDateTime.now());
        expected.setPriceList(1);
        expected.setProductId(1);
        expected.setPriority(0);
        expected.setPrice(BigDecimal.ONE);
        expected.setCurrency("EUR");

        when(priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyInt(), anyInt(), any(), any()))
                .thenReturn(Collections.singletonList(expected));

        Price result = priceService.findApplicablePrice(OffsetDateTime.now(), 0, 0);

        assertEquals(expected, result);
    }

}
