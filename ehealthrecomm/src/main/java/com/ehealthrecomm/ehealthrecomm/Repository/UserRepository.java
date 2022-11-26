package com.ehealthrecomm.ehealthrecomm.Repository;

import com.ehealthrecomm.ehealthrecomm.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username); //jlui donne un username et il me retourne un user.

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
