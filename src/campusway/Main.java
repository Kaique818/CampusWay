
package campusway;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        Grafo grafo = new Grafo();

        // Nomes dos locais, seguindo seus respectivos IDs
        String[] nomes = {
            "Portaria",
            "Biblioteca",
            "Laboratório de Informática",
            "Salas de Aula",
            "Secretaria",
            "Estacionamento",
            "Refeitório",
            "Quadra Esportiva",
            "Auditório"
        };

        int opcao = -1;

        // Menu principal
        while (opcao != 0) {

            System.out.println("\n==============================");
            System.out.println("         CAMPUSWAY");
            System.out.println("==============================");
            System.out.println("1 - Visualizar locais");
            System.out.println("2 - Consultar conexões");
            System.out.println("3 - Encontrar menor rota");
            System.out.println("0 - Sair");
            System.out.println("==============================");

            System.out.print("Escolha uma opção: ");

            if (entrada.hasNextInt()) {
                opcao = entrada.nextInt();
                entrada.nextLine();
            } else {
                System.out.println("Digite um número válido!");
                entrada.nextLine();
                continue;
            }

            switch (opcao) {

                case 1:
                    grafo.mostrarLocais();
                    break;

                case 2:
                    grafo.mostrarConexoes();
                    break;

                case 3:

                    System.out.println("\n===== ENCONTRAR MENOR ROTA =====");

                    grafo.mostrarLocais();

                    System.out.print("\nDigite o ID do local de origem: ");
                    int origem = lerNumero(entrada);

                    System.out.print("Digite o ID do local de destino: ");
                    int destino = lerNumero(entrada);

                    if (origem < 0 || origem >= nomes.length
                            || destino < 0 || destino >= nomes.length) {

                        System.out.println("ID inválido! Tente novamente.");

                    } else {

                        ResultadoRota resultado =
                                grafo.encontrarMenorRota(origem, destino);

                        if (resultado.getEncontrado()) {

                            System.out.println("\n===== RESULTADO DA ROTA =====");

                            ArrayList<Integer> caminho = resultado.getCaminho();

                            System.out.println("Origem: " + nomes[origem]);
                            System.out.println("Destino: " + nomes[destino]);

                            System.out.print("Caminho: ");

                            for (int i = 0; i < caminho.size(); i++) {

                                int id = caminho.get(i);

                                System.out.print(nomes[id]);

                                if (i < caminho.size() - 1) {
                                    System.out.print(" -> ");
                                }
                            }

                            System.out.println("\nDistância total: "
                                    + resultado.getDistanciaTotal() + " metros");

                        } else {

                            System.out.println("Não foi possível encontrar uma rota.");
                        }
                    }

                    break;

                case 0:
                    System.out.println("\nEncerrando o CampusWay...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        entrada.close();
    }

    // Método para ler números inteiros
    public static int lerNumero(Scanner entrada) {

        while (!entrada.hasNextInt()) {
            System.out.println("Digite um número válido!");
            entrada.nextLine();
        }

        int numero = entrada.nextInt();
        entrada.nextLine();

        return numero;
    }
}
