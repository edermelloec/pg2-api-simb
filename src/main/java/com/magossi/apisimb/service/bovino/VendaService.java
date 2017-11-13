package com.magossi.apisimb.service.bovino;


import com.magossi.apisimb.domain.bovino.Morto;
import com.magossi.apisimb.domain.bovino.Venda;
import com.magossi.apisimb.repository.bovino.VendaRepository;
import com.magossi.apisimb.service.exceptions.BovinoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class VendaService {
    @Autowired
    VendaRepository vendaRepository;


    public List<Venda> buscarTodos(){
        List<Venda> venda = vendaRepository.findAll();

        if(venda==null){
            throw new BovinoNaoEncontradoException("Lista de Bovinos não Encontrada");
        }
        return venda;
    }
}
