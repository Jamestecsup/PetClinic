package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @MockitoBean
    private VisitRepository repository;

    @Test
    public void testFindByPetId() {
        int PET_ID = 1;
        List<Visit> visitsExpected = List.of(
                new Visit(1, PET_ID, 1, LocalDate.of(2024, 1, 15), "Checkup", new BigDecimal("50.00"))
        );

        Mockito.when(this.repository.findByPetId(PET_ID))
                .thenReturn(visitsExpected);

        List<VisitDTO> visits = this.visitService.findByPetId(PET_ID);

        assertEquals(visitsExpected.size(), visits.size());
    }

    @Test
    public void testFindByVetId() {
        int VET_ID = 1;
        List<Visit> visitsExpected = List.of(
                new Visit(1, 1, VET_ID, LocalDate.of(2024, 1, 15), "Checkup", new BigDecimal("50.00"))
        );

        Mockito.when(this.repository.findByVetId(VET_ID))
                .thenReturn(visitsExpected);

        List<VisitDTO> visits = this.visitService.findByVetId(VET_ID);

        assertEquals(visitsExpected.size(), visits.size());
    }

    @Test
    public void testDeleteVisit() {
        int VISIT_ID = 1;
        Visit visit = new Visit(VISIT_ID, 1, 1, LocalDate.of(2024, 1, 15), "Checkup", new BigDecimal("50.00"));

        Mockito.when(this.repository.findById(VISIT_ID))
                .thenReturn(Optional.of(visit));
        Mockito.doNothing().when(this.repository).delete(visit);

        try {
            this.visitService.delete(VISIT_ID);
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }
    }
}
