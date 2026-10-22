package br.com.fiap.gamavault.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> erro404(EntidadeNaoEncontradaException e) {
        return Map.of("mensagem", e.getMessage());
    }
    @ExceptionHandler(ConflitoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> erro409(ConflitoException e) {
        return Map.of("mensagem", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> dadosInvalidos(MethodArgumentNotValidException e) {
        Map<String, String> erros = new HashMap<>();
        for (FieldError field : e.getFieldErrors())
            erros.put(field.getField(), field.getDefaultMessage());
        return erros;
    }
}
