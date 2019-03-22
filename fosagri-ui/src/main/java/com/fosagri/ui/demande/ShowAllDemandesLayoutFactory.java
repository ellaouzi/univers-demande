package com.fosagri.ui.demande;

import com.fosagri.model.entity.Demande;
import com.fosagri.service.showDemandeService.ShowAllDemandesService;
import com.fosagri.ui.commonns.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Component
public class ShowAllDemandesLayoutFactory implements UIComponentBuilder{

	private List<Demande> adherents;
	private BeanItemContainer<Demande> container;

	public void refreshTable() {
		adherents = showAllDemandesService.getAllDemandes();
		container.removeAllItems();
		container.addAll(adherents);
	}


	private class ShowAllDemandesLayout extends VerticalLayout {
		private Grid adherentsTable;

		public ShowAllDemandesLayout init() {
			setWidth("100%");
			setMargin(true);

			container = new BeanItemContainer<Demande>(Demande.class, adherents);
			adherentsTable = new Grid(container);
			adherentsTable.setColumnOrder("codAg", "nombre","choix1","choix2", "periode1","periode2","gsm","email","statut");
			adherentsTable.removeColumn("id");
 			adherentsTable.removeColumn("destination");
 			adherentsTable.removeColumn("titre");
			adherentsTable.removeColumn("nom");
			adherentsTable.removeColumn("type");
			adherentsTable.removeColumn("benificiare");
 			adherentsTable.setImmediate(true);
 			adherentsTable.setWidth("100%");
			return this;
		}

		public ShowAllDemandesLayout load() {
			adherents = showAllDemandesService.getAllDemandes();
			return this;
		}

		public ShowAllDemandesLayout layout() {
			addComponent(adherentsTable);
			return this;
		}
	}

	@Autowired
	private ShowAllDemandesService showAllDemandesService;

	public Component createComponent() {
		return new ShowAllDemandesLayout().init().load().layout();
	}



}
