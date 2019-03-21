package com.fosagri.ui.students;

import java.util.List;

import com.vaadin.data.validator.BeanValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gwt.i18n.server.testing.Gender;
import com.fosagri.model.entity.Student;
import com.fosagri.model.entity.University;
import com.fosagri.service.addstudent.AddStudentService;
import com.fosagri.service.showUniversityService.ShowAllUniversitiesService;
import com.fosagri.utils.NotificationMessages;
import com.fosagri.utils.StudentStringUitls;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory {
	
	private class AddStudentMainLayout extends VerticalLayout implements Button.ClickListener {
		
		private TextField firstName;
		private TextField lastName;
		private TextField age;
		private ComboBox gender;
		private ComboBox university;
		private Button saveButton;
		private Button clearButton;
		
		private BeanFieldGroup<Student> fieldGroup;
		private Student student;
		private StudentSavedListener studentSavedListener;
		
		public AddStudentMainLayout(StudentSavedListener studentSavedListener) {
			this.studentSavedListener = studentSavedListener;
		}
		
		public AddStudentMainLayout init() {
			fieldGroup = new BeanFieldGroup<Student>(Student.class);
			student = new Student();
			
			firstName = new TextField(StudentStringUitls.FIRST_NAME.getString());
			lastName = new TextField(StudentStringUitls.LAST_NAME.getString());
			age = new TextField(StudentStringUitls.AGE.getString());

			age.addValidator(new IntegerRangeValidator("Value must be a integer between 0 and 100", 0, 100));

			age.setValidationVisible(true);
			//age.addValidator(new BeanValidator(Student.class, "age"));

			firstName.setImmediate(true);

			gender = new ComboBox(StudentStringUitls.GENDER.getString());
			
			saveButton = new Button(StudentStringUitls.SAVE_BUTTON.getString());
			clearButton = new Button(StudentStringUitls.CLEAR_BUTTON.getString());
			university = new ComboBox(StudentStringUitls.UNIVERSITY.getString());
			university.setWidth("100%");
			
			saveButton.addClickListener(this);
			clearButton.addClickListener(this);
			
			
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			clearButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
			
			gender.addItem(Gender.FEMALE.toString());
			gender.addItem(Gender.MALE.toString());
			gender.addItem(Gender.MALE.toString());

			firstName.setNullRepresentation("");
			lastName.setNullRepresentation("");
			age.setNullRepresentation("");
			
			return this;
		}
	

		private AddStudentMainLayout bind() {
			fieldGroup.bindMemberFields(this);

			fieldGroup.setItemDataSource(student);
			return this;
		}
		
		private boolean isSaveOperationValid() {
			return showAllUniversitiesService.getAllUniversities().size() != 0;
		}
		
		public AddStudentMainLayout load() {
			
			List<University> universities = showAllUniversitiesService.getAllUniversities();
			university.addItems(universities);
			
			return this;
		}
		
		public Component layout() {
			setMargin(true);
			
			GridLayout gridLayout = new GridLayout(2, 4);
			gridLayout.setSpacing(true);
			gridLayout.addComponent(firstName,0,0);
			gridLayout.addComponent(lastName,1,0);
			
			gridLayout.addComponent(age, 0, 1);
			gridLayout.addComponent(gender, 1,1);
			
			gridLayout.addComponent(university, 0, 2,1,2);
			
			gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0, 3);

			return gridLayout;

		}


		public void buttonClick(ClickEvent event) {
			
			if(event.getSource() == this.saveButton) {
				save();

			}else {
				clearField();
			}
			
		}
		
		private void save() {
			if(!isSaveOperationValid()) {
				Notification.show(NotificationMessages.STUDENT_SAVE_INVALID_ERROR_TITLE.getString(),
						NotificationMessages.STUDENT_SAVE_INVALID_ERROR_DESCRIPTION.getString(),
						Type.ERROR_MESSAGE);
				return;
			}
			
			try {
				fieldGroup.commit();
				System.out.println("AskUserName=================================");
				askUserName() ;
			} catch (CommitException e) {
				 //Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE.getString(),
						//NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(),
						//Type.ERROR_MESSAGE);

				Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(), e.getCause() != null ? e.getCause().getMessage() : e.getMessage(),
						Notification.Type.ERROR_MESSAGE);
				 return;
			}
			addStudentService.saveStudent(student);
			studentSavedListener.studentSaved();
			clearField();
			
			Notification.show(NotificationMessages.STUDENT_SAVE_SUCCESS_TITLE.getString(),
					NotificationMessages.STUDENT_SAVE_SUCCESS_DESCRIPTION.getString(),
					Type.WARNING_MESSAGE);
			
		}
		private void askUserName() {
			HorizontalLayout usernameLayout = new HorizontalLayout();
			TextField usernameField = new TextField();
			Button startButton = new Button("Start Chatting");
			usernameLayout.addComponents(usernameField, startButton);


		}
		
		
		private void clearField() {
			firstName.setValue(null);
			lastName.setValue(null);
			gender.setValue(null);
			age.setValue(null);
			university.setValue(null);
		}
	}
	
	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;
	
	@Autowired
	private AddStudentService addStudentService;
	
	public Component createComponent(StudentSavedListener studentSavedListener) {
		return new AddStudentMainLayout(studentSavedListener).init().load().bind().layout();
	}

}
