package com.magossi.apisimb.repository.bovino;

import com.magossi.apisimb.domain.bovino.*;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 15/05/2016.
 */
public interface BovinoRepository extends JpaRepository<Bovino, Long>{

        Bovino findByTag(String tag);


        default List<Bovino> findAllOrOrderByNomeBovino() {

                return null;
        }

        //Bovino findByNomeBovino(String nome);
        List<Bovino> findByNomeBovinoContainingAndStatus(String nome, Boolean status);
        List<Bovino> findByPai(String pai);
        List<Bovino> findByMaeContaining(String mae);
        List<Bovino> findByRaca(Raca raca);
        List<Bovino> findByPelagem(Pelagem pelagem);
        List<Bovino> findByFazenda(Fazenda fazenda);
        List<Bovino> findByProprietario(Proprietario proprietario);
        List<Bovino> findByDataNascimento(Date data);
        Bovino findByPeso(Peso peso);
        Bovino findByEcc(Ecc ecc);
        List<Bovino> findByStatusOrderByNomeBovino(Boolean status);





}