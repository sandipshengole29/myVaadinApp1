package com.myVaadinApp1.contactView;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myVaadinApp1.entity.Contact;
import com.myVaadinApp1.service.ContactService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView(name = ContactsView.VIEW_NAME)
public class ContactsView extends VerticalLayout implements View {
	private static Logger LOGGER = LoggerFactory.getLogger(ContactsView.class);

	private static final long serialVersionUID = -3089511061636116441L;

	public static final String VIEW_NAME = "contacts";

	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactDetailView contactDetail;

	Button newBtn = new Button("New Contact");
	Button refreshBtn = new Button("Refresh");
	CssLayout actions = new CssLayout(newBtn, refreshBtn);
	Grid<Contact> grid = new Grid<>(Contact.class);

	@PostConstruct
	void init() {
		LOGGER.info("======= inside init of ContactsView =======");
		
		newBtn.addClickListener(e -> contactDetail.showDetail(new Contact()));
		refreshBtn.addClickListener(e -> refresh());

		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setColumns("id", "firstName", "lastName", "email", "phoneNumber");
		grid.setItems(contactService.getContacts());
		
		grid.asSingleSelect().addValueChangeListener(e -> {
			contactDetail.showDetail(e.getValue());
		});

		addComponent(grid);
		addComponent(actions);
		addComponent(contactDetail);

		contactDetail.setChangeHandler(() -> {
			contactDetail.setVisible(false);
			refresh();
		});

	}

	public final void refresh() {
		LOGGER.info("======= inside refresh of ContactsView =======");
		grid.setItems(contactService.getContacts());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// This view is constructed in the init() method()
	}
}
