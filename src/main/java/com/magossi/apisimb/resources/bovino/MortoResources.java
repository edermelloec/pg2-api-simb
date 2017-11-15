package com.magossi.apisimb.resources.bovino;

import com.magossi.apisimb.domain.bovino.Morto;
import com.magossi.apisimb.service.bovino.MortoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SuppressWarnings("ALL")
@RestController
@RequestMapping("/morto")
public class MortoResources {

    @Autowired
    MortoService mortoService;

    @RequestMapping(value = "/{busca}/{tipoBusca}", method = RequestMethod.GET)
    public ResponseEntity<List<Morto>> listar(@PathVariable("busca")String busca, @PathVariable("tipoBusca")String tipoBusca){

        List<Morto> mortos =null;

        if("todos".equals(busca)){
            mortos = mortoService.buscarTodos();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mortos);
    }
}
