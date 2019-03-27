package com.fosagri.service.prestation.demande;


import com.fosagri.model.entity.Prestation;
import com.fosagri.repository.demande.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("prestationService")
public class PrestationServiceImpl implements PrestationService {

    @Autowired
    PrestationRepository prestationRepository;

    public Prestation findById(Long id) {
        return prestationRepository.findById(id);
    }
}