package com.fosagri.rest;

import com.fosagri.model.entity.Adherent;
import com.fosagri.model.entity.Demande;
import com.fosagri.repository.adherent.AdherentRepository;
import com.fosagri.repository.demande.DemandeRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdherentApi {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private AdherentRepository adherentRepository;
    @RequestMapping(value = "/adherentByPPR/{ppr}", method = RequestMethod.GET)
    public Adherent pprAdhr(@PathVariable String ppr) {
        String codAg=extractPPR(ppr);
        System.out.println(codAg);
          Adherent adherent = adherentRepository.findByPpr(codAg);
         return adherent;
    }
    public   String extractPPR(String pprStr){
        String numberOnly= pprStr.replaceAll("[^0-9]", "");
        return numberOnly;
    }

    @RequestMapping(value = "/adherents", method = RequestMethod.GET)
    public List<Adherent> read() {
         return  IteratorUtils.toList(adherentRepository.findAll().iterator());
    }


}

