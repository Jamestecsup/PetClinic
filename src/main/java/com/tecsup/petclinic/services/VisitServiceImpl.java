package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.mappers.VisitMapper;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    @Override
    public VisitDTO create(VisitDTO visitDTO) {
        Visit visit = visitRepository.save(visitMapper.mapToEntity(visitDTO));
        return visitMapper.mapToDto(visit);
    }

    @Override
    public VisitDTO update(VisitDTO visitDTO) {
        Visit visit = visitRepository.save(visitMapper.mapToEntity(visitDTO));
        return visitMapper.mapToDto(visit);
    }

    @Override
    public VisitDTO findById(Integer id) throws VisitNotFoundException {
        return visitRepository.findById(id)
                .map(visitMapper::mapToDto)
                .orElseThrow(() -> new VisitNotFoundException("Record not found...!"));
    }
}
