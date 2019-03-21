package com.fosagri.repository.adherent;

import com.fosagri.model.entity.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
 Adherent findByPpr(String ppr);

	@Query("select a from Adherent a where a.ppr =:ppr")
	Adherent findAdherentByPpr(@Param("ppr") String ppr);

	@Query("select count(a) from Adherent a ")
	Integer getNumOfAdherents();


}
