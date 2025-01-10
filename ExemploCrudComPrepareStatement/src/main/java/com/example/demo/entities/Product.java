package com.example.demo.entities;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable{

	private static final long serialVersionUID = -7829565942340792203L;
	
	private Long id;
	
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	private String category;
	private String insertTime;
	private String alterTime;
	private String cod_geral;
	
	public Product() {}

	public Product(Long id, String name, String description, Double price, String imgUrl, String category,
			String insertTime, String alterTime, String cod_geral) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.category = category;
		this.insertTime = insertTime;
		this.alterTime = alterTime;
		this.cod_geral = cod_geral;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getAlterTime() {
		return alterTime;
	}

	public void setAlterTime(String alterTime) {
		this.alterTime = alterTime;
	}

	public String getCodGeral() {
		return cod_geral;
	}

	public void setCodGeral(String cod_geral) {
		this.cod_geral = cod_geral;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alterTime, category, cod_geral, description, id, imgUrl, insertTime, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(alterTime, other.alterTime) && Objects.equals(category, other.category)
				&& Objects.equals(cod_geral, other.cod_geral) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(imgUrl, other.imgUrl)
				&& Objects.equals(insertTime, other.insertTime) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price);
	}
}
