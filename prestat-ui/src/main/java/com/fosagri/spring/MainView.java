package com.fosagri.spring;

import com.fosagri.model.entity.Prestation;
import com.fosagri.repository.prestation.PrestationRepository;
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
PrestationRepository prestationRepository;
    private Prestation prestation;

    public static final String NAME="/prestat";
    private Grid grid = new Grid();
   // Crud<Prestation> crud = new Crud<>(Prestation.class, new PrestationCrudEditor());

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private Button save = new Button("Save", e -> saveCustomer());
    VerticalLayout vbox = new VerticalLayout();

    HorizontalLayout hbox = new HorizontalLayout();

    @Override
    protected void init(VaadinRequest request) {

      //  GridCrud<Prestation> crud = new GridCrud<>(User.class);




        updateGrid();
        //grid.addColumn("ppr");
        //grid.addColumn("nom");
        final BeanItemContainer<Prestation> ds =
                new BeanItemContainer<Prestation>(Prestation.class, prestationRepository.findAll());
       // Grid grid = new Grid("Employees", ds);
         Grid grid = new Grid();

         grid.addColumn("Nom", String.class);
        grid.addColumn("Coix1", String.class);
        Button loadButton = new Button("Load data");
        loadButton.addClickListener(e -> {

            grid.getContainerDataSource().removeAllItems();

            List<Prestation> prestations = prestationRepository.findAll();

            for (Prestation prestation : prestations) {

                grid.addRow(prestation.getNom(), prestation.getChoix1());
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
        List prestations = prestationRepository.findAll();
        grid.setContainerDataSource(new BeanItemContainer<>(Prestation.class,
                prestations));
        setFormVisible(false);
    }

    private void updateForm() {


        if (grid.getSelectedRows().isEmpty()) {
            setFormVisible(false);
        } else {
            prestation = (Prestation) grid.getSelectedRow();
            BeanFieldGroup.bindFieldsUnbuffered(prestation, this);
            setFormVisible(true);
        }
    }

    private void setFormVisible(boolean visible) {
        firstName.setVisible(visible);
        lastName.setVisible(visible);
        save.setVisible(visible);
    }

    private void saveCustomer() {
        prestationRepository.save(prestation);
        updateGrid();
    }


}