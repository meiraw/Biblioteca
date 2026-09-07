package com.meira.biblioteca_api.Exception;

//Emplementando  a regra de negócio no status de resposta com RuntimeException
public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException (String mensagem){
        super(mensagem); // Envia essa Mensagem para  a Classe RuntimeException
    }
}
