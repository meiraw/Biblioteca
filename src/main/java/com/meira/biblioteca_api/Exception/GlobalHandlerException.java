package com.meira.biblioteca_api.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(ResourceNotFoundException ex){
        ErrorResponse error =  new ErrorResponse(ex.getMessage(), 404);
        return ResponseEntity.status(404).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos!");

        ErrorResponse error = new ErrorResponse(mensagem, 400);

        return ResponseEntity.status(400).body(error);
    }

    //Emplementação de erro na regra de negócio no delete de status do livro de EMPRTESTADO
    // e no delete de autor , que não pode acontecer
    //E a emplementação do 409 conflict
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErrorResponse> handleRegraNegocio(RegraNegocioException ex) {

        ErrorResponse error = new ErrorResponse(ex.getMessage(), 409);

        return ResponseEntity.status(409).body(error);
    }
}
