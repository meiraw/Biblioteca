package com.meira.biblioteca_api.Repository;

import com.meira.biblioteca_api.Enums.StatusLivro;
import com.meira.biblioteca_api.Model.LivroModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
// JpaSpecificationExecutor<LivroModel>  faz consultas dinâmicas
public interface LivroRepository extends JpaRepository<LivroModel, UUID>, JpaSpecificationExecutor<LivroModel> { // Aplicando o Specification
    List<LivroModel> findByTituloContainingIgnoreCase(String titulo);
    Page<LivroModel> findByStatus(StatusLivro status, Pageable pageable);

    Page<LivroModel> findByTituloContainingIgnoreCaseAndStatus(
            String titulo,
            StatusLivro status,
            Pageable pageable
    );

    // o que é o Query ?
    //No Spring Data JPA, @Query é uma anotação que permite escrever a consulta que um metodo do Repository vai executar.

    @Query("SELECT livro FROM LivroModel livro WHERE livro.anopublicacao = :ano")
    Page<LivroModel>buscarPorAno(@Param("ano") Integer ano , Pageable pageable);
    //buscarPorAno: o metodo que o Service vai chamar.
    //Integer ano: recebe o ano desejado.
    //@Param("ano"): liga esse valor ao espaço chamado :ano na consulta.
    //Pageable pageable: recebe as opções de paginação.
    //Page<LivroModel>: informa que o resultado será uma página de livros.
}
//Essa linguagem é JPQL
//SELECT 1 retorne os livros encontrados
// FROM LivroModel 1 Consulte a entidade LivroModel e de a ela o apelido livro
// WHERE  Aplique a condição seguinte
// livro.anopublicacao use o campo anopublicacao do livro
// = :ano Compare com o valor recebido no parâmetro ano

// o @Param ("ano " ) liga com o =:ano

//Essa consulta em si é em JPQL
//Ela é usa o nome da entidade java (LivroModel) e sues campos java (anopublicacao)
//Mesmo sendo código em SQL , não tem nada aver em criar entidades
