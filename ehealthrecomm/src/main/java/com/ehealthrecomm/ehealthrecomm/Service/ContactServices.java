package com.ehealthrecomm.ehealthrecomm.Service;

import com.ehealthrecomm.ehealthrecomm.Model.Contact;
import com.ehealthrecomm.ehealthrecomm.Repository.ContactRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServices {


    @Autowired
    private ContactRepository contactrepo;

    public Iterable<Contact> listAll() {
        return this.contactrepo.findAll();
    }

    public void save(Contact contact){

        contactrepo.save(contact);
    }
}
