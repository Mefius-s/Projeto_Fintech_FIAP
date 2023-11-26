package br.com.fiap.bean;
import java.util.Calendar;

public class Usuario {
	
	private int idUsuario;
	private String nome;
	private String email;
	private String senha;
	private Calendar dataNascimento;
	private String telefone;
	private String endereco;

	public Usuario() {
		super();
	}

	public Usuario(int idUsuario, String nome, String email, String senha, Calendar dataNascimento, String telefone,
			String endereco) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
