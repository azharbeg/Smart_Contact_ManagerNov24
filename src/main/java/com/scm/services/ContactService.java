package com.scm.services;

import com.scm.entities.Contact;
import com.scm.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactService {

    // save contacts here
    Contact save(Contact contact);

    // update contact
    Contact update();

    //get contacts
    List<Contact> getAll();

    //get contact by id
    Contact getById(String id);

    //delete contat

    void delete (String id);

    //search contact

    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByEmail(String emailKeyword,int size, int page, String sortBy, String order,User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword,int size, int page, String sortBy, String order,User user);


    // get contact by userId
    List<Contact> getByUserId(String userId);

    List<Contact> getByUser(User user);

    Page<Contact>getByUser(User user, int page, int size,String sortField, String sortDirection);
}

