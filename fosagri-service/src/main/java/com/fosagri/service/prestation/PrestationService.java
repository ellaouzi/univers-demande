package com.fosagri.service.prestation;


import com.fosagri.model.entity.Prestation;

import java.util.List;

public interface PrestationService {

    Prestation findById(Long id);

    Prestation findByName(String name);

    void savePrestation(Prestation prestation);

    void updatePrestation(Prestation prestation);

    void deletePrestationById(Long id);

    List<Prestation> findAllPrestations();

    void deleteAllPrestations();

    public boolean isPrestationExist(Prestation prestation);

}