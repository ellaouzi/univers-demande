package com.fosagri.repository.prestation;

import com.fosagri.model.entity.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository
public interface PrestationRepository extends JpaRepository<Prestation, Long> {
	@Query("select p from Prestation p order by p.titre")
	List<Prestation> getAllPrestations();
	
	@Query("select count(p) from Prestation p where p.id =:id")
	Integer getNumOfAdherentForPrestations(@Param("id") Long id);

	/* A version to fetch List instead of Page to avoid extra count query. */
	List<Prestation> findAllBy(Pageable pageable);

	List<Prestation> findByNomLikeIgnoreCase(String nameFilter);

	// For lazy loading and filtering
	List<Prestation> findByNomLikeIgnoreCase(String nameFilter, Pageable pageable);

	long countByNomLikeIgnoreCase(String nameFilter);

}
