/**
 * 
 */
package com.myVaadinApp1.service;

import java.util.List;

import com.myVaadinApp1.entity.Contact;

/**
 * @author sandi
 *
 */
public interface ContactService {

	public List<Contact> getContacts();

	public Contact save(Contact contact);

	public void delete(Contact contact);

}
