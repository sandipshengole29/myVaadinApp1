package com.myVaadinApp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myVaadinApp1.entity.Contact;

@Repository(value = "contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
