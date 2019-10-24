package com.fosagri.repository.demande;

import com.fosagri.model.entity.Demande;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;



public interface DemandeRepo extends CrudRepository<Demande, Long> {


}
