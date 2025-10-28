/**
 * Classe de demonstração com exemplos de uso do sistema
 */
public class TesteMapaHeaven {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   TESTES - MAPA HEAVEN");
        System.out.println("========================================\n");
        
        MapaService service = new MapaService();
        
        // Teste 1: Lado Atacante
        System.out.println("TESTE 1: LADO ATACANTE\n");
        service.escolherLado("ATACANTE");
        
        // Teste 2: Caminho mais rápido
        System.out.println("\nTESTE 2: CAMINHO MAIS RAPIDO");
        System.out.println("Base Ataque -> Bomb C\n");
        service.verCaminhoMaisRapido("Base Ataque", "Bomb C");
        
        pausar();
        
        // Teste 3: Tempo entre pontos
        System.out.println("TESTE 3: TEMPO ENTRE PONTOS");
        System.out.println("Meio -> Bomb B\n");
        service.verTempoEntre("Meio", "Bomb B");
        
        pausar();
        
        // Teste 4: Pontos mais próximos
        System.out.println("TESTE 4: PONTOS MAIS PROXIMOS");
        System.out.println("A partir de: Link C\n");
        service.verProximos("Link C", 3);
        
        pausar();
        
        // Teste 5: Tempo até Bombs
        System.out.println("TESTE 5: TEMPO ATE BOMBS");
        System.out.println("A partir de: Lobby A\n");
        service.verTempoParaBombs("Lobby A");
        
        pausar();
        
        // Teste 6: Lado Defensor
        System.out.println("TESTE 6: LADO DEFENSOR\n");
        service.escolherLado("DEFENSOR");
        
        // Teste 7: Conexão exclusiva Defensor
        System.out.println("\nTESTE 7: CONEXAO EXCLUSIVA DEFENSOR");
        System.out.println("Link A -> Bomb A (conexao direta)\n");
        service.verCaminhoMaisRapido("Link A", "Bomb A");
        
        pausar();
        
        // Teste 8: Comparação Atacante vs Defensor
        System.out.println("TESTE 8: COMPARACAO ATACANTE vs DEFENSOR\n");
        System.out.println("Rota: Link A -> Bomb A\n");
        
        System.out.println("--- LADO ATACANTE ---");
        MapaService serviceAtacante = new MapaService();
        serviceAtacante.escolherLado("ATACANTE");
        serviceAtacante.verTempoEntre("Link A", "Bomb A");
        
        System.out.println("--- LADO DEFENSOR ---");
        MapaService serviceDefensor = new MapaService();
        serviceDefensor.escolherLado("DEFENSOR");
        serviceDefensor.verTempoEntre("Link A", "Bomb A");
        
        System.out.println("========================================");
        System.out.println("   TESTES CONCLUIDOS");
        System.out.println("========================================");
    }
    
    private static void pausar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
