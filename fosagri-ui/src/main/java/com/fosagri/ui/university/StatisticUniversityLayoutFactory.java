package com.fosagri.ui.university;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fosagri.model.entity.University;
import com.fosagri.service.showUniversityService.ShowAllUniversitiesService;
import com.fosagri.service.universityStatisticService.UniversityStatisticService;
import com.fosagri.ui.commonns.UIComponentBuilder;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class StatisticUniversityLayoutFactory  implements UIComponentBuilder {
	
	private List<University> universities;
	
	private StatisticsUniversityLayout statisticsLayout;
	
	@Autowired
	private UniversityStatisticService universityStatisticService;
	
	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;
	
	
	private class StatisticsUniversityLayout extends VerticalLayout {
		
		public StatisticsUniversityLayout load() {
			universities = showAllUniversitiesService.getAllUniversities();
			return this;
		}
		
		public StatisticsUniversityLayout layout() {	
			setMargin(true);
			for(University university : universities) {
				int numOfStudents = universityStatisticService.getNumOfStudentsForUniversity(university.getId());
				Label label = new Label("<p><b>"+university.getUniversityName()+"</b>"+" - "+numOfStudents+" Students "+"</p>",  ContentMode.HTML);
				addComponent(label);
				
			}
			return this;
		}
		
	}
	
	public void refresh() {
		if(statisticsLayout == null) return;
		statisticsLayout.removeAllComponents();
		statisticsLayout.load();
		statisticsLayout.layout();
	}
	
	
	public Component createComponent() {	
		statisticsLayout = new StatisticsUniversityLayout();
		return statisticsLayout.load().layout();
	}
}
