package br.ucsal.fitsys.fitsys.services;

import br.ucsal.fitsys.fitsys.domain.Aluno;
import br.ucsal.fitsys.fitsys.domain.Instrutor;
import br.ucsal.fitsys.fitsys.repositories.AlunoRepository;
import br.ucsal.fitsys.fitsys.repositories.InstrutorRepository;
import br.ucsal.fitsys.fitsys.security.UserSS;
import br.ucsal.fitsys.fitsys.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    /**
     * Essa classe foi gerada para retornar um UserSS com os dados do usuário logado
     */

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserSS userSS = null;

        Aluno aluno = alunoRepository.findByEmail(email);

        if (aluno != null) {
            userSS = new UserSS(aluno.getId(), aluno.getEmail(), aluno.getSenha(), aluno.getPerfil());
        } else {
            Instrutor instrutor = instrutorRepository.findByEmail(email);
            if (instrutor != null)
                userSS = new UserSS(instrutor.getId(), instrutor.getEmail(), instrutor.getSenha(), instrutor.getPerfil());
        }

        if (userSS == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Email: " + email + " , Tipo: " + UserSS.class.getName());
        }

        return userSS;
    }
}
