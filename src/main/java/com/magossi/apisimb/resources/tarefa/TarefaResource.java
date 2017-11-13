package com.magossi.apisimb.resources.tarefa;

import com.magossi.apisimb.domain.bovino.Bovino;
import com.magossi.apisimb.domain.matriz.Inseminacao;
import com.magossi.apisimb.domain.tarefa.Tarefa;
import com.magossi.apisimb.service.bovino.BovinoService;
import com.magossi.apisimb.service.tarefa.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.AssertFalse;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 16/11/2016.
 */

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/tarefa")
public class TarefaResource {

    @Autowired
    public TarefaService tarefaService;

    @Autowired
    public BovinoService bovinoService;


    // ******************************** METODOS POST *******************************************************


    @RequestMapping(method =  RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Tarefa tarefa){
        tarefa = tarefaService.salvar(tarefa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tarefa.getIdTarefa()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}/inseminacao", method =  RequestMethod.POST)
    public ResponseEntity<Void> salvarInseminacaoTarefa(@PathVariable("id") Long id, @RequestBody Inseminacao inseminacao){
        Tarefa tarefa = tarefaService.buscarId(id);
        if (tarefa.getBovinoMatriz().getFichaMatriz().getInseminacao().isEmpty()){
            List<Inseminacao> inseminacaos = new ArrayList<>();
            tarefa.getBovinoMatriz().getFichaMatriz().setInseminacao(inseminacaos);
        }
        tarefa.setStatusDaTarefa(true);
        tarefa.setDataConclusao(new Date());
        tarefa.getBovinoMatriz().getFichaMatriz().getInseminacao().add(inseminacao);
        tarefa = tarefaService.atualizar(tarefa);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tarefa.getIdTarefa()).toUri();

        return ResponseEntity.created(uri).build();
    }


    // ******************************** METODOS PUT *******************************************************

    @RequestMapping(method =  RequestMethod.PUT)
    public ResponseEntity<Void> alterar(@RequestBody Tarefa tarefa){
        tarefa = tarefaService.alterar(tarefa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tarefa.getIdTarefa()).toUri();

        return ResponseEntity.created(uri).build();
    }



    // ******************************** METODOS DELETE *******************************************************

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id")Long id){

        tarefaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }


    // ******************************** METODOS GET *******************************************************

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> listar(){

        List<Tarefa> tarefas = tarefaService.buscarTodas();
        return ResponseEntity.status(HttpStatus.OK).body(tarefas);
    }

    @RequestMapping(value = "/ativas", method = RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> listarAtivas(){

        List<Tarefa> tarefas = tarefaService.buscarTodasAtivas();
        return ResponseEntity.status(HttpStatus.OK).body(tarefas);
    }

    @RequestMapping(value = "/concluidas", method = RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> listarConcluidas(){

        List<Tarefa> tarefas = tarefaService.buscarTodasConcluidas();
        return ResponseEntity.status(HttpStatus.OK).body(tarefas);
    }

    @RequestMapping(value = "/imei/{imei}", method = RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> buscarTarefaPorImei(@PathVariable("imei")String imei){

        List<Tarefa> tarefas = tarefaService.buscarImei(imei);
        return ResponseEntity.status(HttpStatus.OK).body(tarefas);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable("id") Long id){   //? encapsula qualquer tipo de objeto

        Tarefa tarefa = tarefaService.buscarId(id);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @RequestMapping(value = "/bovinoMatriz/{idBovino}", method = RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> buscarTarefasPorBovinoMatriz(@PathVariable("idBovino")Long idBovino){
        Bovino bovino = bovinoService.buscarId(idBovino);
        List<Tarefa> tarefas = tarefaService.buscarPorBovinoMatriz(bovino);
        return ResponseEntity.status(HttpStatus.OK).body(tarefas);

    }

    @RequestMapping(value = "/dataAtivas/{data}", method = RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> buscarTarefaAtivasPorData(@PathVariable("data")String dataString) throws Exception {

        List<Tarefa> tarefas = null;

        if("todos".equals(dataString)){
            tarefas = tarefaService.buscarTodasAtivas();
        }else{
            Date data = formataStringToDate(dataString);
            tarefas = tarefaService.buscarAtivasData(data);
        }

        return ResponseEntity.status(HttpStatus.OK).body(tarefas);

    }

    @RequestMapping(value = "/dataConcluidas/{data}", method = RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> buscarTarefaConcluidasPorData(@PathVariable("data")String dataString) throws Exception {

        List<Tarefa> tarefas = null;

        if("todos".equals(dataString)){
            tarefas = tarefaService.buscarTodasConcluidas();
        }else{
            Date data = formataStringToDate(dataString);
            tarefas = tarefaService.buscarConcluidasData(data);
        }


        return ResponseEntity.status(HttpStatus.OK).body(tarefas);

    }

    public static Date formataStringToDate(String data) throws Exception {
        if (data == null || data.equals(""))
            return null;
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            date = (java.util.Date)formatter.parse(data);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
}
