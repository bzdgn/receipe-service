package io.github.bzdgn.receipe.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Receipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Integer serving;
	private String ingredients;
	private String instructions;
	private boolean isVegan;

	public Receipe() {
	}

	public Receipe(String name, Integer serving, String ingredients, String instructions, boolean isVegan) {
		this.name = name;
		this.serving = serving;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.isVegan = isVegan;
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

	public Integer getServing() {
		return serving;
	}

	public void setServing(Integer serving) {
		this.serving = serving;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public boolean isVegan() {
		return isVegan;
	}

	public void setVegan(boolean isVegan) {
		this.isVegan = isVegan;
	}

	@Override
	public String toString() {
		return "Receipe [id=" + id + ", name=" + name + ", serving=" + serving + ", ingredients=" + ingredients
				+ ", instructions=" + instructions + ", isVegan=" + isVegan + "]";
	}

}