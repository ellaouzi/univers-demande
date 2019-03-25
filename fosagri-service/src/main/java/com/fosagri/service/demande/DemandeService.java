package com.fosagri.service.demande;


import com.fosagri.model.entity.Demande;

import java.util.List;

public interface DemandeService {

    Demande findById(Long id);

    Demande findByName(String name);


    void saveDemande(Demande demande);

    void updateDemande(Demande demande);

    void deleteDemandeById(Long id);

    List<Demande> findAllDemandes();

    void deleteAllDemandes();

    public boolean isDemandeExist(Demande demande);

}