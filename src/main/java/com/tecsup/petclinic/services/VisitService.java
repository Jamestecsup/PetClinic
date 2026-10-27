package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;

import java.util.List;

public interface VisitService {

    List<VisitDTO> findByPetId(Integer petId);

    List<VisitDTO> findByVetId(Integer vetId);

    void delete(Integer id) throws VisitNotFoundException;

    VisitDTO findById(Integer id) throws VisitNotFoundException;
}
