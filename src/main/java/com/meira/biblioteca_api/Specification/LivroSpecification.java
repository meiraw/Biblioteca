package com.meira.biblioteca_api.Specification;

import com.meira.biblioteca_api.Model.LivroModel;
import org.springframework.data.jpa.domain.Specification;
//Depois de criar no repository a permissão do Specification
//Responsavel para montar consulta de livro (Filtro )
//Ele é o where para buscas
public class LivroSpecification {


    //Cria a regra da pesquisa para o livro model
    //No caso o busca será direta ao titulo do livro com o "String titulo "
    public static Specification<LivroModel> tituloContem(String titulo) {
        //Retorna a pesquisa , neste caso dentro do LivroModel
        return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(   //Condição LIKE em SQl
                            criteriaBuilder.lower(root.get("titulo")), // significa pegar o titulo do livro
                            "%" + titulo.toLowerCase() + "%"            // transformando todos os caracteres em minusculos
                    );

    }
}


