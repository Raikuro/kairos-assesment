package com.kairos.assessment.application;

import com.kairos.assessment.domain.model.Price;
import com.kairos.assessment.application.repository.PriceRepository;
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
import static org.mockito.Mockito.when;


@SpringBootTest
class PriceServiceTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @Test
    public void findApplicablePrice_butNotFoundAnyPrice() {
        OffsetDateTime now = OffsetDateTime.now();
        when(priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(0, 0, now, now))
                .thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class,
                () -> priceService.findApplicablePrice(now, 0, 0));
    }

    @Test
    public void findApplicablePrice() throws NotFoundException {
        OffsetDateTime now = OffsetDateTime.now();
        Price expected = new Price();
        expected.setBrandId(1);
        expected.setStartDate(now);
        expected.setEndDate(now);
        expected.setPriceList(1);
        expected.setProductId(1);
        expected.setPriority(0);
        expected.setPrice(BigDecimal.ONE);
        expected.setCurrency("EUR");

        when(priceRepository.findAllByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(0,0, now, now))
                .thenReturn(Collections.singletonList(expected));

        Price result = priceService.findApplicablePrice(now, 0, 0);

        assertEquals(expected, result);
    }

}
