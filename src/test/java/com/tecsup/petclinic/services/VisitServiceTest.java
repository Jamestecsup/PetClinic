package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.PetDTO;
import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

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
        // Given: un ID de pet con varias visitas con costos
        Integer PET_ID = 1;

        // Expecting: que el total calculado sea la suma de todos los costos
        BigDecimal totalCost = this.visitService.calculateTotalCostByPet(PET_ID);

        // Then: verificar que el resultado sea correcto
        assertNotNull(totalCost);
        assertTrue(totalCost.compareTo(BigDecimal.ZERO) >= 0,
                "El costo total no debe ser negativo");

        log.info("COSTO TOTAL PARA EL PET {} : {}", PET_ID, totalCost);
    }

    /**
     * Test para calcular costo total cuando no hay visitas
     * Debe devolver BigDecimal.ZERO si el pet no tiene visitas
     */
    @Test
    public void testCalculateTotalCostByPet_NoVisits() {
        // Given: un ID de pet que no tiene visitas (por ejemplo, pet con ID 100)
        Integer PET_ID = 100;

        // When: calcular el costo total
        BigDecimal totalCost = this.visitService.calculateTotalCostByPet(PET_ID);

        // Then: debe devolver ZERO
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
        // Given: un ID de visita que no existe
        Integer VISIT_ID = 999;

        // When & Then: debe lanzar VisitNotFoundException
        VisitNotFoundException exception = assertThrows(
                VisitNotFoundException.class,
                () -> this.visitService.findById(VISIT_ID),
                "Debe lanzar VisitNotFoundException cuando la visita no existe"
        );

        log.info("EXCEPCIÓN LANZADA CORRECTAMENTE: {}", exception.getMessage());
    }
}