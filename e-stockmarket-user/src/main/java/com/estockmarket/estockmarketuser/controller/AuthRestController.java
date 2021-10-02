package com.estockmarket.estockmarketuser.controller;

import com.estockmarket.estockmarketuser.models.ERole;
import com.estockmarket.estockmarketuser.models.Role;
import com.estockmarket.estockmarketuser.models.User;
import com.estockmarket.estockmarketuser.payload.request.LoginRequest;
import com.estockmarket.estockmarketuser.payload.request.SignupRequest;
import com.estockmarket.estockmarketuser.payload.response.JwtResponse;
import com.estockmarket.estockmarketuser.payload.response.MessageResponse;
import com.estockmarket.estockmarketuser.repository.RoleRepository;
import com.estockmarket.estockmarketuser.repository.UserRepository;
import com.estockmarket.estockmarketuser.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthRestController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		User user = userRepository.findByUsername(loginRequest.getUsername()).get();
		String token = jwtUtil.generateToken(loginRequest.getUsername());

		List<String> roles = user.getRoles().stream()
				.map(item -> item.getName().name())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(token,
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				roles));
	}

	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signUpRequest) {
		// Persist user to some persistent storage
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		System.out.println("Info saved...");

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "manager":
						Role modRole = roleRepository.findByName(ERole.ROLE_MANAGER)
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
