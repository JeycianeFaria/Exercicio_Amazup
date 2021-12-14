package br.com.zup.Amazup.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<MensagemErro> manipularErrosValidacao(MethodArgumentNotValidException exception){
         List<MensagemErro> erros = new ArrayList<>();
        for (FieldError referencia : exception.getFieldErrors()){
            MensagemErro mensagem = new MensagemErro(referencia.getDefaultMessage());
            erros.add(mensagem);
        }

        return erros;
    }

}
