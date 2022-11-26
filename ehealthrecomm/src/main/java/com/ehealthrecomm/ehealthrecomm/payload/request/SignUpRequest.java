package com.ehealthrecomm.ehealthrecomm.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpRequest {


	@Size(min = 3, max = 20)
	private String username;


	@Size(max = 50)
	private String email;


	@Size(min = 6, max = 40)
	private String password;

	private Set<String> role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

}
