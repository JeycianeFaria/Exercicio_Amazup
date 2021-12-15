package br.com.zup.Amazup.config;

import br.com.zup.Amazup.livro.exceptions.LivroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity manipularErroGenero(HttpMessageNotReadableException exception) {
        if(exception.getLocalizedMessage().contains("br.com.zup.Amazup.livro.enuns.Genero")){
            return ResponseEntity.status(422).build();
        }

        return ResponseEntity.status(400).build();
    }

    @ExceptionHandler(LivroNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemErro manipularLivroNaoEncontrado(LivroNaoEncontradoException exception){
        return  new MensagemErro(exception.getMessage());
    }

}
