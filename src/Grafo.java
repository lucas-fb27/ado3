import java.util.ArrayList;
import java.util.HashMap;

public class Grafo<TIPO> {
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<TIPO>>();
        this.arestas = new ArrayList<Aresta<TIPO>>();
    }

    public void adicionarVertice(TIPO dado) {
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        Aresta<TIPO> aresta = new Aresta<TIPO>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<TIPO> getVertice(TIPO dado) {
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado().equals(dado)) {
                return this.vertices.get(i);
            }
        }
        return null;
    }

    public ArrayList<Vertice<TIPO>> getVertices() {
        return vertices;
    }

    // Calcula distancia entre dois pontos usando Dijkstra
    public double getDistancia(TIPO origem, TIPO destino) {
        HashMap<Vertice<TIPO>, Double> distancias = calcularDistancias(origem);
        Vertice<TIPO> vDestino = getVertice(destino);
        if (vDestino == null || !distancias.containsKey(vDestino)) {
            return Double.MAX_VALUE;
        }
        return distancias.get(vDestino);
    }

    // Retorna o caminho entre dois pontos
    public ArrayList<TIPO> encontrarCaminho(TIPO origem, TIPO destino) {
        return getCaminho(origem, destino);
    }

    // Retorna o caminho entre dois pontos
    public ArrayList<TIPO> getCaminho(TIPO origem, TIPO destino) {
        HashMap<Vertice<TIPO>, Double> distancias = new HashMap<Vertice<TIPO>, Double>();
        HashMap<Vertice<TIPO>, Vertice<TIPO>> predecessores = new HashMap<Vertice<TIPO>, Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> naoVisitados = new ArrayList<Vertice<TIPO>>();

        // Inicializar
        for (Vertice<TIPO> v : this.vertices) {
            if (v.getDado().equals(origem)) {
                distancias.put(v, 0.0);
            } else {
                distancias.put(v, Double.MAX_VALUE);
            }
            predecessores.put(v, null);
            naoVisitados.add(v);
        }

        // Processar vertices
        while (naoVisitados.size() > 0) {
            Vertice<TIPO> atual = null;
            double menorDist = Double.MAX_VALUE;

            for (Vertice<TIPO> v : naoVisitados) {
                double dist = distancias.get(v);
                if (dist < menorDist) {
                    menorDist = dist;
                    atual = v;
                }
            }

            if (atual == null || menorDist == Double.MAX_VALUE) {
                break;
            }

            naoVisitados.remove(atual);

            for (Aresta<TIPO> aresta : atual.getArestasSaida()) {
                Vertice<TIPO> vizinho = aresta.getFim();
                if (naoVisitados.contains(vizinho)) {
                    double novaDist = distancias.get(atual) + aresta.getPeso();
                    if (novaDist < distancias.get(vizinho)) {
                        distancias.put(vizinho, novaDist);
                        predecessores.put(vizinho, atual);
                    }
                }
            }
        }

        // Reconstruir caminho
        ArrayList<TIPO> caminho = new ArrayList<TIPO>();
        Vertice<TIPO> vDestino = getVertice(destino);
        if (vDestino == null || distancias.get(vDestino) == Double.MAX_VALUE) {
            return caminho;
        }

        Vertice<TIPO> atual = vDestino;
        while (atual != null) {
            caminho.add(0, atual.getDado());
            atual = predecessores.get(atual);
        }

        return caminho;
    }

    // Calcula todas as distancias a partir de uma origem
    public HashMap<Vertice<TIPO>, Double> calcularDistancias(TIPO origem) {
        HashMap<Vertice<TIPO>, Double> distancias = new HashMap<Vertice<TIPO>, Double>();
        ArrayList<Vertice<TIPO>> naoVisitados = new ArrayList<Vertice<TIPO>>();

        // Inicializar
        for (Vertice<TIPO> v : this.vertices) {
            if (v.getDado().equals(origem)) {
                distancias.put(v, 0.0);
            } else {
                distancias.put(v, Double.MAX_VALUE);
            }
            naoVisitados.add(v);
        }

        // Processar vertices
        while (naoVisitados.size() > 0) {
            Vertice<TIPO> atual = null;
            double menorDist = Double.MAX_VALUE;

            for (Vertice<TIPO> v : naoVisitados) {
                double dist = distancias.get(v);
                if (dist < menorDist) {
                    menorDist = dist;
                    atual = v;
                }
            }

            if (atual == null || menorDist == Double.MAX_VALUE) {
                break;
            }

            naoVisitados.remove(atual);

            for (Aresta<TIPO> aresta : atual.getArestasSaida()) {
                Vertice<TIPO> vizinho = aresta.getFim();
                if (naoVisitados.contains(vizinho)) {
                    double novaDist = distancias.get(atual) + aresta.getPeso();
                    if (novaDist < distancias.get(vizinho)) {
                        distancias.put(vizinho, novaDist);
                    }
                }
            }
        }

        return distancias;
    }
}