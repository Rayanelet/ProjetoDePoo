package SistemaBancario;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cpf;
	private String nome;
	private int idade;
	private String sexo;
	private String endereco;

	public Cliente() {
	}

	public Cliente(String cpf, String nome, int idade, String sexo, String endereco) {
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", endereco="
				+ endereco + "]";
	}

	public int hashCode() {
		return Objects.hash(cpf);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}

}