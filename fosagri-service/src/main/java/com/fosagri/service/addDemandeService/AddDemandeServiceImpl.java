package com.fosagri.service.addDemandeService;

import com.fosagri.model.entity.Demande;
import com.fosagri.repository.demande.DemandeRepository;
import com.fosagri.service.addDemandeService.AddDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddDemandeServiceImpl implements AddDemandeService  {

	@Autowired
	private DemandeRepository demandeRepository;
	
	public void addDemande(Demande demandeDAO) {
		Demande demande = new Demande();
		demande.setBenificiare(demandeDAO.getBenificiare());
		demande.setChoix1(demandeDAO.getChoix1());
		demande.setPeriode1(demandeDAO.getPeriode1());
		demande.setCodAg(demandeDAO.getCodAg());
		demande.setChoix2(demandeDAO.getChoix2());
		demande.setPeriode2(demandeDAO.getPeriode2());
		demande.setEmail(demandeDAO.getEmail());
		demande.setGsm(demandeDAO.getGsm());
		demande.setNombre(demandeDAO.getNombre());
		demande.setPprconj(demandeDAO.getPprconj());
		demande.setDatedemande(new Date());
		demande.setStatut("Nouvelle demande");

		demandeRepository.save(demande);
	}
	
}
