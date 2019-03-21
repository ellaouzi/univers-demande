package com.fosagri.spring;

import com.fosagri.model.entity.Prestation;
import com.fosagri.repository.prestation.PrestationRepository;
//import com.vaadin.flow.spring.annotation.SpringComponent;
//import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class PrestationCrudEditor {

 private PrestationRepository repository;
 private Prestation prestation;


}
