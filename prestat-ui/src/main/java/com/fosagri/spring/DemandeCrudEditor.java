package com.fosagri.spring;

import com.fosagri.model.entity.Demande;
import com.fosagri.repository.demande.DemandeRepository;
//import com.vaadin.flow.spring.annotation.SpringComponent;
//import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class DemandeCrudEditor {

 private DemandeRepository repository;
 private Demande demande;


}
