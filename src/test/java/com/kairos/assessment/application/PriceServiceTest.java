package com.kairos.assessment.application;

import com.kairos.assessment.domain.model.Price;
import com.kairos.assessment.infrastructure.repository.H2JpaPriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Optional;

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
    private H2JpaPriceRepository priceRepository;

    @Test
    public void findApplicablePriceNotFoundAnyPrice() throws NotFoundException {
        when(priceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByStartDateDesc(anyInt(), anyInt(), any(), any()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,
                () -> priceService.findApplicablePrice(OffsetDateTime.now(), 0, 0));
    }

    @Test
    public void findApplicablePrice() throws NotFoundException {
        Price expected = new Price();
        expected.setId(1L);
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
        Price price = priceService.findApplicablePrice(OffsetDateTime.now(), 0, 0);
        assertEquals(expected, price);
    }

}
