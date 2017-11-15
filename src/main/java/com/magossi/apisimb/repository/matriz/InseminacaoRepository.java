package com.magossi.apisimb.repository.matriz;

import com.magossi.apisimb.domain.matriz.Inseminacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by RafaelMq on 04/11/2016.
 */
public interface InseminacaoRepository extends JpaRepository<Inseminacao, Long> {

    List<Inseminacao> findByStatus(Boolean status);

    @Query("select i from Inseminacao i where i.matriz = ?1  and i.status = true")
    List<Inseminacao> buscarPorMatriz(String id);

    List<Inseminacao> findByMatriz(String id);
}
