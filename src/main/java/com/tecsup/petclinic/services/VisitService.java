package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;

public interface VisitService {

    VisitDTO create(VisitDTO visitDTO);

    VisitDTO update(VisitDTO visitDTO);

    VisitDTO findById(Integer id) throws VisitNotFoundException;
}
