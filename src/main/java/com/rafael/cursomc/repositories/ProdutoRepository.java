package com.rafael.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rafael.cursomc.domain.Categoria;
import com.rafael.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	//usar a query e esse método aí esquisito que o spring te dá, dá no mesmo
	//pra usar só o método, tira a anotação de query e param
	//pra usar só a anotaçao de query, troca o nome do metodo pra qualquer coisa(search)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}
