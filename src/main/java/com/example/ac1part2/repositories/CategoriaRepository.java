package com.example.ac1part2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ac1part2.models.Categoria;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNomeStartingWith(String nome);

    @Query("SELECT c FROM Categoria c left join fetch c.produtos WHERE c.id = :id")
    Categoria findByIdWithProdutos(@Param("id") Long id);
}
