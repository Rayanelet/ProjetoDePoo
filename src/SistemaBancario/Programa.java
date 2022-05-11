
package SistemaBancario;

import java.io.IOException;
import java.util.Scanner;
import BancoNegocio.CriaConta;
public class Programa {
	static Scanner input = new Scanner(System.in);
	static boolean menu = true;
	static Banco banco = new Banco();
	static int opcao = 1;
	
	public static void main(String[] args) {
		while(menu == true ) {
			try {
				switch (menu()) {
				case 1:
					menuCliente();
					break;
					
				case 2:
					menuConta();
					break;
					
				default:
					System.out.println("Finalizando Serviço");
					menu = false;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}  	
	
	private static int menu() {
		System.out.println("1- Cliente");
		System.out.println("2- Conta");
		System.out.println("0- Sair");
		opcao = input.nextInt();
		
		return opcao;
	}
	
	private static void menuCliente() throws IOException {
		int menuCli = 0;
		System.out.println("1- Cadastrar Cliente");
		System.out.println("2- Pesquisar Cliente por CPF");
		System.out.println("3- Remover Cliente por CPF");
		System.out.println("4- Voltar ao menu principal");
		menuCli = input.nextInt();
		
		while(menu == true) {
			switch (menuCli) {
			case 1:
				banco.adicionarCliente();
				menuCliente();
				break;
				
			case 2:
				banco.recuperarCliente();
				menuCliente();
				break;
				
			case 3:
				banco.removeCliente();
				menuCliente();
				break;
				
			default:
				System.out.println("Voltando........");
				menu = false;
				menu();
				break;
			}
		}
		
	}
	
	public static void menuConta() {
		int menuCli = 0;
		System.out.println("1- Cadastrar Conta");
		System.out.println("2- Pesquisar Conta");
		System.out.println("3- Remover Conta");
		System.out.println("4- Depositar valor");
		System.out.println("5- Sacar valor");	
		System.out.println("6- Voltar ao menu principal");
		menuCli = input.nextInt();
		
		while(menu == true) {
			switch (menuCli) {
			case 1:
				banco.criarConta();
				menuConta();
				break;
				
			case 2:
				banco.recuperarConta();
				menuConta();
				break;
				
			case 3:
				banco.deletarConta();
				menuConta();
				break;
				
			case 4: 
			    banco.depositar();
	         	menuConta();
				break;
				
			case 5:
				banco.sacar();
				menuConta();
				break;		
				
		 default:
				System.out.println("Voltando........");
				menu = false;
				menu();
				break;
			}
		}
	}

	private static void Sacar() {
		// TODO Auto-generated method stub
		
	}
	
}