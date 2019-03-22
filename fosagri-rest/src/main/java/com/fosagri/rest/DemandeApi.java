package com.fosagri.rest;



import com.fosagri.model.entity.Demande;
import com.fosagri.repository.demande.DemandeRepository;
import com.fosagri.service.demande.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/relais")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DemandeApi {

    @Autowired
    DemandeService demandeService;  //Service which will do all data retrieval/manipulation work



//-------------------Retrieve All Demandes--------------------------------------------------------

    @RequestMapping(value = "/demande/", method = RequestMethod.GET)
    public ResponseEntity<List<Demande>> listByDemandes() {
        List<Demande> Demandes = demandeService.findAllDemandes();
        if(Demandes.isEmpty()){
            return new ResponseEntity<List<Demande>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Demande>>(Demandes, HttpStatus.OK);
    }

    @RequestMapping(value = "/demande/{prestation}", method = RequestMethod.GET)
    public ResponseEntity<List<Demande>> listDemandesByPresationRefId(@PathVariable("prestation") Long prestation) {
        List<Demande> Demandes = demandeService.findByPrestationId(prestation);
        if(Demandes.isEmpty()){
            return new ResponseEntity<List<Demande>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Demande>>(Demandes, HttpStatus.OK);
    }

    //-------------------Retrieve Single Demande--------------------------------------------------------

    @RequestMapping(value = "/demande/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Demande> getDemande(@PathVariable("id") long id) {
         Demande Demande = demandeService.findById(id);
        if (Demande == null) {
             return new ResponseEntity<Demande>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Demande>(Demande, HttpStatus.OK);
    }

    //-------------------Create a Demande--------------------------------------------------------

    @RequestMapping(value = "/demande/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDemande(@RequestBody Demande demande,    UriComponentsBuilder ucBuilder) {

        if (demandeService.isDemandeExist(demande)) {
            System.out.println("A Demande with name " + demande.getId() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        else {
            demande.setStatut("Nouvelle demande");
            demande.setDatedemande(new Date());
        }
        demandeService.saveDemande(demande);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/demande/{id}").buildAndExpand(demande.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    //------------------- Update a Demande --------------------------------------------------------

    @RequestMapping(value = "/demande/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Demande> updateDemande(@PathVariable("id") long id, @RequestBody Demande demande) {
        System.out.println("Updating Demande " + id);
        Demande currentDemande = demandeService.findById(id);
        if (currentDemande==null) {
            System.out.println("Demande with id " + id + " not found");
            return new ResponseEntity<Demande>(HttpStatus.NOT_FOUND);
        }
        currentDemande.setTitre(demande.getTitre());
        currentDemande.setType(demande.getType());
        currentDemande.setStatut(demande.getStatut());
        currentDemande.setCodAg(demande.getCodAg());
        currentDemande.setChoix1(demande.getChoix1());
        currentDemande.setChoix2(demande.getChoix2());
        currentDemande.setPeriode1(demande.getPeriode1());
        currentDemande.setPeriode2(demande.getPeriode2());
        currentDemande.setPprconj(demande.getPprconj());
        currentDemande.setEmail(demande.getEmail());
        currentDemande.setGsm(demande.getGsm());
        demandeService.updateDemande(currentDemande);
        return new ResponseEntity<Demande>(currentDemande, HttpStatus.OK);
    }



    //------------------- Delete a Demande --------------------------------------------------------

    @RequestMapping(value = "/demande/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Demande> deleteDemande(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Demande with id " + id);
        Demande Demande = demandeService.findById(id);
        if (Demande == null) {
             return new ResponseEntity<Demande>(HttpStatus.NOT_FOUND);
        }

        demandeService.deleteDemandeById(id);
        return new ResponseEntity<Demande>(HttpStatus.NO_CONTENT);
    }



    //------------------- Delete All Demandes --------------------------------------------------------

    @RequestMapping(value = "/demande/", method = RequestMethod.DELETE)
    public ResponseEntity<Demande> deleteAllDemandes() {
        System.out.println("Deleting All Demandes");
        demandeService.deleteAllDemandes();
        return new ResponseEntity<Demande>(HttpStatus.NO_CONTENT);
    }

}