import java.util.Scanner;

/**
 * Classe principal com interface CLI para interagir com o mapa Heaven
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MapaService service = new MapaService();
        String pontoAtual = "";

        System.out.println("========================================");
        System.out.println("   MAPA HEAVEN - VALORANT");
        System.out.println("========================================\n");

        // Escolher lado
        System.out.println("Escolha seu lado:");
        System.out.println("1. ATACANTE");
        System.out.println("2. DEFENSOR");
        System.out.print("Opcao: ");

        String opcaoLado = scanner.nextLine().trim();
        if (opcaoLado.equals("2")) {
            service.escolherLado("DEFENSOR");
        } else {
            service.escolherLado("ATACANTE");
        }

        System.out.println();

        // Loop principal
        String opcao;
        do {
            exibirMenu(pontoAtual);
            System.out.print("Opcao: ");
            opcao = scanner.nextLine().trim();
            System.out.println();

            switch (opcao) {
                case "1":
                    if (pontoAtual.isEmpty()) {
                        System.out.print("Digite o ponto atual: ");
                        pontoAtual = scanner.nextLine().trim();
                        if (!service.pontoExiste(pontoAtual)) {
                            System.out.println("Ponto '" + pontoAtual + "' nao existe no mapa.\n");
                            pontoAtual = "";
                            break;
                        }
                    }
                    System.out.print("Quantos pontos proximos deseja ver? ");
                    try {
                        int n = Integer.parseInt(scanner.nextLine().trim());
                        service.verProximos(pontoAtual, n);
                    } catch (NumberFormatException e) {
                        System.out.println("Numero invalido.\n");
                    }
                    break;

                case "2":
                    System.out.print("Digite a origem: ");
                    String origem = scanner.nextLine().trim();
                    if (!service.pontoExiste(origem)) {
                        System.out.println("Ponto '" + origem + "' nao existe no mapa.\n");
                        break;
                    }
                    System.out.print("Digite o destino: ");
                    String destino = scanner.nextLine().trim();
                    if (!service.pontoExiste(destino)) {
                        System.out.println("Ponto '" + destino + "' nao existe no mapa.\n");
                        break;
                    }
                    service.verCaminhoMaisRapido(origem, destino);
                    break;

                case "3":
                    System.out.print("Digite a origem: ");
                    String origem2 = scanner.nextLine().trim();
                    if (!service.pontoExiste(origem2)) {
                        System.out.println("Ponto '" + origem2 + "' nao existe no mapa.\n");
                        break;
                    }
                    System.out.print("Digite o destino: ");
                    String destino2 = scanner.nextLine().trim();
                    if (!service.pontoExiste(destino2)) {
                        System.out.println("Ponto '" + destino2 + "' nao existe no mapa.\n");
                        break;
                    }
                    service.verTempoEntre(origem2, destino2);
                    break;

                case "4":
                    if (pontoAtual.isEmpty()) {
                        System.out.print("Digite o ponto atual: ");
                        pontoAtual = scanner.nextLine().trim();
                        if (!service.pontoExiste(pontoAtual)) {
                            System.out.println("Ponto '" + pontoAtual + "' nao existe no mapa.\n");
                            pontoAtual = "";
                            break;
                        }
                    }
                    service.verTempoParaBombs(pontoAtual);
                    break;

                case "5":
                    System.out.print("Digite o novo ponto atual: ");
                    String novoPonto = scanner.nextLine().trim();
                    if (!service.pontoExiste(novoPonto)) {
                        System.out.println("Ponto '" + novoPonto + "' nao existe no mapa.\n");
                        break;
                    }
                    pontoAtual = novoPonto;
                    System.out.println("Ponto atual: " + pontoAtual + "\n");
                    break;

                case "6":
                    System.out.println("Escolha o lado:");
                    System.out.println("1. ATACANTE");
                    System.out.println("2. DEFENSOR");
                    System.out.print("Opcao: ");
                    String novoLado = scanner.nextLine().trim();
                    if (novoLado.equals("2")) {
                        service.escolherLado("DEFENSOR");
                    } else {
                        service.escolherLado("ATACANTE");
                    }
                    System.out.println();
                    break;

                case "7":
                    listarPontos();
                    break;

                case "0":
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida.\n");
            }

            if (!opcao.equals("0")) {
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (!opcao.equals("0"));

        scanner.close();
    }

    private static void exibirMenu(String pontoAtual) {
        System.out.println("========================================");
        System.out.println("         MENU PRINCIPAL");
        System.out.println("========================================");
        if (!pontoAtual.isEmpty()) {
            System.out.println("Ponto atual: " + pontoAtual);
        }
        System.out.println("1. Ver pontos mais proximos");
        System.out.println("2. Ver caminho mais rapido");
        System.out.println("3. Ver tempo entre dois pontos");
        System.out.println("4. Ver tempo ate Bomb Sites (A, B, C)");
        System.out.println("5. Mudar ponto atual");
        System.out.println("6. Trocar lado (Atacante/Defensor)");
        System.out.println("7. Listar todos os pontos do mapa");
        System.out.println("0. Sair");
        System.out.println("========================================");
    }

    private static void listarPontos() {
        System.out.println("\nPONTOS DO MAPA HEAVEN");
        System.out.println("========================================");

        String[] pontos = {
                "Base Ataque", "Base Defensora",
                "Bomb A", "Bomb B", "Bomb C",
                "Lobby A", "Lobby C",
                "Long A", "Long C",
                "Jardim A", "Rato",
                "Janela Meio", "Meio", "Feno",
                "DD", "Janela C", "Quadrado",
                "Link A", "Link C", "Ceu"
        };

        for (String ponto : pontos) {
            System.out.println("- " + ponto);
        }

        System.out.println("========================================\n");
    }
}