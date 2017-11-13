package com.magossi.apisimb.resources.bovino;

import com.magossi.apisimb.domain.bovino.Bovino;
import com.magossi.apisimb.domain.bovino.Desmama;
import com.magossi.apisimb.service.bovino.DesmamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/desmama")
public class DesmamaResources {
    @Autowired
    DesmamaService desmamaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Desmama>> listar(){
        System.out.println("123TEST");
        List<Desmama> desmamas = desmamaService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(desmamas);
    }
}
