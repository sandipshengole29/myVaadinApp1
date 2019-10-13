package com.myVaadinApp1.mainUi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myVaadinApp1.contactView.ContactsView;
import com.myVaadinApp1.defaultView.ErrorView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path = MainUI.APP_ROOT)
@SpringViewDisplay
public class MainUI extends UI implements ViewDisplay {
	private static Logger LOGGER = LoggerFactory.getLogger(MainUI.class);
	
	static final String APP_ROOT = "/contactVaadinDemo";

	private static final String CONTACTS_VIEW = "Contacts";

	private Panel springViewDisplay;

	private static final long serialVersionUID = 4967383498113318791L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		LOGGER.info("===== inside init of MainUI =====");
		
		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		setContent(root);

		final CssLayout navigationButtons = new CssLayout();

		navigationButtons.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		navigationButtons.addComponent(createNavigationButton(CONTACTS_VIEW, ContactsView.VIEW_NAME));
	
		root.addComponent(navigationButtons);

		springViewDisplay = new Panel();
		springViewDisplay.setSizeFull();
		root.addComponent(springViewDisplay);
		root.setExpandRatio(springViewDisplay, 1.0f);

		getNavigator().setErrorView(ErrorView.class);
	}

	private Button createNavigationButton(String caption, final String viewName) {
		LOGGER.info("===== inside createNavigationButton of MainUI =====");
		
		Button button = new Button(caption);
		button.addStyleName(ValoTheme.BUTTON_SMALL);

		button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
		return button;
	}

	@Override
	public void showView(View view) {
		LOGGER.info("===== inside showView of MainUI =====");
		springViewDisplay.setContent((Component) view);
	}
}