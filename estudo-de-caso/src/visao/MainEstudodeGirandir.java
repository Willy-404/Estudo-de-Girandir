package visao;

import java.util.Scanner;

import controle.EquipamentoDAO;
import modelo.Equipamento;

public class MainEstudodeGirandir {
	
	static Scanner leitura = new Scanner(System.in);

	//metodo alterar valores String;
	public static String altString() {
		String NomeParaSubstituir = leitura.nextLine();
		return NomeParaSubstituir;
	}
	//metodo alterar numeros inteiros;
	public static int altInt() {
		int numInt = Integer.valueOf(leitura.nextLine());
		return numInt;
	}
	//metodo alterar numeros float;
	public static float altFloat() {
		float numFloat = Float.valueOf(leitura.nextLine());
		return numFloat;
	}
	public static void main(String[] args) {
		
		EquipamentoDAO dao = EquipamentoDAO.getInstancia();
		int op;
		boolean EquipamentoEncontrado;
		do {
			
			System.out.println("");
			System.out.println("*****SISTEMA DE CADASTRO DE EQUIPAMENTOS ELETRÔNICOS*****");
			System.out.println("Escolha uma Opcao");
			System.out.println("Digite 1 para Cadastrar Equipamento no Sistema");
			System.out.println("Digite 2 para Visualizar os Equipamentos ja cadastrados");
			System.out.println("Digite 3 para Deletar um Equipamento do Sistema");
			System.out.println("Digite 4 para Alterar valores");
			System.out.println("Digite 0 para Finalizar o programa");
			System.out.println("");
			String opcao = altString();
			op = Integer.valueOf(opcao);

			switch (op) {

			case 1:
				Equipamento equipamento1 = new Equipamento();

				System.out.println("Digite o numero de serie do produto: ");
				String numseriedoequipamento = altString();
				int numseriedoequipamento1 = Integer.valueOf(numseriedoequipamento);
				equipamento1.setNumerodeserie(numseriedoequipamento1);
				while (numseriedoequipamento1 < 0) {
					System.out.println("Numero de serie invalido, digite de novo: ");
					String numserieconferir = altString();
					numseriedoequipamento1 = Integer.valueOf(numserieconferir);
					equipamento1.setNumerodeserie(numseriedoequipamento1);
				}
				System.out.println("Digite o nome do produto: ");
				String nomedoequipamento = altString();
				equipamento1.setNome(nomedoequipamento);

				System.out.println("Digite a marca: ");
				String marcadoequipamento = altString();
				equipamento1.setMarca(marcadoequipamento);

				System.out.println("Data de fabricacao do produto (dd/mm/aa): ");
				System.out.println("Digite o Dia: ");
				String dd = altString();
				while ((Integer.valueOf(dd) > 31) || (Integer.valueOf(dd)) < 1) {
					System.out.println("Digite um dia Valido!");
					String ddverif = altString();
					dd = ddverif;
				}
				System.out.println("Digite o Mes: ");
				String mm = altString();
				while ((Integer.valueOf(mm) > 12) || (Integer.valueOf(mm)) < 1) {
					System.out.println("Digite um Mes Valido!");
					String mmverif = altString();
					mm = mmverif;
				}
				System.out.println("Digite o Ano: ");
				String aa = altString();
				while ((Integer.valueOf(aa) > 2024) || (Integer.valueOf(aa)) < 1900) {
					System.out.println("Digite um Ano Valido!");
					String aaverif = altString();
					aa = aaverif;
				}
				equipamento1.setDatafab(dd + "/" + mm + "/" + aa);
				

				System.out.println("Digite o preco do produto: ");
				String precodoequipamento = altString();
				float precodoequipamento1 = Float.valueOf(precodoequipamento);
				equipamento1.setPreco(precodoequipamento1);
				while (precodoequipamento1 < 1) {
					System.out.println("Preco invalido, digite um valor real: ");
					String precoconferir = altString();
					precodoequipamento1 = Float.valueOf(precoconferir);
				}
				dao.cadastro(equipamento1);
				break;

			case 2:
				for (Equipamento equip : dao.listar()) {
					//listar
					System.out.println("Codigo: " + equip.getCodigo());
					System.out.println("Nome: " + equip.getNome());
					System.out.println("Numero de Serie: " + equip.getNumerodeserie());
					System.out.println("Marca: " + equip.getMarca());
					System.out.println("Preco: R$" + equip.getPreco());
					System.out.println("Data de Fabricacao: " + equip.getDatafab());
					System.out.println("");
				}
				break;

			case 3:
				System.out.println("***Digite o codigo do equipamento para Exclusao***");
				Integer CodigoParaDeletar = altInt();
				EquipamentoEncontrado = false;
				for (Equipamento equipamento : dao.listar()) {
					if (CodigoParaDeletar.equals(equipamento.getCodigo())) {
						dao.remover(equipamento);
						EquipamentoEncontrado = true;
						break;
					}
				}
				if (EquipamentoEncontrado == true) {
					System.out.println("Equipamento removido com sucesso!");
				} else {
					System.out.println("Equipamento não encontrado.");
				}
				System.out.println("");
				break;

			case 4:
				EquipamentoEncontrado = false;
				System.out.println("Digite o codigo do equipamento que deseja alterar");
				Integer CodigoParaAlterar = Integer.valueOf(altString());
				for (Equipamento equipamento : dao.listar()) {
					if (CodigoParaAlterar.equals(equipamento.getCodigo())) {
						EquipamentoEncontrado = true;
						System.out.println("Digite o que deseja alterar:");
						System.out.println("1-Nome");
						System.out.println("2-Numero de Serie");
						System.out.println("3-Marca");
						System.out.println("4-Preco");
						System.out.println("5-Data de Fabricacao");
						System.out.println("0-Cancelar");
						int po = Integer.valueOf(altString());
						switch (po) {
						case 1:
							System.out.println("Digite um novo nome para o Equipamento");
							String NomeParaSubstituir = altString();
							equipamento.setNome(NomeParaSubstituir);
							System.out.println("Nome alterado com sucesso!");
							po = 0;
							break;
						case 2:
							System.out.println("Digite um novo numero de Serie:");
							int NumSerieSubstituir = altInt();
							equipamento.setNumerodeserie(NumSerieSubstituir);
							while (NumSerieSubstituir < 0) {
								System.out.println("Numero de serie invalido!");
								System.out.println("Digite um novo numero de Serie");
								NumSerieSubstituir = altInt();
								equipamento.setNumerodeserie(NumSerieSubstituir);
							}
							System.out.println("Numero de Serie alterado com sucesso!");
							po = 0;
							break;
						case 3:
							System.out.println("Digite a nova marca do equipamento: ");
							String MarcaParaSubstituir = altString();
							equipamento.setMarca(MarcaParaSubstituir);
							System.out.println("Marca alterada com sucesso!");
							po = 0;
							break;
						case 4:
							System.out.println("Digite um novo Preco para o Equipamento: ");
							float PrecoSubstituir = altFloat();
							equipamento.setPreco(PrecoSubstituir);
							while (PrecoSubstituir < 1) {
								System.out.println("Preco invalido, digite um valor real: ");
								PrecoSubstituir = altFloat();
							}
							System.out.println("Preco alterado com sucesso!");
							po = 0;
							break;
						case 5:
							System.out.println("Nova data de fabricacao do produto (dd/mm/aa): ");
							System.out.println("Digite o Dia: ");
							String dd1 = altString();
							while ((Integer.valueOf(dd1) > 31) || (Integer.valueOf(dd1)) < 1) {
								System.out.println("Digite um dia Valido!");
								String ddverif = altString();
								dd1 = ddverif;
							}
							System.out.println("Digite o Mes: ");
							String mm1 = altString();
							while ((Integer.valueOf(mm1) > 12) || (Integer.valueOf(mm1)) < 1) {
								System.out.println("Digite um Mes Valido!");
								String mmverif = altString();
								mm1 = mmverif;
							}
							System.out.println("Digite o Ano: ");
							String aa1 = altString();
							while ((Integer.valueOf(aa1) > 2024) || (Integer.valueOf(aa1)) < 1900) {
								System.out.println("Digite um Ano Valido!");
								String aaverif = altString();
								aa1 = aaverif;
							}
							String DataSubstituir = altString();
							equipamento.setDatafab(DataSubstituir);
							equipamento.setDatafab(dd1 + "/" + mm1 + "/" + aa1);
							System.out.println("Data alterada com sucesso!");
							po = 0;
							break;
						}
						while (po != 0);
						System.out.println("Fim das Alteracoes");
						break;
					}
				}
				if (EquipamentoEncontrado == false) {
					System.out.println("Equipamento não encontrado, verifique o codigo");
				}
				break;
			}
		}while(op!=0);System.out.println("Fim do Programa");
}}