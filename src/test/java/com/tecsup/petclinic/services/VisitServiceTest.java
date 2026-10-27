package com.tecsup.petclinic.services;

import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testCalculateTotalCostByPet() {
        assertEquals(
                new BigDecimal("320.00"),

                visitService.calculateTotalCostByPet(7)
        );
    }

    @Test
    public void testCalculateTotalCostByPet_NoVisits() {
        assertEquals(
                BigDecimal.ZERO,

                visitService.calculateTotalCostByPet(100)
        );
    }

    @Test
    public void testFindVisitById_NotFound() {
        assertThrows(
                VisitNotFoundException.class,

                () -> visitService.findById(999)
        );
    }
}
