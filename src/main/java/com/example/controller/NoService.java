package com.example.controller;

import com.example.DTO.NodeDTO;
import com.example.Model.Node;
import com.example.exception.InvalidParentId;
import com.example.exception.noInexistente;
import com.example.repository.NoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoService {

    @Autowired
    private NoRepository repository;


    public String create(NodeDTO no) throws InvalidParentId {
        Node node;



        if (repository.count() == 0) {
            no.setParentid(null);
            node = repository.save(dtoToNode(no));
        } else if (repository.existsById(no.getParentid()) == true  && repository.Maxid() >= no.getParentid()) {
            node = repository.save(dtoToNode(no));
        } else   {
            throw new InvalidParentId();
        }

        return generatePersistenceJsonReturn(node);
    }

    String update(NodeDTO no) throws noInexistente {
        Node node = new Node();
        if (repository.existsById(no.getId())){

            Node old = repository.findByid(no.getId());
            no.setId(old.getId());
            node=dtoToNode(no);
            node.setParent(old.getParent());
            node =repository.save(node);
        }else {
            throw new noInexistente();
        }
        return generatePersistenceJsonReturn(node);
    }

    public List<NodeDTO> retornaParentid(Long parent) {
        return converteListToObject(repository.findByParentId(parent));
    }

    public String generatePersistenceJsonReturn(final Node node) {
        final StringBuilder sb = new StringBuilder();
        sb.append("{\"id\": ").append(node.getId()).append("}");

        return sb.toString();
    }
    public String delete(Long id) throws noInexistente{
        Node no = new Node();

        if (repository.existsById(id)){
            no = repository.findByid(id);
            repository.delete(no);

        }else{
            throw new noInexistente();
        }

        return generatePersistenceJsonReturn(no);
    }


    public List<Node> findAll() {
     ;
        return repository.findByParentId(null);
    }


    public List<NodeDTO> converteListToObject(final List<Node> lista_serialize) {


        final List<NodeDTO> noFinal = new ArrayList<>();

        for (final Node node : lista_serialize) {
            final NodeDTO no = new NodeDTO();
            no.setId(node.getId());
            no.setCode(node.getCode());
            no.setDescription(node.getDescription());
            no.setDetail(node.getDetail());

            if (node.getChildren() == null) {
                no.setHasChildren(false);
            } else {
                no.setHasChildren(!node.getChildren().isEmpty());
            }
            no.setParentid(node.getParent().getId());
            noFinal.add(no);
        }
        return noFinal;
    }

    public Node dtoToNode(NodeDTO DTO) {
        final Node node = new Node();

        node.setCode(DTO.getCode());
        node.setDescription(DTO.getDescription());
        node.setDetail(DTO.getDetail());

        if (DTO.getId() != null) {

            node.setId(DTO.getId());
        }

        if (DTO.getParentid() != null && DTO.getParentid() != 0) {
            final Node parent = new Node();

            parent.setId(DTO.getParentid());
            node.setParent(parent);
        }


        return node;
    }



}
