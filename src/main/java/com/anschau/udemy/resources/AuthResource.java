//ESTE CARA AQUI NÃO FUNCIONA AINDA PORQUE EU NÃO ADICIONO TEMPO DE EXPIRAÇÃO NOS TOKEN POR CAUSA DA DATA
/*package com.anschau.udemy.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anschau.udemy.security.JWTUtil;
import com.anschau.udemy.security.UserSS;
import com.anschau.udemy.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer "+ token);
		return ResponseEntity.noContent().build();
	}
}
*/