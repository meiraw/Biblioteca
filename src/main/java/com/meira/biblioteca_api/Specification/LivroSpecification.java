package com.meira.biblioteca_api.Specification;

import com.meira.biblioteca_api.DTO.ResponseDTO.LivroResponseDTO;
import com.meira.biblioteca_api.Enums.StatusLivro;
import com.meira.biblioteca_api.Model.LivroModel;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

//Depois de criar no repository a permissão do Specification
//Responsavel para montar consulta de livro (Filtro )
//Ele é o where para buscas
public class LivroSpecification {


    //Cria a regra da pesquisa para o livro model
    //No caso o busca será direta ao titulo do livro com o "String titulo "
    public static Specification<LivroModel> tituloContem(String titulo) { //Esse metodo recebe um texto e devolve uma regra de busca para livros
        //Retorna a pesquisa , neste caso dentro do LivroModel
        return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(   //Condição LIKE em SQl
                            criteriaBuilder.lower(root.get("titulo")), // significa pegar o titulo do livro // transformando todos os caracteres em minusculos
                            "%" + titulo.toLowerCase() + "%"            // Aqui define que o titulo deve ter o  %% , exemplo , livro : %JAVA%
                    ); //O % é usado pelo LIKE para representar qualquer sequência de caracteres, inclusive nenhuma.

    }

    public static Specification<LivroModel> statusIgual(StatusLivro status){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(  root.get("status"), // significa pegar o titulo do livro
                        status           // transformando todos os caracteres em minusculos
                );
    }

}


