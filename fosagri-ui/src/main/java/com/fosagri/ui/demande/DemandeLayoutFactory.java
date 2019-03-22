package com.fosagri.ui.demande;

import com.fosagri.ui.commonns.UniversMainUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = DemandeLayoutFactory.NAME, ui = UniversMainUI.class)
public class DemandeLayoutFactory extends VerticalLayout implements View, DemandeSavedListener {

    public static final String NAME = "operationsdemandes";


    @Autowired
    private AddDemandeLayoutFactory addDemandeLayoutFactory;
    private TabSheet tabSheet;

    @Autowired
    private ShowAllDemandesLayoutFactory showDemandesLayoutFactory;

    private void addLayout() {
        setMargin(true);
        tabSheet = new TabSheet();
        tabSheet.setWidth("100%");
        addComponent(tabSheet);
        Component addDemandeTab = addDemandeLayoutFactory.createComponent(this);
        Component showAllDemandesTab = showDemandesLayoutFactory.createComponent();
         tabSheet.addTab(showAllDemandesTab, "TOUTES LES DEMANDES");
        tabSheet.addTab(addDemandeTab, "NOUVELLE DEMANDE");
        addComponent(tabSheet);


    }

    public void demandeSaved() {
        showDemandesLayoutFactory.refreshTable();
    }

    public void enter(ViewChangeEvent event) {
        removeAllComponents();
        addLayout();
    }
}
