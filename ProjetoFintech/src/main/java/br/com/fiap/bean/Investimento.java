package br.com.fiap.bean;

public class Investimento {

	private int idInvestimento;
	private int idUsuario;
	private int idCorretora;
	private String nome;
	private double valorInicial;
	private double taxa;
	
	public Investimento() {
		super();
	}

	public Investimento(int idInvestimento, int idUsuario, int idCorretora, String nome, double valorInicial,
			double taxa) {
		super();
		this.idInvestimento = idInvestimento;
		this.idUsuario = idUsuario;
		this.idCorretora = idCorretora;
		this.nome = nome;
		this.valorInicial = valorInicial;
		this.taxa = taxa;
	}

	public int getIdInvestimento() {
		return idInvestimento;
	}

	public void setIdInvestimento(int idInvestimento) {
		this.idInvestimento = idInvestimento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdCorretora() {
		return idCorretora;
	}

	public void setIdCorretora(int idCorretora) {
		this.idCorretora = idCorretora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

}
