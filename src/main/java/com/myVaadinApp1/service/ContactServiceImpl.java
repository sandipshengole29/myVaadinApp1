/**
 * 
 */
package com.myVaadinApp1.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myVaadinApp1.entity.Contact;
import com.myVaadinApp1.repository.ContactRepository;

/**
 * @author sandi
 *
 */

@Service
public class ContactServiceImpl implements ContactService {
	private Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Contact> getContacts() {
		LOGGER.info("=== getContacts ===");
		return contactRepository.findAll();
	}

	@Override
	public Contact save(Contact contact) {
		LOGGER.info("=== save ===");
		contactRepository.save(contact);
		return contact;
	}

	@Override
	public void delete(Contact contact) {
		LOGGER.info("=== delete ===");
		contactRepository.delete(contact);
	}
}
