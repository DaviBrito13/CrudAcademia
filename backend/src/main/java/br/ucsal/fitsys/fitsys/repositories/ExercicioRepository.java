package br.ucsal.fitsys.fitsys.repositories;

import br.ucsal.fitsys.fitsys.domain.Aluno;
import br.ucsal.fitsys.fitsys.domain.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Integer> {

    //Não deixa fazer alteração
    @Transactional(readOnly = true)
    Exercicio findByTituloIgnoreCase(String titulo);


}
