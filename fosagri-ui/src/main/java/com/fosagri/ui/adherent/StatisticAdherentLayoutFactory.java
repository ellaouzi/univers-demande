package com.fosagri.ui.adherent;

import com.fosagri.model.entity.Adherent;
import com.fosagri.service.adherentService.AdherentStatisticService;
import com.fosagri.service.adherentService.ShowAllAdherentsService;
import com.fosagri.ui.commonns.UIComponentBuilder;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Component
public class StatisticAdherentLayoutFactory implements UIComponentBuilder {
	
	private List<Adherent> adherents;
	
	private StatisticsAdherentLayout statisticsLayout;
	
	@Autowired
	private AdherentStatisticService adherentStatisticService;
	
	@Autowired
	private ShowAllAdherentsService showAllAdherentsService;
	
	
	private class StatisticsAdherentLayout extends VerticalLayout {
		
		public StatisticsAdherentLayout load() {
			adherents = showAllAdherentsService.getAllAdherents();
			return this;
		}
		
		public StatisticsAdherentLayout layout() {	
			setMargin(true);
			for(Adherent adherent : adherents) {
				int numOfStudents = adherentStatisticService.getNumOfAdherents();
				Label label = new Label("<p><b>"+adherent.getNom()+"</b>"+" - "+numOfStudents+" Students "+"</p>",  ContentMode.HTML);
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
		statisticsLayout = new StatisticsAdherentLayout();
		return statisticsLayout.load().layout();
	}
}
