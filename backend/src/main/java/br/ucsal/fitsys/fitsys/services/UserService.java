package br.ucsal.fitsys.fitsys.services;

import br.ucsal.fitsys.fitsys.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /**
     * Essa classe foi criada para poder retornar o usuário logado no sistema
     * @return UserSS
     */

    @Autowired
    AlunoService alunoService;

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
