package com.magossi.apisimb.repository.matriz;

import com.magossi.apisimb.domain.bovino.Bovino;
import com.magossi.apisimb.domain.matriz.Parto;
import com.magossi.apisimb.testes.ConexaoFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 04/11/2016.
 */
public interface PartoRepository extends JpaRepository<Parto, Long> {
    @Query("select p from Parto p where p.idFichaMatriz = ?1  ")
    List<Parto> buscarPorMatriz(Long id);

    @Query("select p from Parto p where p.dataParto between ?1 and ?2")
    List<Parto> buscarPorDataParto(Date data1, Date data2);

    @Query("select p from Parto p where upper(p.descricao) like upper(?1)")
    List<Parto> buscarPorDescricao(String touro);

    @Query("select p from Parto p where upper(p.status) like upper(?1)")
    List<Parto> buscarPorStatus(String touro);
}
