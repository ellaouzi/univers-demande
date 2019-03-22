package com.fosagri.ui.adherent;

import com.fosagri.ui.commonns.UniversMainUI;
import com.fosagri.utils.StudentStringUitls;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name=AdherentLayoutFactory.NAME, ui=UniversMainUI.class)
public class AdherentLayoutFactory extends VerticalLayout implements View, AdherentSavedListener{

	public static final String NAME= "operationsadherents";
	
	@Autowired
	private AddAdherenetLayoutFactory addAdherentLayoutFactory;
	private TabSheet tabSheet;

	@Autowired
	private ShowAllAdherentsLayoutFactory showAdherentsLayoutFactory;

	@Autowired
	private StatisticAdherentLayoutFactory statisticAdherentLayoutFactory;
	private void addLayout() {
		setMargin(true);
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		addComponent(tabSheet);
		Component addAdherentTab = addAdherentLayoutFactory.createComponent(this);
	    Component showAllAdherentsTab = showAdherentsLayoutFactory.createComponent();
		tabSheet.addTab(addAdherentTab, "ADD ADHERENT");
	    tabSheet.addTab(showAllAdherentsTab, "Show All ADHERENTS");
		addComponent(tabSheet);
	}
	
	public void adherentSaved() {
		showAdherentsLayoutFactory.refreshTable();
		statisticAdherentLayoutFactory.refresh();
	}

	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}
}
