package com.rafael.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafael.cursomc.domain.Categoria;
import com.rafael.cursomc.domain.Pedido;
import com.rafael.cursomc.dto.CategoriaDTO;
import com.rafael.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido object = service.find(id);
		
		return ResponseEntity.ok().body(object);
	}
	
	@RequestMapping(method=RequestMethod.POST)//anotaçao do metodo
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){//faz o JSON ser convertido pra objeto Java automaticamente
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();//created gera o codigo 201 e recebe a uri como argumento
	}
}
