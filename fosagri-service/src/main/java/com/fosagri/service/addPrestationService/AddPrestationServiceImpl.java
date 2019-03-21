package com.fosagri.service.addPrestationService;

import com.fosagri.model.entity.Prestation;
import com.fosagri.repository.prestation.PrestationRepository;
import com.fosagri.service.addPrestationService.AddPrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddPrestationServiceImpl implements AddPrestationService  {

	@Autowired
	private PrestationRepository prestationRepository;
	
	public void addPrestation(Prestation prestationDAO) {
		Prestation prestation = new Prestation();
		prestation.setBenificiare(prestationDAO.getBenificiare());
		prestation.setChoix1(prestationDAO.getChoix1());
		prestation.setPeriode1(prestationDAO.getPeriode1());
		prestation.setCodAg(prestationDAO.getCodAg());
		prestation.setChoix2(prestationDAO.getChoix2());
		prestation.setPeriode2(prestationDAO.getPeriode2());
		prestation.setEmail(prestationDAO.getEmail());
		prestation.setGsm(prestationDAO.getGsm());
		prestation.setNombre(prestationDAO.getNombre());
		prestation.setPprconj(prestationDAO.getPprconj());
		prestation.setDateprestation(new Date());
		prestation.setStatut("Nouvelle demande");

		prestationRepository.save(prestation);
	}
	
}
