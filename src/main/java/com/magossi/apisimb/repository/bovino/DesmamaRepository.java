package com.magossi.apisimb.repository.bovino;

import com.magossi.apisimb.domain.bovino.Desmama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;

public interface DesmamaRepository extends JpaRepository<Desmama, Long> {

    @Query("select d from Desmama d where d.idBovino= ?1  ")
    List<Desmama> buscarPorBovino(Long id);

    @Query("select d from Desmama d where d.idFichaMatriz = ?1  and d.status = true")
    List<Desmama> buscarPorMatriz(Long id);
}

