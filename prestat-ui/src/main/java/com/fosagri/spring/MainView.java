package com.fosagri.spring;

import com.fosagri.model.entity.Demande;
import com.fosagri.repository.demande.DemandeRepository;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
 import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.grid.MGrid;
import sun.applet.Main;

import javax.servlet.annotation.WebServlet;
import javax.xml.bind.Binder;
import java.util.Arrays;
import java.util.List;

@SpringUI(path=MainView.NAME)
@Title("F O S A - G r i")

 class MainView extends UI {
@Autowired
DemandeRepository demandeRepository;
    private Demande demande;

    public static final String NAME="/prestat";
    private Grid grid = new Grid();

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private Button save = new Button("Save", e -> saveCustomer());
    VerticalLayout vbox = new VerticalLayout();

    HorizontalLayout hbox = new HorizontalLayout();

    @Override
    protected void init(VaadinRequest request) {





        updateGrid();
         final BeanItemContainer<Demande> ds =
                new BeanItemContainer<Demande>(Demande.class, demandeRepository.findAll());
          Grid grid = new Grid();

         grid.addColumn("Choix1", String.class);
        grid.addColumn("Choix2", String.class);
        Button loadButton = new Button("Load data");
        loadButton.addClickListener(e -> {

            grid.getContainerDataSource().removeAllItems();

            List<Demande> demandes = demandeRepository.findAll();

            for (Demande demande : demandes) {

                grid.addRow(demande.getChoix1(), demande.getChoix2());
            }
        });
        Button clearButton = new Button("Clear data");
        clearButton.addClickListener(e -> {
            grid.getContainerDataSource().removeAllItems();
        });
        vbox.addComponents(grid);
        hbox.addComponents(loadButton, clearButton);
        hbox.setSpacing(true);
        vbox.addComponent(hbox);
        vbox.setMargin(true);
        vbox.setSpacing(true);

        setContent(vbox);

        Grid.SelectionModel selectionModel =
                 grid.setSelectionMode(Grid.SelectionMode.SINGLE);
    }

    private void updateGrid() {
        List demandes = demandeRepository.findAll();
        grid.setContainerDataSource(new BeanItemContainer<>(Demande.class,
                demandes));
        setFormVisible(false);
    }

    private void updateForm() {


        if (grid.getSelectedRows().isEmpty()) {
            setFormVisible(false);
        } else {
            demande = (Demande) grid.getSelectedRow();
            BeanFieldGroup.bindFieldsUnbuffered(demande, this);
            setFormVisible(true);
        }
    }

    private void setFormVisible(boolean visible) {
        firstName.setVisible(visible);
        lastName.setVisible(visible);
        save.setVisible(visible);
    }

    private void saveCustomer() {
        demandeRepository.save(demande);
        updateGrid();
    }


}