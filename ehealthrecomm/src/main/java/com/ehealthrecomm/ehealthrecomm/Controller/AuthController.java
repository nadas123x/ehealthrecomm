package com.ehealthrecomm.ehealthrecomm.Controller;

import com.ehealthrecomm.ehealthrecomm.Model.ERole;
import com.ehealthrecomm.ehealthrecomm.Model.Role;
import com.ehealthrecomm.ehealthrecomm.Model.User;
import com.ehealthrecomm.ehealthrecomm.Repository.RoleRepository;
import com.ehealthrecomm.ehealthrecomm.Repository.UserRepository;
import com.ehealthrecomm.ehealthrecomm.payload.request.LoginRequest;
import com.ehealthrecomm.ehealthrecomm.payload.request.SignUpRequest;
import com.ehealthrecomm.ehealthrecomm.payload.response.JwtResponse;
import com.ehealthrecomm.ehealthrecomm.payload.response.MessageResponse;
import com.ehealthrecomm.ehealthrecomm.security.JwtUtil;
import com.ehealthrecomm.ehealthrecomm.security.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		String jwtToken = jwtUtil.generateJwtToken(authenticate);

		UserDetailsImpl userDetails = (UserDetailsImpl)authenticate.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(userDetails.getId(), jwtToken, userDetails.getUsername(), userDetails.getEmail(), roles));
	}






	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("User With same Username Already Exist"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email is Already Registerd"));
		}

		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();

		Set<Role> roles = new HashSet<Role>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error! No role Found"));
			roles.add(userRole);
		}else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}
}
