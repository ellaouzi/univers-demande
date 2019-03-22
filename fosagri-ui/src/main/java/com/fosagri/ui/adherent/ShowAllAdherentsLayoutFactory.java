package com.fosagri.ui.adherent;

import java.util.List;

import com.fosagri.service.adherentService.ShowAllAdherentsService;
import org.springframework.beans.factory.annotation.Autowired;

import com.fosagri.model.entity.Adherent;
 import com.fosagri.ui.commonns.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowAllAdherentsLayoutFactory implements UIComponentBuilder{

	private List<Adherent> adherents;
	private BeanItemContainer<Adherent> container;

	public void refreshTable() {
		adherents = showAllAdherentsService.getAllAdherents();
		container.removeAllItems();
		container.addAll(adherents);
	}


	private class ShowAllAdherentsLayout extends VerticalLayout {
		private Grid adherentsTable;

		public ShowAllAdherentsLayout init() {
			setMargin(true);
			container = new BeanItemContainer<Adherent>(Adherent.class, adherents);
			adherentsTable = new Grid(container);
			adherentsTable.setColumnOrder("nom", "prenom","ppr", "age", "gender");
			adherentsTable.removeColumn("id");
 			adherentsTable.setImmediate(true);
			return this;
		}

		public ShowAllAdherentsLayout load() {
			adherents = showAllAdherentsService.getAllAdherents();
			return this;
		}

		public ShowAllAdherentsLayout layout() {
			addComponent(adherentsTable);
			return this;
		}
	}

	@Autowired
	private ShowAllAdherentsService showAllAdherentsService;

	public Component createComponent() {
		return new ShowAllAdherentsLayout().init().load().layout();
	}



}
