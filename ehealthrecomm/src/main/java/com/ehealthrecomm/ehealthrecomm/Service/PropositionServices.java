package com.ehealthrecomm.ehealthrecomm.Service;

import com.ehealthrecomm.ehealthrecomm.Model.Proposition;
import com.ehealthrecomm.ehealthrecomm.Repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropositionServices {


    @Autowired
    private PropositionRepository Propositionrepo;

    public Iterable<Proposition> listAll() {
        return this.Propositionrepo.findAll();
    }

    public void save(Proposition Proposition){

        Propositionrepo.save(Proposition);
    }
}
