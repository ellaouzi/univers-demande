package com.fosagri.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fosagri.model.entity.Student;
import com.fosagri.service.showallstudents.ShowAllStudentsService;
import com.fosagri.ui.commonns.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class ShowAllStudentsLayoutFactory implements UIComponentBuilder{

	private List<Student> students;
	private BeanItemContainer<Student> container;
	
	public void refreshTable() {
		students = showAllStudentsService.getAllStudents();
		container.removeAllItems();
		container.addAll(students);
	}
	
	
	private class ShowAllStudentsLayout extends VerticalLayout {
		private Grid studentsTable;
		
		public ShowAllStudentsLayout init() {
			setMargin(true);
			
			container = new BeanItemContainer<Student>(Student.class, students);
			studentsTable = new Grid(container);
			studentsTable.setColumnOrder("firstName", "lastName", "age", "gender");
			studentsTable.removeColumn("id");
			studentsTable.removeColumn("university");
			studentsTable.setImmediate(true);
			
			
			return this;
		}
		
		public ShowAllStudentsLayout load() {
			students = showAllStudentsService.getAllStudents();
			return this;
		}
		
		public ShowAllStudentsLayout layout() {
			addComponent(studentsTable);
			return this;
		}
	}
	
	@Autowired
	private ShowAllStudentsService showAllStudentsService;
	
	public Component createComponent() {
		return new ShowAllStudentsLayout().init().load().layout();
	}
	
	

}
