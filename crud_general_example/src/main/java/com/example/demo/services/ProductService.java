package com.example.demo.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dbo.ProductDbo;
import com.example.demo.entities.Product;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductDbo productDbo;

	@Transactional(readOnly = true)
	public List<Product> findAll() throws ParseException, ResourceNotFoundException {
		List<Product> list = productDbo.findByNameOrIdOrAll(null, null);
		return list;
	}

	public List<Product> findProductByName(String name) throws ParseException, ResourceNotFoundException {
		return productDbo.findByNameOrIdOrAll(name, null);
	}

	public Product insert(Product product) {
		return productDbo.insert(product);
	}

	public Product update(Long id, Product product) throws ResourceNotFoundException {
		return productDbo.update(id, product);
	}

	public void delete(Long id)  throws ResourceNotFoundException, ParseException {
		if(productDbo.findByNameOrIdOrAll(null, id) != null) {
			productDbo.delete(id);
		}
	}
}