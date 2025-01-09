package com.example.demo;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class CrudExampleApplication {

//	 @Autowired
//	 private DataSource dataSource;

	    public static void main(String[] args) {
	        SpringApplication.run(CrudExampleApplication.class, args);
	    }

//	    @PostConstruct
//	    public void testConnection() {
//	        try (Connection conn = dataSource.getConnection();
//	             Statement stmt = conn.createStatement();
//	             ResultSet rs = stmt.executeQuery("SELECT 'Hello World' AS message")) {
//
//	            if (rs.next()) {
//	                System.out.println("Banco conectado! Mensagem: " + rs.getString("message"));
//	            } else {
//	                System.out.println("Não foi possível obter resposta do banco.");
//	            }
//
//	        } catch (Exception e) {
//	            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
//	        }
//	    }

}
