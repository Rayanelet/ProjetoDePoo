package Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import SistemaBancario.Cliente;
import SistemaBancario.Conta;

public class PersistenciaContaArquivo {

	Conta c;
	File arquivo = new File("Conta.txt");
	List<Conta> contas = new ArrayList<Conta>();
	ObjectOutputStream output;
	ObjectInputStream input;

	int i = 1;

	public void gravarConta(ArrayList<Conta> contas) throws IOException {
		if (arquivo.exists() == false) {
			arquivo = new File("Conta.txt");
		}

		FileOutputStream fileOut = new FileOutputStream(arquivo, true);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(contas);
		objectOut.flush();
		objectOut.close();
		fileOut.flush();
		fileOut.close();

		FileWriter fileWriter = new FileWriter("Conta2.txt", true);
		PrintWriter pw = new PrintWriter(fileWriter);
		for (Conta str : contas) {
			pw.write(str + System.lineSeparator());
		}
		pw.flush();
		pw.close();
		fileWriter.close();

	}

	public void deletarConta(ArrayList<String> listaConta1) throws IOException {
		if (arquivo.exists() == false) {
			arquivo = new File("Conta2.txt");
		}

		FileOutputStream fileOut = new FileOutputStream("Conta.txt", true);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

		FileOutputStream fileOutAux = new FileOutputStream("Conta2.txt");
		ObjectOutputStream objectOutAux = new ObjectOutputStream(fileOutAux);

		objectOutAux.writeObject(listaConta1);
		objectOut.flush();
		objectOut.close();
		fileOut.flush();
		fileOut.close();

		objectOutAux.flush();
		objectOutAux.close();
		fileOutAux.flush();
		fileOutAux.close();
	}

	public ArrayList<String> recuperarTodasContas() {
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

	public ArrayList<String> recuperaConta(String numeroConta) {
		try {
			File file = new File("Conta2.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String linha = "";

			ArrayList<String> linhas = new ArrayList<String>();

			while (linha != null) {
				linha = bufferedReader.readLine();
				linhas.add(linha);
				i++;

				for (int j = 0; j < linhas.size(); j++) {
					if (linhas.get(j).contains(numeroConta)) {
						return linhas;
					}
				}
			}

			fileReader.close();
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Cliente> recuperarCliente() {
		List<Cliente> c = new ArrayList<Cliente>();
		try{
			FileInputStream arquivoLeitura = new FileInputStream("Cliente.txt");
			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

			c = (List<Cliente>) objLeitura.readObject();
			objLeitura.close();
			arquivoLeitura.close();
			
			return c;
		}
		catch( Exception e ){
			e.printStackTrace( );
		}
		return null;
	}
}
