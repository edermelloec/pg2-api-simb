package com.magossi.apisimb.service.bovino;

import com.magossi.apisimb.domain.bovino.Bovino;
import com.magossi.apisimb.repository.bovino.*;
import com.magossi.apisimb.service.exceptions.BovinoExistenteException;
import com.magossi.apisimb.service.exceptions.BovinoNaoEncontradoException;
import com.magossi.apisimb.service.exceptions.BovinoNaoExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by RafaelMq on 20/05/2016.
 */

@SuppressWarnings("ALL")
@Service
public class BovinoService {

    @Autowired
    BovinoRepository bovinoRepository;

    @Autowired
    FazendaRepository fazendaRepository;

    @Autowired
    PelagemRepository pelagemRepository;

    @Autowired
    ProprietarioRepository proprietarioRepository;

    @Autowired
    RacaRepository racaRepository;

    @Autowired
    EccRepository eccRepository;


    public Bovino salvar(Bovino bovino){
        if(bovino.getTag() != null){

            Bovino b = bovinoRepository.findByTag(bovino.getTag());
            if(b != null){
                throw new BovinoExistenteException("Bovino já Existe");
            }
        }

        return bovinoRepository.save(bovino);

    }


    public Bovino alterar(Bovino bovino){

        return bovinoRepository.save(bovino);

    }



    public Bovino buscarTag(String tag){
        Bovino bovino = bovinoRepository.findByTag(tag);

        if(bovino==null){
            throw new BovinoNaoExistenteException("Bovino não Existe");
        }

        return bovino;
    }

    public List<Bovino> buscarNomeBovino(String nome){
        List<Bovino> bovino = bovinoRepository.findByNomeBovino(nome);

        if(bovino==null){
            throw new BovinoNaoExistenteException("Bovino não Existe");
        }

        return bovino;
    }
    public List<Bovino> buscarMatriz(String nome){
        List<Bovino> bovino = bovinoRepository.findByNomeBovino(nome);

        if(bovino==null){
            throw new BovinoNaoExistenteException("Bovino não Existe");
        }

        return bovino;
    }

    public List<Bovino> buscarMae(String mae){
        List<Bovino> bovino = bovinoRepository.findByMaeContaining(mae);

        if(bovino==null){
            throw new BovinoNaoExistenteException("Bovino não Existe");
        }

        return bovino;
    }

    public Bovino buscarId(Long id){
        Bovino bovino = bovinoRepository.findOne(id);

        if(bovino==null){
            throw new BovinoNaoEncontradoException("Bovino não Encontrado");
        }
        return bovino;
    }

    public List<Bovino> buscarTodos(){
        List<Bovino> bovino = bovinoRepository.findAll();

        if(bovino==null){
            throw new BovinoNaoEncontradoException("Lista de Bovinos não Encontrada");
        }
        return bovino;
    }

    public List<Bovino> buscarTodosAtivos(){
        List<Bovino> bovino = bovinoRepository.findByStatusOrderByNomeBovino(true);

        if(bovino==null){
            throw new BovinoNaoEncontradoException("Lista de Bovinos não Encontrada");
        }
        return bovino;
    }





//
//
//
//
//
//
//
//
//
//
//    public List<Pelagem> buscarPelagens(){
//        List<Pelagem> pelagens = pelagemRepository.findAll();
//
//        if(pelagens==null){
//            throw new BovinoNaoEncontradoException("Lista de Pelagens não Encontrada");
//        }
//        return pelagens;
//    }
//
//    public List<Proprietario> buscarProprietarios(){
//        List<Proprietario> proprietarios = proprietarioRepository.findAll();
//
//        if(proprietarios==null){
//            throw new BovinoNaoEncontradoException("Lista de Prorietarios não Encontrada");
//        }
//        return proprietarios;
//    }
//
//    public List<Raca> buscarRacas(){
//        List<Raca> racas = racaRepository.findAll();
//
//        if(racas==null){
//            throw new BovinoNaoEncontradoException("Lista de Racas não Encontrada");
//        }
//        return racas;
//    }


}
