package com.magossi.apisimb.service.bovino;

import com.magossi.apisimb.domain.bovino.Desmama;
import com.magossi.apisimb.domain.bovino.Morto;
import com.magossi.apisimb.repository.bovino.DesmamaRepository;
import com.magossi.apisimb.service.exceptions.BovinoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class DesmamaService {

    @Autowired
    DesmamaRepository desmamaRepository;

    public List<Desmama> buscarTodos(){
        List<Desmama> desmama = desmamaRepository.findAll();

        if(desmama==null){
            throw new BovinoNaoEncontradoException("Lista de Bovinos não Encontrada");
        }
        return desmama;
    }
}
