package com.vivs.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vivs.cursomc.domain.Categoria;
import com.vivs.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	/*@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:name% AND cat IN :categs")
	Page<Produto> search(@Param("name") String nome, @Param("categs") List<Categoria> categorias, Pageable pageRequest);*/
	
	/*@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:name% AND cat IN :categs")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("name") String nome, @Param("categs") List<Categoria> categorias, Pageable pageRequest);*/
	
	//A query acima faz o mesmo que a linha abaixo
	@Transactional(readOnly=true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);

}
