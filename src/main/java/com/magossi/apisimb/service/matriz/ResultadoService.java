package com.magossi.apisimb.service.matriz;

import com.magossi.apisimb.domain.matriz.Resultado;
import com.magossi.apisimb.repository.matriz.ResultadoRepository;
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
public class ResultadoService {

    @Autowired
    ResultadoRepository m_resultadoRepository;


    public Resultado salvar(Resultado resultado){
        if(resultado.getIdResultado() != null){

            Resultado r = m_resultadoRepository.findOne(resultado.getIdResultado());
            if(r != null){
                throw new FazendaExistenteException("Resultado já Existe");
            }
        }
        return m_resultadoRepository.save(resultado);
    }

    public List<Resultado> listar(){
        List<Resultado> resultados = m_resultadoRepository.findAll();

        if(resultados==null){
            throw new FazendaNaoEncontradaException("Lista de Resultados não Encontrada");
        }
        return resultados;
    }
}
