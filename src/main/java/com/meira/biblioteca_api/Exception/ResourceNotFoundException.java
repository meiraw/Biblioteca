package com.meira.biblioteca_api.Exception;

public class ResourceNotFoundException extends RuntimeException  {
    public ResourceNotFoundException (String mensagem){
        super(mensagem);
    }
}
