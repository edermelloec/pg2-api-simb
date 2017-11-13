package com.magossi.apisimb.resources.matriz;

import com.magossi.apisimb.domain.bovino.Bovino;
import com.magossi.apisimb.domain.bovino.Desmama;
import com.magossi.apisimb.domain.bovino.Peso;
import com.magossi.apisimb.domain.matriz.FichaTouro;
import com.magossi.apisimb.domain.matriz.Inseminacao;
import com.magossi.apisimb.domain.matriz.Parto;
import com.magossi.apisimb.domain.matriz.Resultado;
import com.magossi.apisimb.service.matriz.PartoService;
import com.magossi.apisimb.testes.Morte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by RafaelMq on 04/11/2016.
 */

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/parto")
public class PartoResources {

    @Autowired
    PartoService partoService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Parto parto) {
        parto = partoService.salvar(parto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(parto.getIdParto()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Parto>> listar() {

        List<Parto> partos = partoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(partos);
    }

    @RequestMapping(value = "/salvar/parto", method = RequestMethod.POST)
    public ResponseEntity<Void> salvarParto(@RequestBody Parto parto) {


        parto = partoService.salvarParto(parto);
        partoService.salvarQtdParto(parto.getIdFichaMatriz().intValue());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(parto.getIdParto()).toUri();

        return ResponseEntity.created(uri).build();

    }


}
