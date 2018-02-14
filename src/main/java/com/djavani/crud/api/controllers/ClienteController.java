package com.djavani.crud.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djavani.crud.api.documents.Cliente;
import com.djavani.crud.api.repositories.ClienteRepository;
import com.djavani.crud.api.responses.Response;
import com.djavani.crud.api.services.ClienteService;

@RestController
@RequestMapping(path = "api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository; 

	@GetMapping
	public ResponseEntity<Response<List<Cliente>>> listarTodos() {
		return ResponseEntity.ok(new Response<List<Cliente>>(this.clienteService.listarTodos()));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Cliente>> listarPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.listarPorId(id)));
	}

	// consulta com Like
	@GetMapping(path = "/{nome}/like")
	public List<Cliente> listarPorLike(@PathVariable(name = "nome") String nome) {
		return clienteRepository.findByNomeLikeIgnoreCase(nome);
	}

	// consulta com FullTextSearch
	@GetMapping(path = "/{nome}/fts")
	public List<Cliente> listFullText(@PathVariable(name = "nome") String nome) {
		Pageable pages = new PageRequest(0, 10, new Sort(new Order(ASC, "score")));

		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(nome);

		return clienteRepository.findAllBy(criteria, pages);
	}

	@PostMapping
	public ResponseEntity<Response<Cliente>> cadastrar(@Valid @RequestBody Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cliente>(erros));
		}
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.cadastrar(cliente)));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Cliente>> atualizar(@PathVariable(name = "id") String id,
			@Valid @RequestBody Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cliente>(erros));
		}

		cliente.setId(id);
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.atualizar(cliente)));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Integer>> remover(@PathVariable(name = "id") String id) {
		this.clienteService.remover(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}
}
