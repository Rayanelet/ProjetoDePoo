package BancoNegocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Exception.ContaException;
import Persistencia.PersistenciaContaArquivo;
import SistemaBancario.Cliente;
import SistemaBancario.Conta;

public class CriaConta {

	Conta conta = new Conta();
	File arquivo = new File("Conta.txt");
	ArrayList<Conta> contas = new ArrayList<Conta>();
	ArrayList<Conta> listaConta = new ArrayList<Conta>();
	ArrayList<String> listaConta1 = new ArrayList<>();
	ArrayList<String> aux = new ArrayList<>();
	ArrayList<Cliente> listaAux = new ArrayList<>();
	PersistenciaContaArquivo persistencia = new PersistenciaContaArquivo();
	
	ArrayList<String> listaCliente1 = new ArrayList<>();
	
	Cliente cliente = new Cliente();
	CriaCliente criaCliente;
	List<Cliente> c1;
	
	Date date = new Date();
	Scanner input = new Scanner(System.in);

	public void addConta() {
		cliente = recuperarCliente();
		conta.setCliente(cliente);
		System.out.println("Digite seu numero da conta");
		conta.setNumeroConta(input.next());
		System.out.println("Digite sua senha");
		conta.setSenha(input.nextInt());
		System.out.println("Digite seu saldo");
		conta.setSaldo(input.nextInt());

		conta.setDataAbertura(date);
		conta.setStatus(true);		
		
		for (Conta i : contas) {
			if (i.getCliente().getCpf().equals(conta.getCliente().getCpf())
					|| i.getNumeroConta() == conta.getNumeroConta()) {
				throw new ContaException("Os dados são invalidos");
			}
		}
		contas.add(conta);
		System.out.println("Conta criada com sucesso");
		ContaatualizaPersistencia(conta);
	}

	public void removeConta() {
		int para = 1;
		System.out.println("Digite o número do cpf");
		String cpf = input.next();

		listaCliente1 = persistencia.recuperarTodasContas();
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
	
	public ArrayList<String> recuperarTodasContas() {
		int i = 0;
		try {
			File file = new File("Conta2.txt");
			FileReader fileReader;
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String linha = "";
			ArrayList<String> linhas = new ArrayList<String>();

			while (linha != null) {
				linha = bufferedReader.readLine();
				linhas.add(linha);
				i++;
			}
			return linhas;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deletarConta(ArrayList<String> listaConta) throws IOException {
		if (arquivo.exists() == false) {
			arquivo = new File("Conta2.txt");
		}

		FileOutputStream fileOut = new FileOutputStream("Conta.txt", true);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

		FileOutputStream fileOutAux = new FileOutputStream("Conta2.txt");
		ObjectOutputStream objectOutAux = new ObjectOutputStream(fileOutAux);

		objectOutAux.writeObject(listaConta);
		objectOut.flush();
		objectOut.close();
		fileOut.flush();
		fileOut.close();

		objectOutAux.flush();
		objectOutAux.close();
		fileOutAux.flush();
		fileOutAux.close();
	}

	public void depositar() {
		System.out.println("Digite o número da conta");
		String numero = input.next();
		System.out.println("Digite sua senha");
		int senha = input.nextInt();
		System.out.println("Digite o valor do deposito");
		double valor = input.nextDouble();
		
		if (verificaConta(numero, senha) == true || conta.isStatus() == true) {
			System.out.println("Deposito feito com sucesso");
		}
		for (Conta i : contas) {
			if (i.getSenha() == senha) {
				if (valor <= 0) {
					throw new ContaException("Você inseriu um valor negativo");
				}

				i.setSaldo(i.getSaldo() + valor);
				atualizaPersistencia();
			}
		}
	}

	public void Sacar() {
		System.out.println("Digite o número da conta");
		int numero = input.nextInt();
		System.out.println("Digite sua senha");
		int senha = input.nextInt();
		System.out.println("Digite o valor que deseja sacar");
		double valor = input.nextDouble();
		
		if (verificaConta(conta.getNumeroConta(), conta.getSenha()) == true || conta.isStatus() == true
				|| valor > conta.getSaldo()) {
			System.out.println("Saque feito com sucesso");

		}

		for (Conta i : contas) {
			if (i.getSenha() == conta.getSenha()) {
				i.setSaldo(i.getSaldo() - valor);
				atualizaPersistencia();
			}
		}
	}

	public void desativarConta(Conta conta) {
		for (Conta i : contas) {
			if (i.getSenha() == conta.getSenha()) {
				i.setStatus(false);
				atualizaPersistencia();
			}
		}
	}

	public Conta buscarConta(String numeroConta) {
		for (Conta i : contas) {
			if (i.getNumeroConta().equals(numeroConta)) {
				return i;
			}
		}
		throw new ContaException("Conta não foi encontrada");
	}

	public ArrayList<String> recuperarConta() {
		System.out.println("Digite o número da conta");
		String numeroConta = input.next();
		listaConta1 = persistencia.recuperaConta(numeroConta);
		System.out.println(listaConta1);
		
		return listaConta1;
	}

	public void atualizaPersistencia() {
		try {
			persistencia.gravarConta(contas);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void ContaatualizaPersistencia(Conta conta2) {
		try {
			contas.add(conta2);
			persistencia.gravarConta(contas);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean verificaConta(String numeroConta, int senha) {
		for (Conta i : contas) {
			if (i.getNumeroConta() == numeroConta && i.getSenha() == senha) {
				return true;

			} else if (i.isStatus() == false) {
				return false;
			}
		}
		return false;
	}

	private Date validaData(String dataStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);

		try {
			Date data = sdf.parse(dataStr);
			return data;
		} catch (ParseException e) {
			System.err.println("Data inválida");
		}
		return null;

	}
	
	public Cliente recuperarCliente(){
		System.out.println("Digite o cpf do cliente");
		String cpf = input.next();
		c1 = persistencia.recuperarCliente();
		for(Cliente i:c1){
			if(cpf.equals(i.getCpf())){
				return i;
			}
		}
		return null;
		
	}
	
	public void deletaPersistencia(ArrayList<String> aux) {
		try {
			persistencia.deletarConta(aux);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
