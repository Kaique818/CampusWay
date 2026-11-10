
package campusway;

public class Aresta {

    private int destino;
    private int distancia;

    public Aresta(int destino, int distancia) {
        this.destino = destino;
        this.distancia = distancia;
    }

    public int getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }
}
