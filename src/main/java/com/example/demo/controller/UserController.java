package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.RefreshToken;
import com.example.demo.entities.User;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RefreshTokenRequest;
import com.example.demo.response.RefreshTokenResponse;
import com.example.demo.service.LoginService;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	private static final String HttpStatus = null;

	@Autowired
	UserService userService;

	@Autowired
	LoginService loginService;

	@Autowired
	RefreshTokenService refreshTokenService;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		log.info("criando um novo usuário com as infos : [{}]", user);
		return this.userService.createNewUser(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
		log.info("usuário [{}] fazendo login", loginRequest.getUsername());
		return loginService.login(loginRequest);
	}

	@RequestMapping(value = "/refreshtoken", method = RequestMethod.POST)
	public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenRequest request) {

		log.info("Solicitação de criação de refresh token para o token [{}]", request);
		return refreshTokenService.findByToken(request.getRefreshToken()).map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser).map(user -> {
					String token = refreshTokenService.generateTokenFromUsername(user.getName());
					log.info("refresh token gerado com sucesso [{}]", token);
					return ResponseEntity.ok(new RefreshTokenResponse(token, request.getRefreshToken(), "Bearer"));
				}).orElseThrow(() -> new CredentialsExpiredException(request.getRefreshToken()));
	}

}
