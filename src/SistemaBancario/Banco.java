package SistemaBancario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import BancoNegocio.CriaCliente;
import BancoNegocio.CriaConta;

public class Banco {

	CriaConta contas = new CriaConta();
	CriaCliente clientes = new CriaCliente();

	public void adicionarCliente() {
		try {
			clientes.addCliente();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void recuperarAllCliente() {
		clientes.pesquisarCliente();
	}

	public void removeCliente() {
		clientes.removeCliente();
	}

	public ArrayList<String> recuperarCliente() {
		return clientes.pesquisarCliente();
	}


	public ArrayList<String> recuperarConta() {
		return contas.recuperarConta();
	}

	public void criarConta() {
		contas.addConta();
	}
	
	public void desativarConta(Conta conta) {
		contas.desativarConta(conta);
	}

	public void deletarConta() {
		contas.removeConta();

	}

	public void depositar() {
		contas.depositar();

	}

	public void Sacar(Conta conta, double valor) {
		contas.Sacar();

	}
	public void sacar() {
		// TODO Auto-generated method stub
		
	}
}
