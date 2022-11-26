package com.ehealthrecomm.ehealthrecomm.Controller;


import com.ehealthrecomm.ehealthrecomm.Model.Proposition;
import com.ehealthrecomm.ehealthrecomm.Service.PropositionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PropositionController {

    @Autowired
    PropositionServices Propositionservice;

    @GetMapping("/getallprops")
    public Iterable <Proposition> getPropositions(){
        return Propositionservice.listAll();
    }
    @PostMapping(value = "/Proposition")
    private long saveBook(@RequestBody Proposition Proposition)
    {
        Propositionservice.save(Proposition);

        return  Proposition.getId();
    }

}
