package com.studing.handler;

import com.studing.infra.ResourceNotFoundDetails;
import com.studing.infra.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice // controle de retorno de excpetion
public class RestExceptionHandler {

    @ExceptionHandler (ResourceNotFoundException.class) // vai ser tratado pelo metodo abaixo
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException){
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder
                .newBuilder().title("Não encontrado ")
                .status(HttpStatus.NOT_FOUND.value())
                .details(rfnException.getMessage())
                .devMessage(rfnException.getClass().getName()).build();
    return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler ( value = {HttpMessageNotReadableException.class ,ConstraintViolationException.class})
    // vai ser tratado pelo metodo abaixo
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException rfnException){
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder
                .newBuilder().title("Preencha todos os campos ")
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .details("Há campos obrigatorios vazios!!")
                .devMessage(rfnException.getClass().getName()).build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_ACCEPTABLE);
    }


    //Tratamento dos erros do JakartaValidation
    /*@ExceptionHandler (ConstraintViolationException.class) // vai ser tratado pelo metodo abaixo
    public ResponseEntity<?> handleHttpMessageNotReadableException(ConstraintViolationException rfnException){
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder
                .newBuilder().title("Preencha todos os campos!!!")
                .status(HttpStatus.BAD_REQUEST.value())
                .details(rfnException.getMessage())
                .devMessage(rfnException.getClass().getName()).build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.BAD_REQUEST);
    }


 */










    /*@ExceptionHandler(Exception.class) // vai tratar todas as exceptions
    public ResponseEntity<?> handleAnyExceptions(Exception e , WebRequest request){

        String erroDescription = e.getLocalizedMessage();

        if (erroDescription == null) erroDescription = e.toString();
        ErrorMessage errorMessage = new ErrorMessage(new Date(), erroDescription);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);

    }*/


}
