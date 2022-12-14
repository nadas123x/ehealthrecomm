package com.ehealthrecomm.ehealthrecomm.security;

import com.ehealthrecomm.ehealthrecomm.Model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

	private long id;
	private String username;
	private String email;
	@JsonIgnore //to mark a property or list of properties to be ignored.
	private String password;

	private Collection<? extends GrantedAuthority> authority; // autorités (permission or right),Those "permissions" are (normally) expressed as strings (with the getAuthority() method)

	public UserDetailsImpl(long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authority) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authority = authority;
	}
	//Stream une séquence d'éléments sur laquelle on peut effectuer un groupe d'opérations de manière séquentielle ou parallèle

	public static UserDetailsImpl build(User user) {
		List<SimpleGrantedAuthority> authority = user.getRoles().stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authority);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authority;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
