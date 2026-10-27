package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;

import java.math.BigDecimal;

public interface VisitService {

    BigDecimal calculateTotalCostByPet(Integer petId);

    Visit findById(Integer id) throws VisitNotFoundException;
}
