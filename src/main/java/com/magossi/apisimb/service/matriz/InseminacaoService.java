package com.magossi.apisimb.service.matriz;

import com.magossi.apisimb.domain.matriz.Inseminacao;
import com.magossi.apisimb.repository.matriz.InseminacaoRepository;
import com.magossi.apisimb.service.exceptions.FazendaExistenteException;
import com.magossi.apisimb.service.exceptions.FazendaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by RafaelMq on 04/11/2016.
 */

@SuppressWarnings("ALL")
@Service
public class InseminacaoService {

    @Autowired
    InseminacaoRepository inseminacaoRepository;


    public Inseminacao salvar(Inseminacao inseminacao){
        if(inseminacao.getIdInseminacao() != null){

            Inseminacao i = inseminacaoRepository.findOne(inseminacao.getIdInseminacao());
            if(i != null){
                throw new FazendaExistenteException("Inseminação já Existe");
            }
        }
        return inseminacaoRepository.save(inseminacao);
    }

    public List<Inseminacao> listar(){
        List<Inseminacao> inseminacoes = inseminacaoRepository.findAll();

        if(inseminacoes==null){
            throw new FazendaNaoEncontradaException("Lista de Inseminações não Encontrada");
        }
        return inseminacoes;
    }
}
