package br.ucsal.fitsys.fitsys.repositories;

import br.ucsal.fitsys.fitsys.domain.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Integer> {

    @Transactional(readOnly = true)
    Optional<Treino> findByNomeIgnoreCase(String nome);

}
