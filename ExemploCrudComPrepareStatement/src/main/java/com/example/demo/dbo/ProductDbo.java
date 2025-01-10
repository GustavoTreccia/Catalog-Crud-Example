package com.example.demo.dbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Product;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Component
public class ProductDbo {
	
	int i = 1;
	
	@Autowired
	private DataSource dataSource;
	
	public List<Product> findByNameOrIdOrAll(String name, Long id) throws ParseException {
	    List<Product> products = new ArrayList<>();

	    try {
	    	Connection conn = dataSource.getConnection();
	    	
	    	String query = "SELECT * FROM tb_product";
	    	
	    	if(name != null) {
	    		query = query.concat(" WHERE name LIKE ?");
	    	}
	    	
	    	if(id != null) {
	    		query = query.concat(" WHERE name LIKE ?");
	    	}
	    	
	    	PreparedStatement stmt = conn.prepareStatement(query);
	    	
	    	if(name != null) {
	    		stmt.setString(1, "%" + name + "%");
	    	}
	    	
	    	if(id != null) {
	    		stmt.setLong(1, id);
	    	}
	    	
	    	ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Product product = new Product();
	            product.setId(rs.getLong("id"));
	            product.setName(rs.getString("name"));
	            product.setPrice(rs.getDouble("price"));
	            product.setDescription(rs.getString("description"));
	            product.setImgUrl(rs.getString("img_url"));
	            product.setCategory(rs.getString("category"));
	            product.setInsertTime(rs.getString("insertTime"));
	            product.setAlterTime(rs.getString("alterTime"));
	            product.setCodGeral(rs.getString("cod_geral"));
	            products.add(product);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao buscar produtos", e);
	    } catch (ResourceNotFoundException e ) {
	    	throw new ResourceNotFoundException("Produto não encontrado");
	    }

	    return products;
	}

	public Product insert(Product product) {
		
		int i = 1;
		
		try {
			Connection conn = dataSource.getConnection();
			String query = "INSERT INTO tb_product (name, price, description, img_url, category, insertTime, alterTime, cod_geral) " +
	                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			Random random = new Random();
			Long key = random.nextLong(1000000000000L);
			
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(i++, product.getName());
	        stmt.setDouble(i++, product.getPrice());
	        stmt.setString(i++, product.getDescription());
	        stmt.setString(i++, product.getImgUrl());
	        stmt.setString(i++, product.getCategory());
	        stmt.setTimestamp(i++, Timestamp.from(Instant.now()));
	        stmt.setNull(i++, Types.TIMESTAMP);
	        stmt.setString(i, key.toString());
	        
	        int rowsAffected = stmt.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    product.setId(generatedKeys.getLong(1));
	                }
	            }
	        }
			
		} catch (SQLException e) {
	        throw new RuntimeException("Erro ao inserir produtos", e);
	    }

		return product;
	}

	public Product update(Long id, Product product) {
		try {
			Connection conn = dataSource.getConnection();
			String query = "UPDATE tb_product SET name = ?, price = ?, description = ?, img_url = ?, category = ?, alterTime = ? " +
	                   "WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, product.getName());
	        stmt.setDouble(2, product.getPrice());
	        stmt.setString(3, product.getDescription());
	        stmt.setString(4, product.getImgUrl());
	        stmt.setString(5, product.getCategory());
	        stmt.setTimestamp(6, Timestamp.from(Instant.now())); // alterTime com o instante atual
	        stmt.setLong(7, id);

	        // Executar a query
	        int rowsAffected = stmt.executeUpdate();

	        if (rowsAffected == 0) {
	            throw new RuntimeException("Nenhum produto encontrado com o id: " + id);
	        }
			
		} catch (SQLException e) {
	        throw new RuntimeException("Erro ao inserir produtos", e);
	    }

		return product;
		
	}

	public void delete(Long id) {
		try {
		Connection conn = dataSource.getConnection();
		String query = "DELETE FROM tb_product WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		stmt.setLong(1, id);
		
		int rowsAffected = stmt.executeUpdate();
		
		if (rowsAffected == 0) {
            throw new RuntimeException("Nenhum produto encontrado com o id: " + id);
        }
		
		} catch (SQLException e) {
	        throw new RuntimeException("Erro ao inserir produtos", e);
	    }
		
	}

}
