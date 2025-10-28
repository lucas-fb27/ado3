import java.util.ArrayList;
import java.util.HashMap;

public class MapaService {
    private Grafo<String> mapa;

    public MapaService() {
        this.mapa = MapaHeavenBuilder.construirMapaBase();
    }

    public void escolherLado(String lado) {
        if (lado.equalsIgnoreCase("DEFENSOR")) {
            this.mapa = MapaHeavenBuilder.construirMapaBase();
            MapaHeavenBuilder.adicionarConexoesDefensor(this.mapa);
            System.out.println("Lado DEFENSOR escolhido");
        } else {
            this.mapa = MapaHeavenBuilder.construirMapaBase();
            System.out.println("Lado ATACANTE escolhido");
        }
    }

    public boolean pontoExiste(String ponto) {
        return mapa.getVertice(ponto) != null;
    }

    public void verProximos(String ponto, int n) {
        Vertice<String> v = mapa.getVertice(ponto);
        if (v == null) {
            System.out.println("Ponto nao encontrado");
            return;
        }

        HashMap<Vertice<String>, Double> distancias = mapa.calcularDistancias(ponto);
        ArrayList<String> pontos = new ArrayList<String>();
        ArrayList<Double> dists = new ArrayList<Double>();

        for (Vertice<String> vertice : mapa.getVertices()) {
            if (!vertice.getDado().equals(ponto)) {
                double dist = distancias.get(vertice);
                if (dist < Double.MAX_VALUE) {
                    pontos.add(vertice.getDado());
                    dists.add(dist);
                }
            }
        }

        // Ordenar (bubble sort simples)
        for (int i = 0; i < dists.size() - 1; i++) {
            for (int j = i + 1; j < dists.size(); j++) {
                if (dists.get(i) > dists.get(j)) {
                    double tempDist = dists.get(i);
                    dists.set(i, dists.get(j));
                    dists.set(j, tempDist);

                    String tempPonto = pontos.get(i);
                    pontos.set(i, pontos.get(j));
                    pontos.set(j, tempPonto);
                }
            }
        }

        System.out.println("\n" + n + " pontos mais proximos de " + ponto + ":");
        int limite = Math.min(n, pontos.size());
        for (int i = 0; i < limite; i++) {
            System.out.printf("%d. %s - %.2f segundos\n", (i + 1), pontos.get(i), dists.get(i));
        }
        System.out.println();
    }

    public void verCaminhoMaisRapido(String origem, String destino) {
        Vertice<String> vOrigem = mapa.getVertice(origem);
        Vertice<String> vDestino = mapa.getVertice(destino);

        if (vOrigem == null || vDestino == null) {
            System.out.println("Ponto nao encontrado");
            return;
        }

        ArrayList<String> caminho = mapa.encontrarCaminho(origem, destino);
        
        if (caminho.isEmpty()) {
            System.out.println("Nao ha caminho");
            return;
        }

        HashMap<Vertice<String>, Double> distancias = mapa.calcularDistancias(origem);
        double dist = distancias.get(vDestino);

        System.out.println("\nCaminho de " + origem + " ate " + destino + ":");
        System.out.printf("Tempo total: %.2f segundos\n", dist);
        for (int i = 0; i < caminho.size(); i++) {
            System.out.println((i + 1) + ". " + caminho.get(i));
        }
        System.out.println();
    }

    public void verTempoEntre(String origem, String destino) {
        Vertice<String> vOrigem = mapa.getVertice(origem);
        Vertice<String> vDestino = mapa.getVertice(destino);

        if (vOrigem == null || vDestino == null) {
            System.out.println("Ponto nao encontrado");
            return;
        }

        HashMap<Vertice<String>, Double> distancias = mapa.calcularDistancias(origem);
        double dist = distancias.get(vDestino);

        if (dist >= Double.MAX_VALUE) {
            System.out.println("Nao ha caminho");
            return;
        }

        System.out.printf("\nTempo de %s ate %s: %.2f segundos\n\n", origem, destino, dist);
    }

    public void verTempoParaBombs(String ponto) {
        Vertice<String> v = mapa.getVertice(ponto);
        if (v == null) {
            System.out.println("Ponto nao encontrado");
            return;
        }

        HashMap<Vertice<String>, Double> distancias = mapa.calcularDistancias(ponto);

        double tempoA = distancias.get(mapa.getVertice("Bomb A"));
        double tempoB = distancias.get(mapa.getVertice("Bomb B"));
        double tempoC = distancias.get(mapa.getVertice("Bomb C"));

        System.out.println("\nTempo ate os Bombs de " + ponto + ":");
        System.out.printf("Bomb A: %.2f segundos\n", tempoA);
        System.out.printf("Bomb B: %.2f segundos\n", tempoB);
        System.out.printf("Bomb C: %.2f segundos\n", tempoC);
        System.out.println();
    }
}
