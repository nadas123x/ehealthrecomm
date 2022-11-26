package com.ehealthrecomm.ehealthrecomm.security;

import com.ehealthrecomm.ehealthrecomm.Model.User;
import com.ehealthrecomm.ehealthrecomm.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserdetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional //Une transaction est définie par le respect de quatre propriétés désignées par l’acronyme ACID :
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found With Username" + username));

		return UserDetailsImpl.build(user);
	}

}
