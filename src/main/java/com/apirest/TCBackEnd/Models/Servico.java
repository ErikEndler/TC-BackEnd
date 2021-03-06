package com.apirest.TCBackEnd.Models;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Categoria categoria;
	private String nome;
	private String descricao;
	private LocalTime tempo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "servico")
	private List<ServicoFuncionario> servico_funcionario;

	public Servico(long id, Categoria categoria, String nome, String descricao, LocalTime tempo) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.tempo = tempo;
	}

	public Servico(Categoria categoria, String nome, String descricao, LocalTime tempo) {
		super();
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.tempo = tempo;
	}

}
