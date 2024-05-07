package com.kairos.assessment.infrastructure.api;

import com.kairos.assessment.application.PriceService;
import com.kairos.assessment.domain.model.Price;
import com.kairos.assessment.infrastructure.api.mapper.ApiMapperImpl;
import com.kairos.model.PriceResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PriceControllerTest {

    @InjectMocks
    private PriceController controller;

    @Mock
    private PriceService priceService;

    @Spy
    private ApiMapperImpl mapper;

    @Test
    public void getApplicablePrice_butNotFoundAnyPrice() throws NotFoundException {
        OffsetDateTime now = OffsetDateTime.now();

        when(priceService.findApplicablePrice(now, 0, 0)).thenThrow(new NotFoundException());

        assertThrows(NotFoundException.class,
                () -> controller.getApplicablePrice(now, 0, 0));
    }

    @Test
    public void getApplicablePrice() throws NotFoundException {
        OffsetDateTime now = OffsetDateTime.now();
        Price price = new Price();
        price.setBrandId(1);
        price.setStartDate(now);
        price.setEndDate(now);
        price.setPriceList(1);
        price.setProductId(1);
        price.setPriority(0);
        price.setPrice(BigDecimal.ONE);
        price.setCurrency("EUR");

        when(priceService.findApplicablePrice(now, 0, 0)).thenReturn(price);
        when(mapper.toResponse(any())).thenCallRealMethod();

        ResponseEntity<PriceResponse> response = controller.getApplicablePrice(now, 0, 0);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        PriceResponse priceResponse = response.getBody();
        assertEquals(price.getProductId(), priceResponse.getProductId());
        assertEquals(price.getBrandId(), priceResponse.getBrandId());
        assertEquals(price.getPriceList(), priceResponse.getPriceList());
        assertEquals(price.getStartDate(), priceResponse.getStartDate());
        assertEquals(price.getEndDate(), priceResponse.getEndDate());
        assertEquals(price.getPrice().floatValue(), priceResponse.getPrice());
        assertEquals(price.getCurrency(), priceResponse.getCurrency());
    }

}