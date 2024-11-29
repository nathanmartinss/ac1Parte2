package com.example.ac1part2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ac1part2.models.Produto;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.preco > :preco")
    List<Produto> findByPrecoGreaterThan(@Param("preco") Double preco);

    @Query("SELECT p FROM Produto p WHERE p.preco <= :preco")
    List<Produto> findByPrecoLessThanEqual(@Param("preco") Double preco);

    @Query("SELECT p FROM Produto p WHERE p.nome LIKE :nome%")
    List<Produto> findByNomeStartingWith(@Param("nome") String nome);
}
