package br.ucsal.fitsys.fitsys.repositories;

import br.ucsal.fitsys.fitsys.domain.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface PresencaRepository extends JpaRepository<Presenca, Integer> {

    @Query("from Presenca p where not(p.data < :inicio and p.data > :fim) and p.aluno.id = :id")
    List<Presenca> findBetween(@Param("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate comeco, @Param("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate termino, @Param("id") Integer id);
}
