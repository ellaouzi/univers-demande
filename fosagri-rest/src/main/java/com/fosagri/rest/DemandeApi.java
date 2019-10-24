package com.fosagri.rest;


import com.fosagri.model.entity.Demande;
import com.fosagri.repository.demande.DemandeRepo;
import com.fosagri.service.demande.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DemandeApi {
 @Autowired
 DemandeService demandeRepo;

 @GetMapping("/demandes")
 public List<Demande> getAllDemandes() {

  System.out.println("Get all AdhAgents...");
  List<Demande> list = new ArrayList<>();
  Iterable<Demande> customers = demandeRepo.findAllDemandes();
  customers.forEach(list::add);
  System.out.println(list.size());

  return list;
 }

}