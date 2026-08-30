```java
package game;

import java.util.*;

/**
 * ================================================================
 *                            GAME
 * ================================================================
 *
 * Controlador principal de LAST CHANCE.
 *
 * Junta:
 *
 * Player
 * Girl
 * GameState
 * GameDevice
 * Item
 * Dialogues
 *
 * ================================================================
 */

public class Game {

    private final GameState state;

    private final Player player;

    private final Girl alice;

    private final GameDevice device;

    private final Item.Inventory inventory;

    private final Dialogues dialogues;

    private final Scanner scanner;

    private boolean running;

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public Game(
        GameState state,
        Player player,
        Girl alice,
        GameDevice device,
        Item.Inventory inventory
    ) {

        this.state = state;

        this.player = player;

        this.alice = alice;

        this.device = device;

        this.inventory = inventory;

        this.dialogues =
            new Dialogues();

        this.scanner =
            new Scanner(System.in);

        this.running = false;
    }

    // ============================================================
    // INÍCIO
    // ============================================================

    public void start() {

        running = true;

        intro();

        while (
            running &&
            !state.isGameFinished()
        ) {

            updateClock();

            switch (
                state.getCurrentState()
            ) {

                case INTRO:

                    intro();

                    break;

                case WAITING:

                    waiting();

                    break;

                case PLAYER_TURN:

                    playerTurn();

                    break;

                case ALICE_TURN:

                    aliceTurn();

                    break;

                case DEALER_TURN:

                    dealerTurn();

                    break;

                case TRUCO:

                    truco();

                    break;

                case DIALOGUE:

                    dialoguePhase();

                    break;

                case REVELATION:

                    revelation();

                    break;

                case FINAL:

                    calculateEnding();

                    break;

                case GAME_OVER:

                    calculateEnding();

                    break;
            }
        }
    }

    // ============================================================
    // INTRO
    // ============================================================

    private void intro() {

        state.setCurrentState(
            GameState.State.INTRO
        );

        printDialogue(
            "Dealer",
            "Boa noite, Leo."
        );

        printDialogue(
            "Leo",
            "..."
        );

        printDialogue(
            "Alice",
            "Você conhece ele?"
        );

        printDialogue(
            "Dealer",
            "Conheço melhor do que deveria."
        );

        state.advanceTime(30);

        state.setCurrentState(
            GameState.State.WAITING
        );
    }

    // ============================================================
    // ESPERA
    // ============================================================

    private void waiting() {

        showClock();

        System.out.println();

        printDialogue(
            "Dealer",
            "Vamos começar?"
        );

        System.out.println();

        System.out.println(
            "[1] Jogar"
        );

        System.out.println(
            "[2] Conversar com Alice"
        );

        System.out.println(
            "[3] Examinar a mesa"
        );

        System.out.println(
            "[4] Ver status"
        );

        int choice =
            readChoice(4);

        switch (choice) {

            case 1:

                state.setCurrentState(
                    GameState.State.PLAYER_TURN
                );

                break;

            case 2:

                state.setCurrentState(
                    GameState.State.DIALOGUE
                );

                break;

            case 3:

                examineTable();

                break;

            case 4:

                showStatus();

                break;
        }
    }

    // ============================================================
    // TURNO DO JOGADOR
    // ============================================================

    private void playerTurn() {

        showClock();

        System.out.println();

        System.out.println(
            "Sua mão:"
        );

        printHand(
            player.getHand()
        );

        System.out.println();

        System.out.println(
            "[1] Jogar carta"
        );

        System.out.println(
            "[2] Pedir Truco"
        );

        System.out.println(
            "[3] Conversar"
        );

        int choice =
            readChoice(3);

        switch (choice) {

            case 1:

                playPlayerCard();

                break;

            case 2:

                callTruco();

                break;

            case 3:

                state.setCurrentState(
                    GameState.State.DIALOGUE
                );

                break;
        }
    }

    // ============================================================
    // JOGAR CARTA
    // ============================================================

    private void playPlayerCard() {

        if (
            player.getHandSize() == 0
        ) {

            dealCards();

            return;
        }

        int card =
            readChoice(
                player.getHandSize()
            );

        String selected =
            player.getCard(
                card - 1
            );

        if (selected == null) {

            return;
        }

        player.removeCard(
            selected
        );

        System.out.println();

        printDialogue(
            "Leo",
            "Eu jogo essa."
        );

        state.advanceTime(15);

        state.setCurrentState(
            GameState.State.ALICE_TURN
        );
    }

    // ============================================================
    // TURNO DA ALICE
    // ============================================================

    private void aliceTurn() {

        if (
            alice.getHandSize() == 0
        ) {

            dealCards();

            state.setCurrentState(
                GameState.State.PLAYER_TURN
            );

            return;
        }

        Random random =
            new Random();

        String card =
            alice.getCard(
                random.nextInt(
                    alice.getHandSize()
                )
            );

        alice.removeCard(
            card
        );

        printDialogue(
            "Alice",
            "Minha vez."
        );

        state.advanceTime(15);

        state.setCurrentState(
            GameState.State.DEALER_TURN
        );
    }

    // ============================================================
    // DEALER
    // ============================================================

    private void dealerTurn() {

        printDialogue(
            "Dealer",
            "Hmm."
        );

        /*
         * Aqui futuramente entraremos com o
         * DealerActions.java.
         */

        checkInstantVictory();

        if (
            state.instantVictoryDetected()
        ) {

            printDialogue(
                "Dealer",
                "Ah... isso vai ser interessante."
            );
        }

        state.advanceTime(10);

        state.setCurrentState(
            GameState.State.WAITING
        );
    }

    // ============================================================
    // TRUCO
    // ============================================================

    private void truco() {

        player.registerTruco();

        int current =
            state.getTableValue();

        if (current >= 12) {

            current = 12;

        } else {

            current *= 2;
        }

        state.setTableValue(
            current
        );

        printDialogue(
            "Leo",
            "TRUCO!"
        );

        printDialogue(
            "Dealer",
            "Olha só..."
        );

        state.advanceTime(20);

        state.setCurrentState(
            GameState.State.PLAYER_TURN
        );
    }

    private void callTruco() {

        state.setCurrentState(
            GameState.State.TRUCO
        );
    }

    // ============================================================
    // DETECTOR DE VITÓRIA INSTANTÂNEA
    // ============================================================

    private void checkInstantVictory() {

        boolean playerWinning =
            playerHasInstantVictory();

        boolean aliceWinning =
            aliceHasInstantVictory();

        if (
            playerWinning ||
            aliceWinning
        ) {

            state.setInstantVictoryDetected(
                true
            );

            if (playerWinning) {

                printDialogue(
                    "Dealer",
                    "Leo tem uma mão que pode encerrar isso."
                );
            }

            if (aliceWinning) {

                printDialogue(
                    "Dealer",
                    "Alice tem uma mão que pode virar tudo."
                );
            }
        }
    }

    private boolean playerHasInstantVictory() {

        /*
         * Espaço reservado para a avaliação
         * completa das regras de Truco.
         *
         * A implementação final pode considerar:
         *
         * - combinação das cartas
         * - rodada atual
         * - valor da mão
         * - aposta
         */

        return
            player.getHandSize() >= 3 &&
            state.getPlayerScore() >= 10;
    }

    private boolean aliceHasInstantVictory() {

        return
            alice.getHandSize() >= 3 &&
            state.getAliceScore() >= 10;
    }

    // ============================================================
    // DIÁLOGOS
    // ============================================================

    private void dialoguePhase() {

        Dialogues.Dialogue[] available =
            dialogues.getConditionalDialogues(
                state,
                alice
            );

        if (
            available.length == 0
        ) {

            state.setCurrentState(
                GameState.State.WAITING
            );

            return;
        }

        Random random =
            new Random();

        Dialogues.Dialogue selected =
            available[
                random.nextInt(
                    available.length
                )
            ];

        printDialogue(
            selected.getSpeaker(),
            selected.getText()
        );

        dialogueChoice();

        state.advanceTime(25);

        state.setCurrentState(
            GameState.State.WAITING
        );
    }

    // ============================================================
    // ESCOLHAS DE DIÁLOGO
    // ============================================================

    private void dialogueChoice() {

        System.out.println();

        System.out.println(
            "[1] Ser gentil"
        );

        System.out.println(
            "[2] Ser desconfiado"
        );

        System.out.println(
            "[3] Provocar o Dealer"
        );

        System.out.println(
            "[4] Não responder"
        );

        int choice =
            readChoice(4);

        switch (choice) {

            case 1:

                player.registerCompassionateChoice();

                alice.increaseTrust(7);

                state.increaseAliceTrust(7);

                alice.increaseHope(5);

                break;

            case 2:

                state.increaseSuspicion(8);

                alice.increaseSuspicion(5);

                player.increaseDealerResistance(3);

                break;

            case 3:

                state.decreaseDealerInfluence(5);

                state.increaseDealerRedemption(3);

                player.increaseDealerResistance(5);

                break;

            case 4:

                player.increaseFear(2);

                state.advanceTime(10);

                break;
        }

        updateRevelations();
    }

    // ============================================================
    // REVELAÇÕES
    // ============================================================

    private void updateRevelations() {

        if (
            state.getSuspicionLevel() >= 50 &&
            !state.discoveredDealersIdentity()
        ) {

            state.discoverDealerIdentity();

            alice.revealDealerIdentity();

            printDialogue(
                "Dealer",
                "Ah. Descobriram."
            );
        }

        if (
            state.getAliceTrust() >= 65 &&
            !state.discoveredRelationship()
        ) {

            state.discoverRelationship();

            alice.triggerMemory();
        }

        if (
            state.getSuspicionLevel() >= 75 &&
            !state.discoveredTruth()
        ) {

            state.discoverTruth();

            state.discoverFamilyConflict();

            state.discoverAccident();
        }
    }

    private void revelation() {

        printDialogue(
            "Dealer",
            "Vocês finalmente entenderam."
        );

        state.setCurrentState(
            GameState.State.WAITING
        );
    }

    // ============================================================
    // CARTAS
    // ============================================================

    private void dealCards() {

        player.clearHand();

        alice.clearHand();

        /*
         * Baralho simplificado.
         *
         * O sistema definitivo do Truco poderá
         * ser separado depois.
         */

        String[] deck = {

            "4♣",
            "5♣",
            "6♣",
            "7♣",

            "Q♥",
            "J♥",
            "K♥",

            "A♠",
            "2♠",
            "3♠",

            "Q♦",
            "J♦",
            "K♦",

            "A♣",
            "2♣",
            "3♣"
        };

        List<String> cards =
            new ArrayList<>(
                Arrays.asList(deck)
            );

        Collections.shuffle(
            cards
        );

        for (int i = 0; i < 3; i++) {

            player.addCard(
                cards.remove(0)
            );

            alice.addCard(
                cards.remove(0)
            );
        }
    }

    // ============================================================
    // EXAMINAR
    // ============================================================

    private void examineTable() {

        printDialogue(
            "Leo",
            "Tem alguma coisa errada com essa mesa."
        );

        printDialogue(
            "Dealer",
            "Tem várias."
        );

        state.increaseSuspicion(3);

        state.advanceTime(20);
    }

    // ============================================================
    // STATUS
    // ============================================================

    private void showStatus() {

        System.out.println();

        System.out.println(
            "HORÁRIO: " +
            state.getFormattedTime()
        );

        System.out.println();

        player.printStatus();

        System.out.println();

        alice.printStatus();

        System.out.println();

        System.out.println(
            "Confiança de Alice: " +
            state.getAliceTrust()
        );

        System.out.println(
            "Influência do Dealer: " +
            state.getDealerInfluence()
        );

        System.out.println(
            "Redenção do Dealer: " +
            state.getDealerRedemption()
        );

        System.out.println();
    }

    // ============================================================
    // RELÓGIO
    // ============================================================

    private void updateClock() {

        if (
            state.isClockRunning()
        ) {

            state.advanceTime(5);
        }
    }

    private void showClock() {

        System.out.println();

        System.out.println(
            "+------------------+"
        );

        System.out.println(
            "|   " +
            state.getFormattedTime() +
            "   |"
        );

        System.out.println(
            "+------------------+"
        );
    }

    // ============================================================
    // FINAIS
    // ============================================================

    private void calculateEnding() {

        int ending;

        /*
         * FINAL 6
         *
         * Melhor resultado.
         */

        if (
            state.canReachBestEnding()
        ) {

            ending = 6;
        }

        /*
         * FINAL 5
         */

        else if (
            state.canReachGoodEnding()
        ) {

            ending = 5;
        }

        /*
         * FINAL 4
         *
         * Dealer domina a situação.
         */

        else if (
            state.getDealerInfluence() >= 80
        ) {

            ending = 4;
        }

        /*
         * FINAL 3
         *
         * O ciclo continua.
         */

        else if (
            state.getDealerRedemption() < 30 &&
            state.getAliceTrust() < 40
        ) {

            ending = 3;
        }

        /*
         * FINAL 2
         *
         * Alice é derrotada na partida.
         */

        else if (
            state.getAliceScore() >= 12
        ) {

            ending = 2;
        }

        /*
         * FINAL 1
         *
         * Leo perde.
         */

        else {

            ending = 1;
        }

        state.setEnding(
            ending
        );

        state.setGameFinished(
            true
        );

        showEnding(
            ending
        );
    }

    // ============================================================
    // FINAIS
    // ============================================================

    private void showEnding(
        int ending
    ) {

        System.out.println();

        System.out.println(
            "========================================"
        );

        switch (ending) {

            case 1:

                System.out.println(
                    "             FINAL 1"
                );

                System.out.println(
                    "            A ÚLTIMA MÃO"
                );

                printDialogue(
                    "Dealer",
                    "Você perdeu."
                );

                break;

            case 2:

                System.out.println(
                    "             FINAL 2"
                );

                System.out.println(
                    "          NÃO ERA PARA ELA"
                );

                printDialogue(
                    "Alice",
                    "Eu queria que tivesse sido diferente."
                );

                break;

            case 3:

                System.out.println(
                    "             FINAL 3"
                );

                System.out.println(
                    "           A MESA VAZIA"
                );

                printDialogue(
                    "Dealer",
                    "Amanhã a gente joga de novo."
                );

                break;

            case 4:

                System.out.println(
                    "             FINAL 4"
                );

                System.out.println(
                    "          O DEALER VENCE"
                );

                printDialogue(
                    "Dealer",
                    "Eu disse que vocês não conseguiriam."
                );

                break;

            case 5:

                System.out.println(
                    "             FINAL 5"
                );

                System.out.println(
                    "           ÚLTIMA CHANCE"
                );

                printDialogue(
                    "Alice",
                    "A gente conseguiu."
                );

                break;

            case 6:

                System.out.println(
                    "             FINAL 6"
                );

                System.out.println(
                    "               ADEUS"
                );

                printDialogue(
                    "Dealer",
                    "Obrigado por não me esquecerem."
                );

                break;
        }

        System.out.println(
            "========================================"
        );
    }

    // ============================================================
    // UTILIDADES
    // ============================================================

    private int readChoice(
        int max
    ) {

        while (true) {

            System.out.print(
                "> "
            );

            String input =
                scanner.nextLine();

            try {

                int value =
                    Integer.parseInt(
                        input
                    );

                if (
                    value >= 1 &&
                    value <= max
                ) {

                    return value;
                }

            } catch (
                NumberFormatException ignored
            ) {

            }

            System.out.println(
                "Escolha uma opção válida."
            );
        }
    }

    private void printDialogue(
        String speaker,
        String text
    ) {

        System.out.println();

        System.out.println(
            "[" + speaker + "]"
        );

        System.out.println(
            text
        );
    }

    private void printHand(
        List<String> hand
    ) {

        for (
            int i = 0;
            i < hand.size();
            i++
        ) {

            System.out.println(
                "[" +
                (i + 1) +
                "] " +
                hand.get(i)
            );
        }
    }
}
```
