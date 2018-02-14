package com.djavani.crud.api.controllers;

import static org.springframework.data.domain.Sort.Direction.ASC;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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

import com.djavani.crud.api.models.Produto;
import com.djavani.crud.api.repositories.ProdutoRepository;
import com.djavani.crud.api.responses.Response;
import com.djavani.crud.api.services.CategoriaService;
import com.djavani.crud.api.services.ProdutoService;

@RestController
@RequestMapping(path = "api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository; 
	
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<Response<List<Produto>>> listarTodos() {
		return ResponseEntity.ok(new Response<List<Produto>>(this.produtoService.listarTodos()));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Produto>> listarPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity.ok(new Response<Produto>(this.produtoService.listarPorId(id)));
	}

	// consulta com Like
	@GetMapping(path = "/{nome}/like")
	public List<Produto> listarPorLike(@PathVariable(name = "nome") String nome) {
		return produtoRepository.findByNomeLikeIgnoreCase(nome);
	}

	// consulta com FullTextSearch
	@GetMapping(path = "/{nome}/fts")
	public List<Produto> listFullText(@PathVariable(name = "nome") String nome) {
		Pageable pages = new PageRequest(0, 10, new Sort(new Order(ASC, "score")));

		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(nome);

		return produtoRepository.findAllBy(criteria, pages);
	}

	@PostMapping
	public ResponseEntity<Response<Produto>> cadastrar(@Valid @RequestBody Produto produto, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Produto>(erros));
		}
		return ResponseEntity.ok(new Response<Produto>(this.produtoService.cadastrar(produto)));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Produto>> atualizar(@PathVariable(name = "id") String id,
			@Valid @RequestBody Produto produto, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Produto>(erros));
		}

		produto.setId(id);
		return ResponseEntity.ok(new Response<Produto>(this.produtoService.atualizar(produto)));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Integer>> remover(@PathVariable(name = "id") String id) {
		this.produtoService.remover(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}
}
