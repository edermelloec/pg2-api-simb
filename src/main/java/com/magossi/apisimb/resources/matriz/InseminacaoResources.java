package com.magossi.apisimb.resources.matriz;

import com.magossi.apisimb.domain.matriz.Inseminacao;
import com.magossi.apisimb.service.matriz.InseminacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by RafaelMq on 04/11/2016.
 */

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/inseminacao")
public class InseminacaoResources {


    @Autowired
    public InseminacaoService inseminacaoService;

    @RequestMapping(method =  RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Inseminacao inseminacao){
        inseminacao = inseminacaoService.salvar(inseminacao);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(inseminacao.getIdInseminacao()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Inseminacao>> listar(){

        List<Inseminacao> inseminacoes = inseminacaoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(inseminacoes);
    }
}
