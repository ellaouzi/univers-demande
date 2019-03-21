package com.fosagri.rest;



import com.fosagri.model.entity.Prestation;
import com.fosagri.repository.prestation.PrestationRepository;
import com.fosagri.service.prestation.PrestationService;
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
    PrestationService prestationService;  //Service which will do all data retrieval/manipulation work



//-------------------Retrieve All Prestations--------------------------------------------------------

    @RequestMapping(value = "/prestation/", method = RequestMethod.GET)
    public ResponseEntity<List<Prestation>> listAllPrestations() {
        List<Prestation> Prestations = prestationService.findAllPrestations();
        if(Prestations.isEmpty()){
            return new ResponseEntity<List<Prestation>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Prestation>>(Prestations, HttpStatus.OK);
    }


    @RequestMapping(value = "/prestation/test", method = RequestMethod.GET)
    public  void test() {
        System.out.println("ca marche ======================================================");

    }


    //-------------------Retrieve Single Prestation--------------------------------------------------------

    @RequestMapping(value = "/prestation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prestation> getPrestation(@PathVariable("id") long id) {
         Prestation Prestation = prestationService.findById(id);
        if (Prestation == null) {
             return new ResponseEntity<Prestation>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Prestation>(Prestation, HttpStatus.OK);
    }



    //-------------------Create a Prestation--------------------------------------------------------

    @RequestMapping(value = "/prestation/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPrestation(@RequestBody Prestation prestation,    UriComponentsBuilder ucBuilder) {

        if (prestationService.isPrestationExist(prestation)) {
            System.out.println("A Prestation with name " + prestation.getId() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        else {
            prestation.setStatut("Nouvelle demande");
            prestation.setDateprestation(new Date());
        }

        prestationService.savePrestation(prestation);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/prestation/{id}").buildAndExpand(prestation.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    //------------------- Update a Prestation --------------------------------------------------------

    @RequestMapping(value = "/prestation/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Prestation> updatePrestation(@PathVariable("id") long id, @RequestBody Prestation prestation) {
        System.out.println("Updating Prestation " + id);

        Prestation currentPrestation = prestationService.findById(id);

        if (currentPrestation==null) {
            System.out.println("Prestation with id " + id + " not found");
            return new ResponseEntity<Prestation>(HttpStatus.NOT_FOUND);
        }

        currentPrestation.setTitre(prestation.getTitre());
        currentPrestation.setType(prestation.getType());
        currentPrestation.setStatut(prestation.getStatut());
        currentPrestation.setCodAg(prestation.getCodAg());
        currentPrestation.setChoix1(prestation.getChoix1());
        currentPrestation.setChoix2(prestation.getChoix2());
        currentPrestation.setPeriode1(prestation.getPeriode1());
        currentPrestation.setPeriode2(prestation.getPeriode2());
        currentPrestation.setPprconj(prestation.getPprconj());
        currentPrestation.setEmail(prestation.getEmail());
        //currentPrestation.setDateprestation(new Date());
        currentPrestation.setGsm(prestation.getGsm());
        prestationService.updatePrestation(currentPrestation);
        return new ResponseEntity<Prestation>(currentPrestation, HttpStatus.OK);
    }



    //------------------- Delete a Prestation --------------------------------------------------------

    @RequestMapping(value = "/prestation/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Prestation> deletePrestation(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Prestation with id " + id);

        Prestation Prestation = prestationService.findById(id);
        if (Prestation == null) {
             return new ResponseEntity<Prestation>(HttpStatus.NOT_FOUND);
        }

        prestationService.deletePrestationById(id);
        return new ResponseEntity<Prestation>(HttpStatus.NO_CONTENT);
    }



    //------------------- Delete All Prestations --------------------------------------------------------

    @RequestMapping(value = "/prestation/", method = RequestMethod.DELETE)
    public ResponseEntity<Prestation> deleteAllPrestations() {
        System.out.println("Deleting All Prestations");

        prestationService.deleteAllPrestations();
        return new ResponseEntity<Prestation>(HttpStatus.NO_CONTENT);
    }

}