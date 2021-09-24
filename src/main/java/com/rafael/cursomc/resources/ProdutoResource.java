package com.rafael.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.cursomc.domain.Produto;
import com.rafael.cursomc.dto.ProdutoDTO;
import com.rafael.cursomc.resources.utils.URL;
import com.rafael.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto object = service.find(id);
		
		return ResponseEntity.ok().body(object);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(name="nome", defaultValue = "")String nome,
			@RequestParam(name="categorias", defaultValue = "")String categorias,
			@RequestParam(name="page", defaultValue = "0") Integer page, //requestparam é anotação pra dizer que é parâmetro opcional
			@RequestParam(name="linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(name="page", defaultValue = "nome")String orderBy, 
			@RequestParam(name="page", defaultValue = "ASC")String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
