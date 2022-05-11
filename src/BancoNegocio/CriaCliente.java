package BancoNegocio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.ClienteException;
import Persistencia.PersistenciaClienteArquivo;
import SistemaBancario.Banco;
import SistemaBancario.Cliente;

public class CriaCliente {

	Cliente cliente = new Cliente();
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ArrayList<Cliente> listaCliente = new ArrayList<>();
	ArrayList<String> listaCliente1 = new ArrayList<>();
	ArrayList<String> aux = new ArrayList<>();
	ArrayList<Cliente> listaAux = new ArrayList<>();
	PersistenciaClienteArquivo persistencia = new PersistenciaClienteArquivo();
	
	File arquivo = new File("Cliente.txt");

	Banco banco;
	CriaConta criaConta;

	Scanner input = new Scanner(System.in);

	public void addCliente() throws ClassNotFoundException, Exception {
		System.out.println("Digite seu cpf");
		cliente.setCpf(input.next());
		System.out.println("Digite seu nome");
		cliente.setNome(input.next());
		System.out.println("Digite sua idade");
		cliente.setIdade(input.nextInt());
		System.out.println("Digite seu Sexo");
		cliente.setSexo(input.next());
		System.out.println("Digite seu endereco");
		cliente.setEndereco(input.next());
		if (clientes.contains(cliente.getCpf()) == true) {
			throw new ClienteException("Este cliente já existe");
		} else {
			System.out.println("Cliente cadastrado com sucesso");
			clientes.add(cliente);

			atualizarPersistencia();
		}
	}

	public void removeCliente() {
		int para = 1;
		System.out.println("Digite o número do cpf");
		String cpf = input.next();

		listaCliente1 = persistencia.recuperarTodosClientes();
		while (listaCliente1.remove(null)) {
			for (int i = 0; i < listaCliente1.size(); i++) {
				if (listaCliente1.get(i).contains(cpf) == true) {
					listaCliente1.remove(listaCliente1.get(i));
					aux = listaCliente1;
					System.out.println("Auxiliar " + aux);

				} else if (listaCliente1.get(i) == null) {
					listaCliente1.remove(listaCliente1.get(i));
					aux = listaCliente1;
				}
			}
			para++;
		}

		deletaPersistencia(aux);
	}

	public void atualizarPersistencia() {
		try {
			persistencia.gravarCliente(clientes);

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void deletaPersistencia(ArrayList<String> aux) {
		try {
			persistencia.deletarCliente(aux);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<String> pesquisarCliente() {
		System.out.println("Digite o seu cpf");
		String cpf = input.next();
		listaCliente1 = persistencia.recuperaCliente(cpf);
		System.out.println(listaCliente1);
		
		return listaCliente1;
	}

}
