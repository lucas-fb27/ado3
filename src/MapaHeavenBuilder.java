public class MapaHeavenBuilder {
    
    /**
     * Constrói o grafo base do mapa Heaven (lado Atacante)
     * O grafo é não-direcionado, então cada aresta é adicionada nos dois sentidos
     */
    public static Grafo<String> construirMapaBase() {
        Grafo<String> mapa = new Grafo<String>();
        
        // Adicionar todos os vértices (pontos do mapa)
        String[] vertices = {
            "Base Ataque", "Lobby C", "Long C", "Quadrado", "Bomb C",
            "Feno", "DD", "Janela C", "Link C", "Base Defensora",
            "Jardim A", "Janela Meio", "Meio", "Bomb B", "Link A",
            "Lobby A", "Long A", "Bomb A", "Rato", "Ceu"
        };
        
        for (String vertice : vertices) {
            mapa.adicionarVertice(vertice);
        }
        
        // BOMB C - Lado Atacante
        adicionarArestaBidirecional(mapa, 5.57, "Base Ataque", "Lobby C");
        adicionarArestaBidirecional(mapa, 2.36, "Lobby C", "Long C");
        adicionarArestaBidirecional(mapa, 3.21, "Lobby C", "Quadrado");
        adicionarArestaBidirecional(mapa, 4.78, "Quadrado", "Bomb C");
        adicionarArestaBidirecional(mapa, 6.69, "Long C", "Bomb C");
        adicionarArestaBidirecional(mapa, 6.53, "Base Ataque", "Feno");
        adicionarArestaBidirecional(mapa, 3.73, "Feno", "DD");
        adicionarArestaBidirecional(mapa, 4.38, "DD", "Bomb C");
        adicionarArestaBidirecional(mapa, 3.34, "DD", "Janela C");
        adicionarArestaBidirecional(mapa, 2.29, "Janela C", "Link C");
        adicionarArestaBidirecional(mapa, 3.28, "Link C", "Bomb C");
        adicionarArestaBidirecional(mapa, 6.61, "Link C", "Base Defensora");
        
        // BOMB B - Lado Atacante
        adicionarArestaBidirecional(mapa, 3.26, "Jardim A", "Janela Meio");
        adicionarArestaBidirecional(mapa, 2.36, "Janela Meio", "Meio");
        adicionarArestaBidirecional(mapa, 2.96, "Meio", "Bomb B");
        adicionarArestaBidirecional(mapa, 2.75, "Meio", "Feno");
        adicionarArestaBidirecional(mapa, 3.67, "Bomb B", "Link A");
        adicionarArestaBidirecional(mapa, 3.73, "Bomb B", "Link C");
        
        // BOMB A - Lado Atacante
        adicionarArestaBidirecional(mapa, 5.90, "Base Ataque", "Jardim A");
        adicionarArestaBidirecional(mapa, 2.56, "Jardim A", "Lobby A");
        adicionarArestaBidirecional(mapa, 4.06, "Lobby A", "Long A");
        adicionarArestaBidirecional(mapa, 3.49, "Long A", "Bomb A");
        adicionarArestaBidirecional(mapa, 3.01, "Lobby A", "Rato");
        adicionarArestaBidirecional(mapa, 4.53, "Rato", "Bomb A");
        adicionarArestaBidirecional(mapa, 4.3, "Link A", "Bomb A");
        
        return mapa;
    }
    
    /**
     * Adiciona conexões extras para o lado Defensor
     */
    public static void adicionarConexoesDefensor(Grafo<String> mapa) {
        adicionarArestaBidirecional(mapa, 7.2, "Link A", "Ceu");
        adicionarArestaBidirecional(mapa, 5.7, "Link A", "Base Defensora");
        adicionarArestaBidirecional(mapa, 7.6, "Base Defensora", "Ceu");
    }
    
    /**
     * Helper para adicionar arestas bidirecionais (grafo não-direcionado)
     */
    private static void adicionarArestaBidirecional(Grafo<String> mapa, double peso, String origem, String destino) {
        mapa.adicionarAresta(peso, origem, destino);
        mapa.adicionarAresta(peso, destino, origem);
    }
}
