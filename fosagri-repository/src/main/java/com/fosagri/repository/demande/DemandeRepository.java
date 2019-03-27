package com.fosagri.repository.demande;

import com.fosagri.model.entity.Demande;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestResource(path = "demandes", rel = "demandes")
  public interface DemandeRepository extends JpaRepository<Demande, Long>
{
  	List<Demande> findAllBy(Pageable pageable);
}
