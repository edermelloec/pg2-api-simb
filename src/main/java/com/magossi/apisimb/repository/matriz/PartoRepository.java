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
import java.util.List;

/**
 * Created by RafaelMq on 04/11/2016.
 */
public interface PartoRepository extends JpaRepository<Parto, Long> {


}
