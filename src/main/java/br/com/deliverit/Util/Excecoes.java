package br.com.deliverit.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class Excecoes {

    @Value("${spring.profiles.active}")
    private String perfil;

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Retorno> excecaoJson(HttpMessageNotReadableException e) {
        if (perfil != null && perfil.equals("DEV")) {
            return new ResponseEntity<>(new Retorno(e.getMessage()), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new Retorno("Json mal formatado!"), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Retorno> excecaoGeral(Exception e) {
        if (perfil != null && perfil.equals("DEV")) {
            return new ResponseEntity<>(new Retorno(e.getMessage()), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new Retorno("Erro!"), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Retorno> execaoUnique(Exception e) {
        if (e.getCause() != null && e.getCause().getClass() == org.hibernate.exception.ConstraintViolationException.class) {
            return new ResponseEntity<>(new Retorno<>("Existem atributos que devem ser únicos!"), HttpStatus.BAD_REQUEST);
        } else {
            if (perfil != null && perfil.equals("DEV")) {
                return new ResponseEntity<>(new Retorno(e.getMessage()), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new Retorno("Erro!"), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @ExceptionHandler({ApiError.class})
    public ResponseEntity<Retorno> erroAPI(ApiError apiError) {
        Retorno retorno = new Retorno();
        retorno.setErro(true);
        retorno.setMensagem(apiError.getMessage());
        return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
    }
}
