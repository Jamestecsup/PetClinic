package com.tecsup.petclinic.services;

import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    /**
     * Test para calcular el costo total por mascota usando BigDecimal
     * Debe sumar correctamente todos los costos de las visitas de un pet
     */
    @Test
    public void testCalculateTotalCostByPet() {
        // Pet 7 tiene dos visitas en los datos de prueba: 45.00 + 275.00.
        Integer PET_ID = 7;
        BigDecimal TOTAL_COST_EXPECTED = new BigDecimal("320.00");

        BigDecimal totalCost = this.visitService.calculateTotalCostByPet(PET_ID);

        assertNotNull(totalCost);
        assertEquals(0, TOTAL_COST_EXPECTED.compareTo(totalCost),
                "El costo total debe ser la suma de las visitas de la mascota");

        log.info("COSTO TOTAL PARA EL PET {} : {}", PET_ID, totalCost);
    }

    /**
     * Test para calcular costo total cuando no hay visitas
     * Debe devolver BigDecimal.ZERO si el pet no tiene visitas
     */
    @Test
    public void testCalculateTotalCostByPet_NoVisits() {
        // Pet 100 no tiene visitas en los datos de prueba.
        Integer PET_ID = 100;

        // When: calcular el costo total
        BigDecimal totalCost = this.visitService.calculateTotalCostByPet(PET_ID);

        assertNotNull(totalCost);
        assertEquals(BigDecimal.ZERO, totalCost,
                "El costo total debe ser ZERO cuando no hay visitas");

        log.info("COSTO TOTAL SIN VISITAS para el PET {} : {}", PET_ID, totalCost);
    }

    /**
     * Test para buscar una visita por ID que no existe
     * Debe lanzar VisitNotFoundException
     */
    @Test
    public void testFindVisitById_NotFound() {
        Integer VISIT_ID = 999;

        VisitNotFoundException exception = assertThrows(
                VisitNotFoundException.class,
                () -> this.visitService.findById(VISIT_ID),
                "Debe lanzar VisitNotFoundException cuando la visita no existe"
        );

        log.info("EXCEPCIÓN LANZADA CORRECTAMENTE: {}", exception.getMessage());
    }
}
