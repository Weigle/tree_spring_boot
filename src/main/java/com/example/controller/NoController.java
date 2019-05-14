package com.example.controller;

import com.example.DTO.NodeDTO;
import com.example.Model.Node;
import com.example.exception.InvalidParentId;
import com.example.exception.noInexistente;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

public class NoController {

    @Autowired
    private NoService noService;



    //lista todos os nos

    @RequestMapping(value = "/node", method = RequestMethod.GET, produces = "application/json")
    public List<Node> listar() {
        //System.out.println(noService.findAll());
        return noService.findAll();
    }

    //lista determinado no
    @RequestMapping(value = "/node/{parentid}", method = RequestMethod.GET, produces = "application/json")
    public List<NodeDTO> listar_no_espesifico(@PathVariable Long parentid) {

        return noService.retornaParentid(parentid);
    }


    @PutMapping(value = "/node/")
    public String update(@RequestBody NodeDTO node) throws noInexistente, InvalidParentId {

        return noService.update(node);

    }

    @PostMapping(value = "/node/")
    public String create(@RequestBody NodeDTO node) throws InvalidParentId {

        return noService.create(node);
    }

    @RequestMapping(value = "/node/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String delete(@PathVariable Long id) throws noInexistente {

        return noService.delete(id);
    }
}
