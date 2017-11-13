package com.magossi.apisimb.resources.matriz;

import com.magossi.apisimb.domain.matriz.Resultado;
import com.magossi.apisimb.service.matriz.ResultadoService;
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
@RequestMapping("/resultado")
public class ResultadoResources {

    @Autowired
    public ResultadoService resultadoService;

    @RequestMapping(method =  RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Resultado resultado){
        resultado = resultadoService.salvar(resultado);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(resultado.getIdResultado()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Resultado>> listar(){

        List<Resultado> resultados = resultadoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(resultados);
    }
}
