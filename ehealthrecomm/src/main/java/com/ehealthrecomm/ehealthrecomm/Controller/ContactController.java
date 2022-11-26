package com.ehealthrecomm.ehealthrecomm.Controller;


import com.ehealthrecomm.ehealthrecomm.Model.Contact;
import com.ehealthrecomm.ehealthrecomm.Service.ContactServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ContactController {

    @Autowired
    ContactServices contactservice;

    @GetMapping("/getall")
    public Iterable <Contact> getContacts(){
        return contactservice.listAll();
    }
    @PostMapping(value = "/contact")
    private long saveBook(@RequestBody Contact contact)
    {
        contactservice.save(contact);

        return  contact.getId();
    }

}
