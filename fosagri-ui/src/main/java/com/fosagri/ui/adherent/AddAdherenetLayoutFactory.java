package com.fosagri.ui.adherent;

import com.fosagri.model.entity.Adherent;
import com.fosagri.service.adherentService.AddAdherentService;
import com.fosagri.utils.NotificationMessages;
import com.fosagri.utils.AdherentStringUtils;
import com.google.gwt.i18n.server.testing.Gender;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Component
public class AddAdherenetLayoutFactory {

    @Autowired
    AddAdherentService addAdherentService;

    private class AddAdherentLayout extends VerticalLayout implements Button.ClickListener {

        private TextField nom;
        private TextField prenom;
        private TextField ppr;
        private TextField age;
        private TextField position;
        private ComboBox gender;
        private Button saveButton;
        private Adherent adherent;
        private BeanFieldGroup<Adherent> fieldGroup;
        private AdherentSavedListener adherentSavedListener;

        public AddAdherentLayout(AdherentSavedListener adherentSavedListener) {
            this.adherentSavedListener = adherentSavedListener;
        }

        public AddAdherentLayout init() {

            fieldGroup = new BeanFieldGroup<Adherent>(Adherent.class);
            adherent = new Adherent();
            nom = new TextField(AdherentStringUtils.ADHERENT_NOM.getString());
            ppr = new TextField(AdherentStringUtils.ADHERENT_PPR.getString());
            prenom = new TextField(AdherentStringUtils.ADHERENT_PRENOM.getString());
            position = new TextField(AdherentStringUtils.ADHERENT_POSITION.getString());
            saveButton = new Button(AdherentStringUtils.SAVE_BUTTON.getString(), this);
            saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            age = new TextField(AdherentStringUtils.AGE.getString());
            gender = new ComboBox(AdherentStringUtils.GENDER.getString());
            gender.addItem(Gender.FEMALE.toString());
            gender.addItem(Gender.MALE.toString());
            return this;
        }

        public AddAdherentLayout bind() {
            //	fieldGroup = new BeanFieldGroup<Adherent>(Adherent.class);
            fieldGroup.bindMemberFields(this);
            fieldGroup.setItemDataSource(adherent);
            return this;
        }


        public Component layout() {
            setWidth("100%");

            GridLayout grid = new GridLayout(1, 7);
            grid.setHeightUndefined();
            grid.setSpacing(true);
            grid.addComponent(nom, 0, 0);
            grid.addComponent(prenom, 0, 1);
            grid.addComponent(ppr, 0, 2);
            grid.addComponent(age, 0, 3);
            grid.addComponent(gender, 0, 4);
            grid.addComponent(position, 0, 5);
            grid.addComponent(saveButton, 0, 6);

            return grid;
        }


        public void buttonClick(ClickEvent event) {
            try {
                fieldGroup.commit();
            } catch (CommitException exception) {
                Notification.show(NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_TITLE.getString(),
                        NotificationMessages.UNIVERSITY_SAVED_VALIDATION_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
                return;
            }
            clearFields();
            addAdherentService.addAdherent(adherent);
            adherentSavedListener.adherentSaved();
            Notification.show("SAVE", "adherent saved successfully", Type.WARNING_MESSAGE);
        }

        private void clearFields() {
            nom.setValue(null);
            prenom.setValue(null);
            ppr.setValue(null);
            gender.setValue(null);
            age.setValue(null);
            position.setValue(null);
        }
    }

    public Component createComponent(AdherentSavedListener adherentSavedListener) {
        return new AddAdherentLayout(adherentSavedListener).init().bind().layout();
    }

}
