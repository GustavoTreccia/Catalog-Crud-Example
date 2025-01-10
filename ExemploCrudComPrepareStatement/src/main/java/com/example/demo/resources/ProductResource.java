package com.example.demo.resources;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;
	private ApiResponse response;
	
	@RestControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
	        String response = ex.getMessage();
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}

	@GetMapping
	public ResponseEntity<List<Product>> findAll() throws ParseException {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{name}")
	public ResponseEntity<List<Product>> findByName(@PathVariable String name) throws ParseException {
		return ResponseEntity.ok().body(service.findProductByName(name));
	}

	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product product) {
		product = service.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
		try {
			product = service.update(id, product);
			return ResponseEntity.ok().body(product);
		} catch (RuntimeException ex) {
			String response = ex.getMessage();
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws ResourceNotFoundException, ParseException {
		service.delete(id);
		response = new ApiResponse("Registro com ID " + id + " foi excluído com sucesso.", 200);
		return ResponseEntity.ok(response);
	}
}
