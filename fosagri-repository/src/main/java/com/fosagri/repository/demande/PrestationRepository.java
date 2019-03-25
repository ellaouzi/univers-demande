package com.fosagri.repository.demande;

import com.fosagri.model.entity.Demande;
import com.fosagri.model.entity.Prestation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RestResource (path = "prestations", rel = "prestations")
public interface PrestationRepository extends JpaRepository<Prestation, Long> {
}
