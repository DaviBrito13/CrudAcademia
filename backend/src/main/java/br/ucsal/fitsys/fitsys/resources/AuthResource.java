package br.ucsal.fitsys.fitsys.resources;

import br.ucsal.fitsys.fitsys.security.JWTUtil;
import br.ucsal.fitsys.fitsys.security.UserSS;
import br.ucsal.fitsys.fitsys.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
//    Endpoint criado para poder dar refresh no token

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer" + token);
        return ResponseEntity.noContent().build();
    }
}
