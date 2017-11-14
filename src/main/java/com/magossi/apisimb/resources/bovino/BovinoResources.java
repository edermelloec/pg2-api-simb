package com.magossi.apisimb.resources.bovino;

import com.magossi.apisimb.domain.bovino.Bovino;

import com.magossi.apisimb.domain.bovino.Ecc;
import com.magossi.apisimb.domain.bovino.Peso;
import com.magossi.apisimb.domain.matriz.FichaMatriz;
import com.magossi.apisimb.service.bovino.BovinoService;
import com.magossi.apisimb.service.bovino.EccService;
import com.magossi.apisimb.testes.GestaoRespositoryBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 15/05/2016.
 */

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/bovino")
public class BovinoResources {


    @Autowired
    BovinoService bovinoService ;

    @Autowired
    EccService eccService;


    GestaoRespositoryBanco grb = new GestaoRespositoryBanco();



   // ******************************** METODOS PUT *******************************************************

    @RequestMapping(method =  RequestMethod.PUT)
    public ResponseEntity<Void> alterar(@RequestBody Bovino bovino){

        bovino = grb.alterarBovino(bovino);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(bovino.getIdBovino()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // ******************************** METODOS POST *******************************************************

    @RequestMapping(method =  RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Bovino bovino){
        bovino = bovinoService.salvar(bovino);
        grb.alterarBovino(bovino.getIdBovino().intValue());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(bovino.getIdBovino()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}/ecc", method =  RequestMethod.POST)
    public ResponseEntity<Void> salvarEccBovino(@PathVariable("id") Long id, @RequestBody Ecc ecc){
        Bovino bovino = bovinoService.buscarId(id);
        bovino.getEcc().add(ecc);
        bovino = bovinoService.alterar(bovino);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(bovino.getIdBovino()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}/peso", method =  RequestMethod.POST)
    public ResponseEntity<Void> salvarPesoBovino(@PathVariable("id") Long id, @RequestBody Peso peso){
        Bovino bovino = bovinoService.buscarId(id);
        bovino.getPeso().add(peso);
        bovino = bovinoService.alterar(bovino);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(bovino.getIdBovino()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}/fichamatriz", method =  RequestMethod.POST)
    public ResponseEntity<Void> salvarFichaMatrizBovino(@PathVariable("id") Long id, @RequestBody FichaMatriz fichaMatriz){
        Bovino bovino = bovinoService.buscarId(id);

        bovino.setFichaMatriz(fichaMatriz);
        bovino = bovinoService.alterar(bovino);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(bovino.getIdBovino()).toUri();

        return ResponseEntity.created(uri).build();
    }


    // ******************************** METODOS GET *******************************************************

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Bovino>> listar(){

        List<Bovino> bovino = bovinoService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(bovino);
    }
    @RequestMapping(value = "/ativos", method = RequestMethod.GET)
    public ResponseEntity<List<Bovino>> listarAtivos(){

        List<Bovino> bovino = bovinoService.buscarTodosAtivos();
        return ResponseEntity.status(HttpStatus.OK).body(bovino);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bovino> buscarBovinoPorId(@PathVariable("id") Long id){   //? encapsula qualquer tipo de objeto

        Bovino bovino = bovinoService.buscarId(id);
        return ResponseEntity.status(HttpStatus.OK).body(bovino);
    }

    @RequestMapping(value = "/tag/{tag}", method = RequestMethod.GET)
    public ResponseEntity<Bovino> buscarBovinoPorTag(@PathVariable("tag")String tag){

        Bovino bovino = bovinoService.buscarTag(tag);
        return ResponseEntity.status(HttpStatus.OK).body(bovino);

    }

    @RequestMapping(value = "/nome/{busca}/{tipoBusca}", method = RequestMethod.GET)
    public ResponseEntity<List<Bovino>> buscarBovinoPorNome(@PathVariable("busca")String busca,@PathVariable("tipoBusca")String tipoBusca){
        List<Bovino> bovino = null;

        if("todos".equals(busca)){
            bovino = bovinoService.buscarTodosAtivos();
        }else if ("nome".equals(tipoBusca)){
            bovino = bovinoService.buscarNomeBovino("%"+busca+"%");
        }else if ("raca".equals(tipoBusca)){
            bovino = bovinoService.buscarPorRaca("%"+busca+"%");
        }else if ("fazenda".equals(tipoBusca)){
            bovino = bovinoService.buscarPorFazenda("%"+busca+"%");
        }else if ("dataNascimento".equals(tipoBusca)){
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date data = formato.parse(busca);
                bovino = bovinoService.buscarPorData(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


        return ResponseEntity.status(HttpStatus.OK).body(bovino);

    }

    @RequestMapping(value = "/mae/{mae}", method = RequestMethod.GET)
    public ResponseEntity<List<Bovino>> buscarBovinoPorMae(@PathVariable("mae")String mae){

        List<Bovino> bovino = bovinoService.buscarMae(mae);
        return ResponseEntity.status(HttpStatus.OK).body(bovino);

    }






}
