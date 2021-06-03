package com.rafael.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.cursomc.domain.Categoria;
import com.rafael.cursomc.repositories.CategoriaRepository;
import com.rafael.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		 Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Object not found! Id: " + id + ", Type: " + Categoria.class.getName()));
		} 

}
