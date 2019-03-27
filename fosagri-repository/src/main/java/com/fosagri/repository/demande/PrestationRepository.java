package com.fosagri.repository.demande;

import com.fosagri.model.entity.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestResource (path = "prestations", rel = "prestations")
 public interface PrestationRepository extends JpaRepository<Prestation, Long> {
    Prestation findById(Long id);
}
