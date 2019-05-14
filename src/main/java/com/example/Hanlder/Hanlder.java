package com.example.Hanlder;

import com.example.exception.InvalidParentId;
import com.example.exception.noInexistente;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
@ControllerAdvice
@RestController
public class Hanlder extends RuntimeException{

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String entityNotFoundHandler(EntityNotFoundException e) {
        return "Não foi possivel encontrar o nó a ser atualizado ou o nó informado como 'parentId'";
    }

    @ExceptionHandler(InvalidParentId.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String invalidParentExceptionHandler(InvalidParentId e) {
        return "Não foi possivel usar o ParentID";
    }

    @ExceptionHandler(noInexistente.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String noInexistente(noInexistente e) {

        return "Não há ID Cadastrado no Banco de dados ";
    }


}
