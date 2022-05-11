package SistemaBancario;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private int senha;
	private String numeroConta;
	private double saldo;
	private Date dataAbertura;
	private boolean ativa;
	
	public Conta() { }

	public Conta(Cliente cliente, int senha, String numeroConta, double saldo) {
		this.cliente = cliente;
		this.senha = senha;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.dataAbertura = new Date();
		this.ativa = true;
	}
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public boolean isStatus() {
		return ativa;
	}

	public void setStatus(boolean ativa) {
		this.ativa = ativa;
	}

	public String toString() {

		return "Conta [numeroConta=" + numeroConta + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura + ", status="
				+ ativa + "]";
	}

	public int hashCode() {
		return Objects.hash(numeroConta);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(numeroConta, other.numeroConta);
	}
}


