package game;

import java.util.Scanner;

/**
 * ================================================================
 *                           LAST CHANCE
 * ================================================================
 *
 * Ponto de entrada do jogo.
 *
 * Main.java NÃO contém a lógica principal.
 *
 * Responsabilidades:
 *
 * - iniciar o jogo
 * - mostrar o menu
 * - criar os objetos principais
 * - iniciar a partida
 * - permitir reiniciar
 *
 * ================================================================
 */

public class Main {

    private static Scanner scanner =
        new Scanner(System.in);

    // ============================================================
    // MAIN
    // ============================================================

    public static void main(String[] args) {

        showTitle();

        boolean running = true;

        while (running) {

            int option =
                showMenu();

            switch (option) {

                case 1:

                    startGame();

                    break;

                case 2:

                    showHowToPlay();

                    break;

                case 3:

                    showAbout();

                    break;

                case 4:

                    running = false;

                    System.out.println();
                    System.out.println(
                        "A mesa fica em silêncio."
                    );

                    System.out.println(
                        "Até a próxima."
                    );

                    break;

                default:

                    System.out.println();
                    System.out.println(
                        "Opção inválida."
                    );

                    waitForEnter();

                    break;
            }
        }

        scanner.close();
    }

    // ============================================================
    // TÍTULO
    // ============================================================

    private static void showTitle() {

        clearScreen();

        System.out.println();
        System.out.println(
            "================================================"
        );

        System.out.println(
            "                  LAST CHANCE"
        );

        System.out.println(
            "================================================"
        );

        System.out.println();

        System.out.println(
            "        " +
            "Uma partida sempre termina."
        );

        System.out.println(
            "        " +
            "Nem sempre alguém sai dela."
        );

        System.out.println();

        System.out.println(
            "================================================"
        );

        waitForEnter();
    }

    // ============================================================
    // MENU
    // ============================================================

    private static int showMenu() {

        clearScreen();

        System.out.println(
            "================================================"
        );

        System.out.println(
            "                  LAST CHANCE"
        );

        System.out.println(
            "================================================"
        );

        System.out.println();

        System.out.println(
            "[1] Nova partida"
        );

        System.out.println(
            "[2] Como jogar"
        );

        System.out.println(
            "[3] Sobre"
        );

        System.out.println(
            "[4] Sair"
        );

        System.out.println();

        System.out.print(
            "> "
        );

        String input =
            scanner.nextLine();

        try {

            return Integer.parseInt(input);

        } catch (NumberFormatException e) {

            return -1;
        }
    }

    // ============================================================
    // INICIAR JOGO
    // ============================================================

    private static void startGame() {

        clearScreen();

        System.out.println(
            "Inicializando..."
        );

        System.out.println();

        pause(700);

        /*
         * Estado global da partida.
         */

        GameState state =
            new GameState();

        /*
         * Jogador.
         */

        Player player =
            new Player();

        /*
         * Alice.
         */

        Girl alice =
            new Girl();

        /*
         * Máquina.
         */

        GameDevice device =
            new GameDevice();

        /*
         * Inventário.
         */

        Item.Inventory inventory =
            Item.createStartingInventory();

        /*
         * Game recebe todas as partes.
         *
         * O construtor correspondente será
         * implementado no Game.java.
         */

        Game game =
            new Game(
                state,
                player,
                alice,
                device,
                inventory
            );

        /*
         * Começa a partida.
         */

        game.start();

        /*
         * Quando Game terminar, voltamos para
         * o menu principal.
         */

        waitForEnter();
    }

    // ============================================================
    // COMO JOGAR
    // ============================================================

    private static void showHowToPlay() {

        clearScreen();

        System.out.println(
            "================================================"
        );

        System.out.println(
            "                  COMO JOGAR"
        );

        System.out.println(
            "================================================"
        );

        System.out.println();

        System.out.println(
            "Você está preso em uma partida de Truco."
        );

        System.out.println();

        System.out.println(
            "Alice está do outro lado da mesa."
        );

        System.out.println(
            "O Dealer controla a partida."
        );

        System.out.println();

        System.out.println(
            "Suas escolhas durante as conversas"
        );

        System.out.println(
            "podem alterar a relação com Alice."
        );

        System.out.println();

        System.out.println(
            "Itens podem:"
        );

        System.out.println(
            "- aumentar a confiança de Alice"
        );

        System.out.println(
            "- revelar memórias"
        );

        System.out.println(
            "- alterar a partida"
        );

        System.out.println(
            "- interferir no Dealer"
        );

        System.out.println(
            "- ajudar ou impedir uma possível redenção"
        );

        System.out.println();

        System.out.println(
            "Nem tudo que o Dealer diz é verdade."
        );

        System.out.println();

        System.out.println(
            "E nem tudo que Alice lembra aconteceu"
        );

        System.out.println(
            "da maneira que ela acredita."
        );

        System.out.println();

        System.out.println(
            "Existem seis finais."
        );

        System.out.println(
            "Quatro deles não são bons."
        );

        System.out.println(
            "Dois deles podem ser."
        );

        System.out.println();

        waitForEnter();
    }

    // ============================================================
    // SOBRE
    // ============================================================

    private static void showAbout() {

        clearScreen();

        System.out.println(
            "================================================"
        );

        System.out.println(
            "                     SOBRE"
        );

        System.out.println(
            "================================================"
        );

        System.out.println();

        System.out.println(
            "LAST CHANCE"
        );

        System.out.println();

        System.out.println(
            "Um jogo narrativo de cartas,"
        );

        System.out.println(
            "escolhas e manipulação."
        );

        System.out.println();

        System.out.println(
            "Você joga."
        );

        System.out.println(
            "Alice joga."
        );

        System.out.println(
            "O Dealer observa."
        );

        System.out.println();

        System.out.println(
            "E alguém está mentindo."
        );

        System.out.println();

        waitForEnter();
    }

    // ============================================================
    // LIMPAR TERMINAL
    // ============================================================

    private static void clearScreen() {

        /*
         * Funciona na maioria dos terminais modernos.
         */

        System.out.print(
            "\033[H\033[2J"
        );

        System.out.flush();
    }

    // ============================================================
    // PAUSA
    // ============================================================

    private static void pause(
        long milliseconds
    ) {

        try {

            Thread.sleep(
                milliseconds
            );

        } catch (InterruptedException e) {

            Thread.currentThread()
                .interrupt();
        }
    }

    // ============================================================
    // ENTER
    // ============================================================

    private static void waitForEnter() {

        System.out.println();

        System.out.print(
            "Pressione ENTER para continuar..."
        );

        scanner.nextLine();
    }
}
