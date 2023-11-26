package br.com.fiap.bean;

import java.util.Calendar;

public class Despesa {

	private int idDespesa;
	private int idUsuario;
	private int idCategoria;
	private String nome;
	private double valor;
	private Calendar data;
	private String descricao;
	
	public Despesa() {
		super();
	}

	public Despesa(int idDespesa, int idUsuario, int idCategoria, String nome, double valor, Calendar data,
			String descricao) {
		super();
		this.idDespesa = idDespesa;
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
		this.nome = nome;
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
	}

	public int getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
