package com.ehealthrecomm.ehealthrecomm.Repository;

import com.ehealthrecomm.ehealthrecomm.Model.Contact;
import com.ehealthrecomm.ehealthrecomm.Model.Proposition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PropositionRepository extends CrudRepository<Proposition,Long> {

}