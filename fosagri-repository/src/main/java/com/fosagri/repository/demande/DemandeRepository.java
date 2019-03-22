package com.fosagri.repository.demande;

import com.fosagri.model.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
	@Query("select p from Demande p order by p.titre")
	List<Demande> getAllDemandes();
	
	List<Demande> findByPrestationId(Long prestationId);
	
	@Query("select count(p) from Demande p where p.id =:id")
	Integer getNumOfAdherentForDemandes(@Param("id") Long id);

	/* A version to fetch List instead of Page to avoid extra count query. */
	List<Demande> findAllBy(Pageable pageable);

	List<Demande> findByNomLikeIgnoreCase(String nameFilter);

	// For lazy loading and filtering
	List<Demande> findByNomLikeIgnoreCase(String nameFilter, Pageable pageable);

	long countByNomLikeIgnoreCase(String nameFilter);

}
