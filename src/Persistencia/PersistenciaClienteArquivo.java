package Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import SistemaBancario.Cliente;

public class PersistenciaClienteArquivo {

	Cliente c;
	File arquivo = new File("Cliente.txt");
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ObjectOutputStream output;
	ObjectInputStream input;

	int i = 1;

	public void gravarCliente(ArrayList<Cliente> clientes) throws IOException, ClassNotFoundException {
		if (arquivo.exists() == false) {
			arquivo = new File("Cliente.txt");
		}

		FileOutputStream fileOut = new FileOutputStream("Cliente.txt", true);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(clientes);
		objectOut.flush();
		objectOut.close();
		fileOut.flush();
		fileOut.close();

		FileWriter fileWriter = new FileWriter("Cliente2.txt", true);
		PrintWriter pw = new PrintWriter(fileWriter);
		for (Cliente str : clientes) {
			pw.write(str + System.lineSeparator());
		}
		pw.flush();
		pw.close();
		// fileWriter.flush();
		fileWriter.close();
	}

	public void deletarCliente(ArrayList<String> listaCliente1) throws IOException {
		if (arquivo.exists() == false) {
			arquivo = new File("Cliente2.txt");
		}

		FileOutputStream fileOut = new FileOutputStream("Cliente.txt", true);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

		FileOutputStream fileOutAux = new FileOutputStream("Cliente2.txt");
		ObjectOutputStream objectOutAux = new ObjectOutputStream(fileOutAux);

		objectOutAux.writeObject(listaCliente1);
		objectOut.flush();
		objectOut.close();
		fileOut.flush();
		fileOut.close();

		objectOutAux.flush();
		objectOutAux.close();
		fileOutAux.flush();
		fileOutAux.close();
	}

	public ArrayList<String> recuperarTodosClientes() {
		try {
			File file = new File("Cliente2.txt");
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

	public ArrayList<String> recuperaCliente(String cpf) {
		List<Cliente> clientes = new ArrayList<>();

		try {
			File file = new File("Cliente2.txt");
			FileReader fileReader = new FileReader(file);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha = "";

			ArrayList<String> linhas = new ArrayList<String>();

			while (linha != null) {
				linha = bufferedReader.readLine();
				linhas.add(linha);
				i++;

				for (int j = 0; j < linhas.size(); j++) {
					if (linhas.get(j).contains(cpf)) {
						return linhas;
						// System.out.println(linhas.get(j));
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
}