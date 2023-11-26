package br.com.fiap.bean;
import java.util.Calendar;

public class ObjetivoFinanceiro {
	
	private int idObjetivo;
	private int idUsuario;
	private double valor;
	private Calendar dataInicio;
	private Calendar dataFim;
	private String descricao;

	public ObjetivoFinanceiro() {
		super();
	}
	
	public ObjetivoFinanceiro(int idObjetivo, int idUsuario, double valor, Calendar dataInicio, Calendar dataFim, String descricao) {
		super();
		this.idObjetivo = idObjetivo;
		this.idUsuario = idUsuario;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.descricao = descricao;
	}

	public int getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(int idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
