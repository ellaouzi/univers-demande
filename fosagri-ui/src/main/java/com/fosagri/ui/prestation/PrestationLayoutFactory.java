package com.fosagri.ui.prestation;

import com.fosagri.ui.commonns.UniversMainUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name=PrestationLayoutFactory.NAME, ui=UniversMainUI.class)
public class PrestationLayoutFactory extends VerticalLayout implements View, PrestationSavedListener{

	public static final String NAME= "operationsprestations";

	
	@Autowired
	private AddPrestationLayoutFactory addPrestationLayoutFactory;
	private TabSheet tabSheet;

	@Autowired
	private ShowAllPrestationsLayoutFactory showPrestationsLayoutFactory;
 
	private void addLayout() {
		setMargin(true);
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");





		addComponent(tabSheet);

		Component addPrestationTab = addPrestationLayoutFactory.createComponent(this);
	    Component showAllPrestationsTab = showPrestationsLayoutFactory.createComponent();
		//ME Component showStatistic = statisticPrestationLayoutFactory.createComponent();
		tabSheet.addTab(showAllPrestationsTab, "TOUTES LES DEMANDES");

		tabSheet.addTab(addPrestationTab, "NOUVELLE DEMANDE");
		//ME tabSheet.addTab(showStatistic, "StatISTICS");
		
		addComponent(tabSheet);
	
		
	}
	
	public void prestationSaved() {
		showPrestationsLayoutFactory.refreshTable();
 	}

	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}
}
