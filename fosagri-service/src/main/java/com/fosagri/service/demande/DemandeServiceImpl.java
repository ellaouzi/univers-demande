package com.fosagri.service.demande;


import com.fosagri.model.entity.Demande;
import com.fosagri.repository.demande.DemandeRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service("demandeService")
public class DemandeServiceImpl implements DemandeService {
    private static final AtomicLong counter = new AtomicLong();
    private static List<Demande> demandes;

    static {
        demandes = populateDummyDemandes();
    }

    @Autowired
    DemandeRepository demandeRepository;

    private static List<Demande> populateDummyDemandes() {
        List<Demande> demandes = new ArrayList<Demande>();

        return demandes;
    }

    public List<Demande> findAllDemandes() {
        return IteratorUtils.toList(demandeRepository.findAll().iterator());

    }

    public Demande findById(long id) {
        for (Demande demande : demandes) {
            if (demande.getId() == id) {
                return demande;
            }
        }
        return null;
    }

    public Demande findById(Long id) {
        return demandeRepository.getOne(id);
    }

    public Demande findByName(String name) {
        for (Demande demande : demandes) {
            if (demande.getTitre().equalsIgnoreCase(name)) {
                return demande;
            }
        }
        return null;
    }

    public void saveDemande(Demande demande) {
        //  demande.setId(counter.incrementAndGet());
        demandeRepository.save(demande);
    }

    public void updateDemande(Demande demande) {
        demandeRepository.save(demande);
    }

    public void deleteDemandeById(Long id) {
        demandeRepository.delete(id);

    }

    public boolean isDemandeExist(Demande demande) {
        return findByName(demande.getTitre()) != null;
    }

    public void deleteAllDemandes() {
        demandes.clear();
    }

	public List<Demande> findByPrestationId(Long prestationId) {
	return demandeRepository.findByPrestationId(prestationId);
	}


}