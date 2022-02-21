package br.ucsal.fitsys.fitsys.repositories;

import br.ucsal.fitsys.fitsys.domain.Endereco;
import br.ucsal.fitsys.fitsys.domain.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
