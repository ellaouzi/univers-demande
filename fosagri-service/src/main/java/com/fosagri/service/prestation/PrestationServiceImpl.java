package com.fosagri.service.prestation;


import com.fosagri.model.entity.Prestation;
import com.fosagri.repository.prestation.PrestationRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service("prestationService")
public class PrestationServiceImpl implements PrestationService {
    private static final AtomicLong counter = new AtomicLong();
    private static List<Prestation> prestations;

    static {
        prestations = populateDummyPrestations();
    }

    @Autowired
    PrestationRepository prestationRepository;

    private static List<Prestation> populateDummyPrestations() {
        List<Prestation> prestations = new ArrayList<Prestation>();

        return prestations;
    }

    public List<Prestation> findAllPrestations() {
        return IteratorUtils.toList(prestationRepository.findAll().iterator());

    }

    public Prestation findById(long id) {
        for (Prestation prestation : prestations) {
            if (prestation.getId() == id) {
                return prestation;
            }
        }
        return null;
    }

    public Prestation findById(Long id) {
        return prestationRepository.getOne(id);
    }

    public Prestation findByName(String name) {
        for (Prestation prestation : prestations) {
            if (prestation.getTitre().equalsIgnoreCase(name)) {
                return prestation;
            }
        }
        return null;
    }

    public void savePrestation(Prestation prestation) {
        //  prestation.setId(counter.incrementAndGet());
        prestationRepository.save(prestation);
    }

    public void updatePrestation(Prestation prestation) {
        prestationRepository.save(prestation);
    }

    public void deletePrestationById(Long id) {
        prestationRepository.delete(id);

    }

    public boolean isPrestationExist(Prestation prestation) {
        return findByName(prestation.getTitre()) != null;
    }

    public void deleteAllPrestations() {
        prestations.clear();
    }


}