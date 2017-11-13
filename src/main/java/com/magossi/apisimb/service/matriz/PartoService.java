package com.magossi.apisimb.service.matriz;

import com.magossi.apisimb.domain.bovino.Bovino;
import com.magossi.apisimb.domain.bovino.Desmama;
import com.magossi.apisimb.domain.bovino.Peso;
import com.magossi.apisimb.domain.matriz.FichaTouro;
import com.magossi.apisimb.domain.matriz.Inseminacao;
import com.magossi.apisimb.domain.matriz.Parto;
import com.magossi.apisimb.domain.matriz.Resultado;
import com.magossi.apisimb.repository.bovino.BovinoRepository;
import com.magossi.apisimb.repository.bovino.PesoRepository;
import com.magossi.apisimb.repository.matriz.InseminacaoRepository;
import com.magossi.apisimb.repository.matriz.PartoRepository;
import com.magossi.apisimb.repository.matriz.ResultadoRepository;
import com.magossi.apisimb.service.exceptions.FazendaExistenteException;
import com.magossi.apisimb.service.exceptions.FazendaNaoEncontradaException;
import com.magossi.apisimb.service.exceptions.GestaoException;
import com.magossi.apisimb.testes.GestaoRespositoryBanco;
import com.magossi.apisimb.testes.Morte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 04/11/2016.
 */

@SuppressWarnings("ALL")
@Service
public class PartoService {

    @Autowired
    PartoRepository partoRepository;
    GestaoRespositoryBanco grb = new GestaoRespositoryBanco();


    public Parto salvar(Parto parto) {
        if (parto.getIdParto() != null) {

            Parto p = partoRepository.findOne(parto.getIdParto());
            if (p != null) {
                throw new FazendaExistenteException("Intervalo de Parto já Existe");
            }
        }
        return partoRepository.save(parto);
    }
    public List<Parto> listar() {
        List<Parto> partos = partoRepository.findAll();

        if (partos == null) {
            throw new GestaoException("Lista de Intervalos de Parto não Encontrada");
        }
        return partos;
    }


    public Parto salvarParto(Parto parto) {
        Parto p;
        p = partoRepository.save(parto);

        if (p==null){
            throw new GestaoException("Parto não foi salvo");
        }
        return p;
    }
    public void salvarQtdParto(Integer id){

        grb.salvarQtdParto(id);

    }

}
