package com.fosagri.ui.commonns;

import com.fosagri.ui.demande.DemandeLayoutFactory;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.fosagri.navigator.UniverseNavigator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;


@SpringUI(path=UniversMainUI.NAME)
@Title("F O S - A G R I")
@Theme("valo")
public class UniversMainUI extends UI {
	
	public static final String NAME="/ui";
	private Panel changeTab = new Panel();
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private UniversMenuFactory universMenuFactory;
	
	@Autowired
	private UniversLogoLayoutFactory universLogoLayoutFactory;

	@Autowired
	private SpringViewProvider viewProvider;
	
	@Override
	protected void init(VaadinRequest request) {
		changeTab.setHeight("100%");
		
		VerticalLayout rootLayout = new VerticalLayout();
		rootLayout.setSizeFull();
		rootLayout.setMargin(true);
		
		Panel contentPanel = new Panel();
		contentPanel.setWidth("100%");
		contentPanel.setHeight("100%");
		
		Panel logoPanel = new Panel();
		logoPanel.setWidth("100%");
		logoPanel.setHeightUndefined();
		
		HorizontalLayout uiLayout = new HorizontalLayout();
		uiLayout.setSizeFull();
		uiLayout.setMargin(true);
		
		Component logo = universLogoLayoutFactory.createComponent();
		Component menu = universMenuFactory.createComponent();
		uiLayout.addComponent(menu);
		uiLayout.addComponent(changeTab);
		
		uiLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);
		uiLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
		
		uiLayout.setExpandRatio(menu, 1);
		uiLayout.setExpandRatio(changeTab, 4);
		
		logoPanel.setContent(logo);
		contentPanel.setContent(uiLayout);
		
		//rootLayout.addComponentAttachListener(logoPanel);
		rootLayout.addComponent(logoPanel);
		rootLayout.addComponent(contentPanel);
		rootLayout.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
		rootLayout.setComponentAlignment(logoPanel, Alignment.TOP_CENTER);
		rootLayout.setExpandRatio(contentPanel, 2);
		
		initNavigator();
		
		
		setContent(rootLayout);
	}
	
	private void initNavigator() {
		UniverseNavigator navigator = new UniverseNavigator(this, changeTab);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
		navigator.addProvider(viewProvider);
		navigator.navigateTo(DemandeLayoutFactory.NAME);
		
	}

}
