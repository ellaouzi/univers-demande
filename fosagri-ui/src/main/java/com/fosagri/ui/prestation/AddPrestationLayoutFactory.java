package com.fosagri.ui.prestation;

import com.fosagri.model.entity.Prestation;
import com.fosagri.service.addPrestationService.AddPrestationService;
import com.fosagri.utils.*;
import com.google.gwt.i18n.server.testing.Gender;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Component
public class AddPrestationLayoutFactory {
	
	@Autowired
	AddPrestationService addPrestationService;
	
	private class AddPrestationLayout extends VerticalLayout implements Button.ClickListener {
		
		private TextField benificiare;
		private TextField codAg;
		private TextField pprconj;
		private TextField email;
		private TextField gsm;

		private TextField nombre;
		private ComboBox choix1;
		private ComboBox choix2;
		private ComboBox periode1;
		private ComboBox periode2;
		private Button saveButton;
		private Prestation prestation;
		private BeanFieldGroup<Prestation> fieldGroup;
		private PrestationSavedListener prestationSavedListener;

		public AddPrestationLayout(PrestationSavedListener prestationSavedListener) {
			this.prestationSavedListener = prestationSavedListener;
		}
		
		public AddPrestationLayout init() {

			fieldGroup = new BeanFieldGroup<Prestation>(Prestation.class);
 			prestation = new Prestation();

			benificiare = new TextField(PrestationStringUtils.PRESTATION_BENIFICIAIRE.getString());
			codAg = new TextField(PrestationStringUtils.PRESTATION_CODAG.getString());
			pprconj = new TextField(PrestationStringUtils.PRESTATION_CODAGCONJ.getString());
			email = new TextField(PrestationStringUtils.PRESTATION_EMAIL.getString());
			gsm = new TextField(PrestationStringUtils.PRESTATION_GSM.getString());
			nombre = new TextField(PrestationStringUtils.PRESTATION_NOMBRE.getString());
			nombre.addValidator(new IntegerRangeValidator("Valeur entre 1 et 7", 1, 7));

			nombre.setValidationVisible(true);
			choix1  = new ComboBox(PrestationStringUtils.PRESTATION_CHOIX1.getString());
			choix2  = new ComboBox(PrestationStringUtils.PRESTATION_CHOIX2.getString());
			periode1  = new ComboBox(PrestationStringUtils.PRESTATION_PERIODE1.getString());
			periode2  = new ComboBox(PrestationStringUtils.PRESTATION_PERIODE2.getString());

			saveButton = new Button(PrestationStringUtils.SAVE_BUTTON.getString(), this);
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);


 			periode1.addItem("Du 1 Au 6 Mars");
 			periode1.addItem("Du 9 Au 15 Mars");
 			periode1.addItem("Du 16 Au 22 Mars");

			choix1.addItem("Ifrane");
			choix1.addItem("Marrakech");
 			periode2.addItem("Du 1 Au 6 Mars");
 			periode2.addItem("Du 9 Au 15 Mars");
 			periode2.addItem("Du 16 Au 22 Mars");

			choix2.addItem("Ifrane");
			choix2.addItem("Marrakech");
			return this;
		}
		
		public AddPrestationLayout bind() {
		//	fieldGroup = new BeanFieldGroup<Prestation>(Prestation.class);
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(prestation);
			return this;
		}


		
		public Component layout() {
			setWidth("100%");
			
			GridLayout grid = new GridLayout(2, 6);
			grid.setHeightUndefined();
			grid.setSpacing(true);
			grid.addComponent(codAg, 0,1);
			grid.addComponent(pprconj, 1,1);
			grid.addComponent(nombre, 0,0);
			grid.addComponent(choix1, 0,2);
			grid.addComponent(choix2, 1,2);
			grid.addComponent(periode1, 0, 3);
			grid.addComponent(periode2, 1, 3);
			grid.addComponent(gsm, 0, 4);
			grid.addComponent(email, 1, 4);
			grid.addComponent(saveButton, 0, 5);

			return grid;
		}
		
		

		public void buttonClick(ClickEvent event) {
			try {
				fieldGroup.commit();
			} catch (CommitException exception) {
				Notification.show(NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_TITLE.getString(), 
						NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE );
				return;
			}
			clearFields();
			addPrestationService.addPrestation(prestation);
 			prestationSavedListener.prestationSaved();
			Notification.show("Demande bien ajoutée", "!", Type.WARNING_MESSAGE);
		}
		
		private void clearFields() {
			choix1.setValue(null);
			periode1.setValue(null);
			codAg.setValue(null);
			 benificiare.setValue(null); 
		}
	}
	
	public Component createComponent(PrestationSavedListener prestationSavedListener) {
		return new AddPrestationLayout(prestationSavedListener).init().bind().layout();
	}

}
