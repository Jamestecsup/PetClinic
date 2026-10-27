package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testCreateVisit() {
        VisitDTO visitDTO = VisitDTO.builder()
                .petId(1)
                .vetId(1)
                .visitDate(LocalDate.of(2025, 1, 15))
                .description("annual checkup")
                .cost(new BigDecimal("65.00"))
                .build();

        VisitDTO newVisitDTO = this.visitService.create(visitDTO);

        log.info("VISIT CREATED : {}", newVisitDTO);

        assertNotNull(newVisitDTO.getId());
        assertEquals(visitDTO.getPetId(), newVisitDTO.getPetId());
        assertEquals(visitDTO.getVetId(), newVisitDTO.getVetId());
        assertEquals(visitDTO.getVisitDate(), newVisitDTO.getVisitDate());
        assertEquals(visitDTO.getDescription(), newVisitDTO.getDescription());
        assertEquals(visitDTO.getCost(), newVisitDTO.getCost());
    }

    @Test
    public void testUpdateVisit() {
        VisitDTO visitDTO = VisitDTO.builder()
                .petId(1)
                .vetId(1)
                .visitDate(LocalDate.of(2025, 2, 10))
                .description("initial checkup")
                .cost(new BigDecimal("40.00"))
                .build();
        VisitDTO createdVisitDTO = this.visitService.create(visitDTO);

        createdVisitDTO.setVisitDate(LocalDate.of(2025, 2, 11));
        createdVisitDTO.setDescription("follow-up checkup");
        createdVisitDTO.setCost(new BigDecimal("55.00"));

        VisitDTO updatedVisitDTO = this.visitService.update(createdVisitDTO);

        assertEquals(createdVisitDTO.getId(), updatedVisitDTO.getId());
        assertEquals(createdVisitDTO.getVisitDate(), updatedVisitDTO.getVisitDate());
        assertEquals(createdVisitDTO.getDescription(), updatedVisitDTO.getDescription());
        assertEquals(createdVisitDTO.getCost(), updatedVisitDTO.getCost());

        try {
            VisitDTO visitFound = this.visitService.findById(updatedVisitDTO.getId());
            assertEquals(updatedVisitDTO.getDescription(), visitFound.getDescription());
            assertEquals(updatedVisitDTO.getCost(), visitFound.getCost());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFindVisitById() {
        VisitDTO visitDTO = VisitDTO.builder()
                .petId(1)
                .vetId(1)
                .visitDate(LocalDate.of(2025, 3, 5))
                .description("vaccination")
                .cost(new BigDecimal("30.00"))
                .build();
        VisitDTO createdVisitDTO = this.visitService.create(visitDTO);

        try {
            VisitDTO visitFound = this.visitService.findById(createdVisitDTO.getId());
            assertEquals(createdVisitDTO.getId(), visitFound.getId());
            assertEquals(visitDTO.getPetId(), visitFound.getPetId());
            assertEquals(visitDTO.getVetId(), visitFound.getVetId());
            assertEquals(visitDTO.getVisitDate(), visitFound.getVisitDate());
            assertEquals(visitDTO.getDescription(), visitFound.getDescription());
            assertEquals(visitDTO.getCost(), visitFound.getCost());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }
    }
}
