package com.magossi.apisimb.service.bovino;

import com.magossi.apisimb.domain.bovino.Morto;
import com.magossi.apisimb.repository.bovino.MortoRepository;
import com.magossi.apisimb.service.exceptions.BovinoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class MortoService {
    @Autowired
    MortoRepository mortoRepository;

    public List<Morto> buscarTodos(){
        List<Morto> morte = mortoRepository.findAll();

        if(morte==null){
            throw new BovinoNaoEncontradoException("Lista de Bovinos não Encontrada");
        }
        return morte;
    }

}
