package br.ucsal.fitsys.fitsys.repositories;

import br.ucsal.fitsys.fitsys.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    //Não deixa fazer alteração
    @Transactional(readOnly = true)
    Aluno findByEmail(String email);

    //Altera o banco
    @Transactional
    void deleteAlunoByEmail(String email);

    @Transactional(readOnly = true)
    boolean existsByEmail(String email);

}
