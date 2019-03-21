package com.fosagri.service.adherentService;

import com.fosagri.model.entity.Adherent;

import java.util.List;

public interface AdherentService {
	
	public void addAdherent(Adherent adherent);
	public Adherent findAdherentByPpr(String ppr);

	interface ShowAllAdherentsService {
		List<Adherent> getAllAdherents();
	}
}
