package com.magossi.apisimb.service.matriz;

import com.magossi.apisimb.domain.bovino.Bovino;
import com.magossi.apisimb.domain.matriz.FichaMatriz;
import com.magossi.apisimb.domain.matriz.Inseminacao;
import com.magossi.apisimb.domain.tarefa.Tarefa;
import com.magossi.apisimb.repository.bovino.BovinoRepository;
import com.magossi.apisimb.repository.matriz.FichaMatrizRepository;
import com.magossi.apisimb.repository.matriz.InseminacaoRepository;
import com.magossi.apisimb.service.exceptions.EccNaoEncontradoException;
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

    @Autowired
    BovinoRepository bovinoRepository;

    @Autowired
    FichaMatrizRepository fichaMatrizRepository;


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
        List<Inseminacao> inseminacoes = inseminacaoRepository.findByStatus(true);

        if(inseminacoes==null){
            throw new FazendaNaoEncontradaException("Lista de Inseminações não Encontrada");
        }
        return inseminacoes;
    }
    public List<Inseminacao> buscarPorMatriz(String busca){
        List<Bovino> bovinos = bovinoRepository.buscarPorMatriz(busca);
        List<Inseminacao> inseminacaos;

        if(bovinos.size()!=0) {

            System.out.println("TEST123 ------------  : " +String.valueOf(bovinos.get(0).getFichaMatriz().getIdFichaMatriz()));
            inseminacaos = inseminacaoRepository.buscarPorMatriz(String.valueOf(bovinos.get(0).getFichaMatriz().getIdFichaMatriz()));
        }else{
            inseminacaos = inseminacaoRepository.findByStatus(true);
        }
        if(inseminacaos==null){
            throw new EccNaoEncontradoException("Lista de Tarefas não Encontrada");
        }
        return inseminacaos;
    }
}
