package campusway;

import java.util.ArrayList;

public class Grafo {

    /*ArrayList é uma estrutura de dados semelhante a um array (vetor), mas 
    com a capacidade de ter o seu tamanho ajustado dinamicamente*/
    private ArrayList<Local> locais;
    private ArrayList<ArrayList<Aresta>> listaAdjacencia;

    public Grafo() {
        locais = new ArrayList<>();
        listaAdjacencia = new ArrayList<>();

        // Cadastro dos locais
        adicionarLocal("Portaria");
        adicionarLocal("Biblioteca");
        adicionarLocal("Laboratório de Informática");
        adicionarLocal("Salas de Aula");
        adicionarLocal("Secretaria");
        adicionarLocal("Estacionamento");
        adicionarLocal("Refeitório");
        adicionarLocal("Quadra Esportiva");
        adicionarLocal("Auditório");

        // Cadastro das conexões
        adicionarConexao(0, 1, 100);
        adicionarConexao(0, 5, 120);
        adicionarConexao(0, 4, 180);
        adicionarConexao(1, 2, 80);
        adicionarConexao(1, 8, 130);
        adicionarConexao(2, 3, 90);
        adicionarConexao(2, 6, 110);
        adicionarConexao(3, 4, 70);
        adicionarConexao(3, 7, 140);
        adicionarConexao(3, 8, 150);
        adicionarConexao(4, 5, 100);
        adicionarConexao(4, 6, 90);
        adicionarConexao(5, 7, 160);
        adicionarConexao(6, 7, 120);
        adicionarConexao(6, 8, 100);
    }

    public void adicionarLocal(String nome) {
        int id = locais.size();

        Local local = new Local(id, nome);
        locais.add(local);

        listaAdjacencia.add(new ArrayList<Aresta>());
    }

    public void adicionarConexao(int origem, int destino, int distancia) {

        if (origem < 0 || origem >= locais.size()
                || destino < 0 || destino >= locais.size()) {
            System.out.println("Local inválido!");
            return;
        }

        Aresta ida = new Aresta(destino, distancia);
        Aresta volta = new Aresta(origem, distancia);

        listaAdjacencia.get(origem).add(ida);
        listaAdjacencia.get(destino).add(volta);
    }

    public void mostrarLocais() {
        System.out.println("\n===== LOCAIS DO CAMPUS =====");

        for (Local local : locais) {
            System.out.println(local);
        }
    }

    public void mostrarConexoes() {
        System.out.println("\n===== CONEXÕES DO CAMPUS =====");

        for (int origem = 0; origem < listaAdjacencia.size(); origem++) {

            ArrayList<Aresta> conexoes = listaAdjacencia.get(origem);

            for (Aresta aresta : conexoes) {

                if (origem < aresta.getDestino()) {
                    String nomeOrigem = locais.get(origem).getNome();
                    String nomeDestino = locais.get(aresta.getDestino()).getNome();

                    System.out.println(nomeOrigem + " -> "
                            + nomeDestino + " ("
                            + aresta.getDistancia() + " metros)");
                }
            }
        }
    }

    public ResultadoRota encontrarMenorRota(int origem, int destino) {

        int quantidade = locais.size();

        // Verifica se os IDs são válidos
        if (origem < 0 || origem >= quantidade
                || destino < 0 || destino >= quantidade) {

            return new ResultadoRota(false, new ArrayList<Integer>(), -1);
        }

        // Criação dos vetores
        int[] distancias = new int[quantidade];
        boolean[] visitado = new boolean[quantidade];
        int[] anterior = new int[quantidade];

        // Inicialização dos vetores
        for (int i = 0; i < quantidade; i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitado[i] = false;
            anterior[i] = -1;
        }

        // A distância da origem até ela mesma é zero
        distancias[origem] = 0;

        // Execução do algoritmo de Dijkstra
        for (int i = 0; i < quantidade; i++) {

            int menorDistancia = Integer.MAX_VALUE;
            int atual = -1;

            // Procura o local não visitado com menor distância
            for (int j = 0; j < quantidade; j++) {

                if (!visitado[j] && distancias[j] < menorDistancia) {
                    menorDistancia = distancias[j];
                    atual = j;
                }
            }

            // Verifica se existe algum local alcançável
            if (atual == -1) {
                break;
            }

            // Marca o local como visitado
            visitado[atual] = true;

            // Se chegou ao destino, pode encerrar
            if (atual == destino) {
                break;
            }

            // Verifica as conexões do local atual
            for (Aresta aresta : listaAdjacencia.get(atual)) {

                int vizinho = aresta.getDestino();
                int distancia = aresta.getDistancia();

                if (!visitado[vizinho]) {

                    int novaDistancia = distancias[atual] + distancia;

                    // Atualiza se encontrou um caminho menor
                    if (novaDistancia < distancias[vizinho]) {
                        distancias[vizinho] = novaDistancia;
                        anterior[vizinho] = atual;
                    }
                }
            }
        }

        // Verifica se foi possível encontrar uma rota
        if (distancias[destino] == Integer.MAX_VALUE) {
            return new ResultadoRota(false, new ArrayList<Integer>(), -1);
        }

        // Reconstrói o caminho encontrado
        ArrayList<Integer> caminho = new ArrayList<Integer>();

        int atual = destino;

        while (atual != -1) {
            caminho.add(0, atual);
            atual = anterior[atual];
        }

        // Retorna o resultado da rota
        return new ResultadoRota(true, caminho, distancias[destino]);
    }

}
