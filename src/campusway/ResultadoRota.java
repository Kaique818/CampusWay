
package campusway;

import java.util.ArrayList;

public class ResultadoRota {

    private boolean encontrado;
    private ArrayList<Integer> caminho;
    private int distanciaTotal;

    public ResultadoRota(boolean encontrado, ArrayList<Integer> caminho, int distanciaTotal) {
        this.encontrado = encontrado;
        this.caminho = caminho;
        this.distanciaTotal = distanciaTotal;
    }

    public boolean getEncontrado() {
        return encontrado;
    }

    public ArrayList<Integer> getCaminho() {
        return caminho;
    }

    public int getDistanciaTotal() {
        return distanciaTotal;
    }
}
