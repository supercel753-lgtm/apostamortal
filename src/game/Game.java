```java
package game;

import java.util.*;

public class Game {

    private final Scanner scanner;
    private final Random random;

    private final Player player;
    private final Girl alice;
    private final GameDevice machine;

    private GameState state;

    // =========================================================
    // RELÓGIO
    // =========================================================

    private int hour = 2;
    private int minute = 13;

    private static final int END_HOUR = 4;

    // =========================================================
    // TRUCO
    // =========================================================

    private int playerScore = 0;
    private int aliceScore = 0;

    private int handValue = 1;

    private Card[] playerHand;
    private Card[] aliceHand;

    private int playerWins;
    private int aliceWins;

    // =========================================================
    // HISTÓRIA
    // =========================================================

    private int tension = 0;

    private boolean dealerRevealed;
    private boolean machineUnderstood;
    private boolean aliceTrustsPlayer;

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public Game() {

        scanner = new Scanner(System.in);
        random = new Random();

        player = new Player("Você");
        alice = new Girl("Alice");
        machine = new GameDevice();

        state = GameState.STARTING;

        playerHand = new Card[3];
        aliceHand = new Card[3];

        dealerRevealed = false;
        machineUnderstood = false;
        aliceTrustsPlayer = false;
    }

    // =========================================================
    // INÍCIO
    // =========================================================

    public void start() {

        clear();

        Dialogues.title();

        pause();

        openingScene();

        while (state != GameState.ENDING &&
               state != GameState.GAME_OVER) {

            if (hour >= END_HOUR) {
                state = GameState.ENDING;
                break;
            }

            randomScene();

            if (state == GameState.ENDING)
                break;

            playTruco();

            checkGameState();
        }

        finalScene();
    }

    // =========================================================
    // ABERTURA
    // =========================================================

    private void openingScene() {

        clear();

        clock();

        Dialogues.narration(
            "Você acorda em uma sala que não reconhece."
        );

        Dialogues.narration(
            "Seu corpo parece pesado demais para obedecer."
        );

        Dialogues.player(
            "Que lugar é esse..."
        );

        Dialogues.alice(
            "Finalmente."
        );

        Dialogues.player(
            "Quem é você?"
        );

        Dialogues.alice(
            "Alice."
        );

        Dialogues.narration(
            "Ela olha para o chão."
        );

        Dialogues.alice(
            "Não precisa ficar olhando."
        );

        Dialogues.narration(
            "Você percebe as marcas deixadas pelo acidente que mudou a vida dela."
        );

        Dialogues.alice(
            "Eu sei como pareço."
        );

        pause();

        Dialogues.narration(
            "Sobre a mesa existe um baralho."
        );

        Dialogues.narration(
            "Atrás dele existe uma máquina."
        );

        Dialogues.machine(
            "PARTIDA ENCONTRADA."
        );

        Dialogues.machine(
            "JOGO: TRUCO."
        );

        Dialogues.machine(
            "VENCEDOR: LIBERDADE."
        );

        Dialogues.machine(
            "RECOMPENSA: GARANTIDA."
        );

        pause();

        Dialogues.dealer(
            "Senhoras e senhores..."
        );

        Dialogues.dealer(
            "Que entrada dramática."
        );

        Dialogues.dealer(
            "Eu teria colocado música, mas alguém aqui aparentemente não sabe trabalhar com orçamento."
        );

        Dialogues.alice(
            "...Ele está aqui."
        );

        Dialogues.player(
            "Quem?"
        );

        Dialogues.dealer(
            "Ora."
        );

        Dialogues.dealer(
            "Seu irmãozinho querido."
        );

        Dialogues.dealer(
            "Seu ex-namorado inesquecível."
        );

        Dialogues.dealer(
            "Seu anfitrião extremamente competente."
        );

        pause();

        Dialogues.dealer(
            "Podem me chamar de Dealer."
        );

        Dialogues.alice(
            "Você está morto."
        );

        Dialogues.dealer(
            "Tecnicamente."
        );

        Dialogues.dealer(
            "Mas eu achei o conceito meio inconveniente."
        );

        pause();

        Dialogues.dealer(
            "Agora..."
        );

        Dialogues.dealer(
            "Vamos jogar truco."
        );

        Dialogues.dealer(
            "E tentem não morrer antes da hora."
        );

        pause();
    }

    // =========================================================
    // CENAS ALEATÓRIAS
    // =========================================================

    private void randomScene() {

        clear();

        clock();

        int scene = random.nextInt(8);

        switch (scene) {

            case 0:
                sceneDealerProvocation();
                break;

            case 1:
                sceneAliceMemory();
                break;

            case 2:
                sceneMachine();
                break;

            case 3:
                sceneDoor();
                break;

            case 4:
                sceneDealerHint();
                break;

            case 5:
                sceneOldMemory();
                break;

            case 6:
                sceneReward();
                break;

            default:
                sceneSilence();
                break;
        }
    }

    // =========================================================
    // DEALER PROVOCANDO
    // =========================================================

    private void sceneDealerProvocation() {

        Dialogues.dealer(
            "Ah, você voltou."
        );

        Dialogues.dealer(
            "Comecei a achar que tinha desistido."
        );

        Dialogues.player(
            "Você não cansa?"
        );

        Dialogues.dealer(
            "Não."
        );

        Dialogues.dealer(
            "Morrer tem várias desvantagens."
        );

        Dialogues.dealer(
            "Mas não precisar dormir é uma delas."
        );

        Dialogues.alice(
            "Você é insuportável."
        );

        Dialogues.dealer(
            "E você namorou comigo."
        );

        pause();

        Dialogues.alice(
            "Eu me arrependo todos os dias."
        );

        Dialogues.dealer(
            "Mentira."
        );

        Dialogues.dealer(
            "Você sente saudade."
        );

        tension += 5;

        advanceTime(3);
    }

    // =========================================================
    // MEMÓRIA DE ALICE
    // =========================================================

    private void sceneAliceMemory() {

        Dialogues.alice(
            "Você lembra dele?"
        );

        Dialogues.player(
            "Não sei."
        );

        Dialogues.alice(
            "Ele fazia isso."
        );

        Dialogues.player(
            "Isso o quê?"
        );

        Dialogues.alice(
            "Falava demais."
        );

        Dialogues.dealer(
            "Eu prefiro chamar de personalidade."
        );

        Dialogues.alice(
            "Eu preferia quando você estava morto."
        );

        Dialogues.dealer(
            "Nossa."
        );

        Dialogues.dealer(
            "Que romântico."
        );

        aliceTrustsPlayer = true;

        advanceTime(5);
    }

    // =========================================================
    // MÁQUINA
    // =========================================================

    private void sceneMachine() {

        Dialogues.narration(
            "A máquina começa a emitir um som."
        );

        Dialogues.machine(
            "ANÁLISE CARDÍACA."
        );

        Dialogues.machine(
            "ESTADO: INSTÁVEL."
        );

        Dialogues.player(
            "Ela está monitorando meu coração?"
        );

        Dialogues.dealer(
            "Está."
        );

        Dialogues.dealer(
            "Não gosto de interromper, mas tecnicamente seu corpo está fazendo um trabalho péssimo."
        );

        Dialogues.alice(
            "Cala a boca."
        );

        Dialogues.dealer(
            "Eu estou tentando manter o clima leve."
        );

        tension += 10;

        machineUnderstood = true;

        advanceTime(4);
    }

    // =========================================================
    // PORTA
    // =========================================================

    private void sceneDoor() {

        Dialogues.narration(
            "Você encara a porta."
        );

        Dialogues.player(
            "E se simplesmente abrirmos?"
        );

        Dialogues.dealer(
            "Ah."
        );

        Dialogues.dealer(
            "Excelente ideia."
        );

        Dialogues.dealer(
            "Por favor."
        );

        Dialogues.narration(
            "Alice tenta abrir a porta."
        );

        pause();

        Dialogues.narration(
            "Ela não se move."
        );

        Dialogues.dealer(
            "Surpresa!"
        );

        Dialogues.alice(
            "Eu odeio você."
        );

        Dialogues.dealer(
            "Recíproco."
        );

        advanceTime(4);
    }

    // =========================================================
    // DICA DO DEALER
    // =========================================================

    private void sceneDealerHint() {

        Dialogues.dealer(
            "Quer uma dica?"
        );

        Dialogues.player(
            "Não."
        );

        Dialogues.dealer(
            "Ótimo."
        );

        pause();

        Dialogues.dealer(
            "Aqui vai."
        );

        Dialogues.dealer(
            "Nunca confie em alguém que conhece suas cartas."
        );

        Dialogues.alice(
            "Você está falando de você."
        );

        Dialogues.dealer(
            "Eu sei."
        );

        Dialogues.dealer(
            "É por isso que a dica é boa."
        );

        advanceTime(3);
    }

    // =========================================================
    // MEMÓRIA ANTIGA
    // =========================================================

    private void sceneOldMemory() {

        Dialogues.narration(
            "Você olha para Alice."
        );

        Dialogues.player(
            "Nós já nos conhecíamos?"
        );

        AliceMemory();

        advanceTime(6);
    }

    private void AliceMemory() {

        Dialogues.alice(
            "Sim."
        );

        pause();

        Dialogues.alice(
            "Muito antes disso."
        );

        Dialogues.player(
            "E meu irmão?"
        );

        pause();

        Dialogues.alice(
            "Ele era..."
        );

        Dialogues.dealer(
            "Eu era maravilhoso."
        );

        Dialogues.alice(
            "Você era um idiota."
        );

        Dialogues.dealer(
            "Mas um idiota maravilhoso."
        );
    }

    // =========================================================
    // RECOMPENSA
    // =========================================================

    private void sceneReward() {

        Dialogues.machine(
            "RECOMPENSA DISPONÍVEL AO VENCEDOR."
        );

        Dialogues.machine(
            "DESEJO PRINCIPAL IDENTIFICADO."
        );

        Dialogues.player(
            "Meu desejo?"
        );

        Dialogues.dealer(
            "Você quer saber?"
        );

        Dialogues.player(
            "Sim."
        );

        Dialogues.dealer(
            "Não."
        );

        Dialogues.player(
            "..."
        );

        Dialogues.dealer(
            "Pergunta idiota."
        );

        tension += 5;

        advanceTime(3);
    }

    // =========================================================
    // SILÊNCIO
    // =========================================================

    private void sceneSilence() {

        Dialogues.narration(
            "Por alguns segundos, ninguém fala."
        );

        pause();

        Dialogues.dealer(
            "Isso está ficando desconfortável."
        );

        pause();

        Dialogues.dealer(
            "Vou falar alguma coisa."
        );

        Dialogues.alice(
            "Não."
        );

        Dialogues.dealer(
            "Tudo bem."
        );

        pause();

        Dialogues.dealer(
            "Eu odeio vocês."
        );

        Dialogues.alice(
            "Obrigado."
        );

        advanceTime(2);
    }

    // =========================================================
    // TRUCO
    // =========================================================

    private void playTruco() {

        clear();

        clock();

        resetHand();

        dealCards();

        Dialogues.dealer(
            "E lá vamos nós."
        );

        showHands();

        // -----------------------------------------------------
        // O DEALER ANALISA ANTES DE QUALQUER CARTA SER JOGADA
        // -----------------------------------------------------

        analyzeInstantWin();

        boolean playing = true;

        while (playing) {

            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("VALOR DA MÃO: " + handValue);
            System.out.println("----------------------------------------");

            showHands();

            System.out.println();
            System.out.println("1 - Jogar carta");
            System.out.println("2 - Pedir TRUCO");
            System.out.println("3 - Correr");

            int option = readNumber();

            switch (option) {

                case 1:

                    playCardRound();

                    playing = false;

                    break;

                case 2:

                    raiseTruco();

                    break;

                case 3:

                    Dialogues.dealer(
                        "Correndo?"
                    );

                    Dialogues.dealer(
                        "Covardia também é estratégia."
                    );

                    aliceScore += handValue;

                    advanceTime(5);

                    playing = false;

                    break;

                default:

                    System.out.println(
                        "Escolha uma opção válida."
                    );
            }
        }

        handValue = 1;
    }

    // =========================================================
    // DISTRIBUIÇÃO
    // =========================================================

    private void dealCards() {

        for (int i = 0; i < 3; i++) {

            playerHand[i] =
                randomCard();

            aliceHand[i] =
                randomCard();
        }
    }

    // =========================================================
    // CARTAS
    // =========================================================

    private Card randomCard() {

        Rank rank =
            Rank.values()[
                random.nextInt(Rank.values().length)
            ];

        Suit suit =
            Suit.values()[
                random.nextInt(Suit.values().length)
            ];

        return new Card(rank, suit);
    }

    // =========================================================
    // ANALISADOR DO DEALER
    // =========================================================

    private void analyzeInstantWin() {

        HandStrength playerStrength =
            evaluateHand(playerHand);

        HandStrength aliceStrength =
            evaluateHand(aliceHand);

        if (playerStrength.isInstantWin()) {

            Dialogues.dealer(
                "Opa."
            );

            Dialogues.dealer(
                "Olha só o que você recebeu."
            );

            Dialogues.dealer(
                "Isso aí é praticamente uma vitória instantânea."
            );

            Dialogues.dealer(
                "Parabéns."
            );

            Dialogues.dealer(
                "Eu quase sinto pena da Alice."
            );

            tension += 8;

            return;
        }

        if (aliceStrength.isInstantWin()) {

            Dialogues.dealer(
                "HAHA!"
            );

            Dialogues.dealer(
                "Ah, isso vai ser maravilhoso."
            );

            Dialogues.dealer(
                "Alice..."
            );

            Dialogues.dealer(
                "Você percebeu o que recebeu?"
            );

            Dialogues.alice(
                "Não."
            );

            Dialogues.dealer(
                "Você praticamente ganhou essa mão."
            );

            Dialogues.alice(
                "E você está feliz com isso?"
            );

            Dialogues.dealer(
                "Extremamente."
            );

            tension += 8;

            return;
        }

        // -----------------------------------------------------
        // MÃO MUITO FORTE
        // -----------------------------------------------------

        if (playerStrength.getScore() >= 800) {

            Dialogues.dealer(
                "Hmm."
            );

            Dialogues.dealer(
                "Mão forte."
            );

            Dialogues.dealer(
                "Não é vitória instantânea, mas eu apostaria nisso."
            );
        }

        if (aliceStrength.getScore() >= 800) {

            Dialogues.dealer(
                "Alice..."
            );

            Dialogues.dealer(
                "Você está com sorte hoje."
            );

            Dialogues.alice(
                "Eu não confio em você."
            );

            Dialogues.dealer(
                "Inteligente."
            );
        }
    }

    // =========================================================
    // AVALIADOR DE MÃO
    // =========================================================

    private HandStrength evaluateHand(Card[] hand) {

        int best = 0;

        boolean hasAce = false;
        boolean sameSuit = true;
        boolean hasPair = false;

        for (Card card : hand) {

            if (card.rank == Rank.AS) {
                hasAce = true;
            }
        }

        for (int i = 1; i < hand.length; i++) {

            if (hand[i].suit != hand[0].suit) {

                sameSuit = false;
            }
        }

        for (int i = 0; i < hand.length; i++) {

            for (int j = i + 1; j < hand.length; j++) {

                if (hand[i].rank == hand[j].rank) {

                    hasPair = true;
                }
            }
        }

        // -----------------------------------------------------
        // TRINCA
        // -----------------------------------------------------

        if (
            hand[0].rank == hand[1].rank &&
            hand[1].rank == hand[2].rank
        ) {

            best = 1000;

            return new HandStrength(
                "TRINCA",
                best,
                true
            );
        }

        // -----------------------------------------------------
        // TRÊS MANILHAS
        // -----------------------------------------------------

        int manilhas = 0;

        for (Card card : hand) {

            if (card.isManilha()) {

                manilhas++;
            }
        }

        if (manilhas == 3) {

            best = 950;

            return new HandStrength(
                "TRÊS MANILHAS",
                best,
                true
            );
        }

        // -----------------------------------------------------
        // FLUSH
        // -----------------------------------------------------

        if (sameSuit) {

            best = 800;

            return new HandStrength(
                "SEQUÊNCIA DO MESMO NAIPE",
                best,
                false
            );
        }

        // -----------------------------------------------------
        // PAR
        // -----------------------------------------------------

        if (hasPair) {

            best = 500;
        }

        // -----------------------------------------------------
        // ÁS
        // -----------------------------------------------------

        if (hasAce) {

            best += 100;
        }

        // -----------------------------------------------------
        // SOMA DAS CARTAS
        // -----------------------------------------------------

        for (Card card : hand) {

            best += card.strength();
        }

        return new HandStrength(
            "MÃO NORMAL",
            best,
            false
        );
    }

    // =========================================================
    // JOGAR CARTA
    // =========================================================

    private void playCardRound() {

        System.out.println();

        System.out.println(
            "Escolha sua carta:"
        );

        for (int i = 0; i < 3; i++) {

            System.out.println(
                (i + 1)
                + " - "
                + playerHand[i]
            );
        }

        int selected = readNumber() - 1;

        if (selected < 0 || selected >= 3) {

            Dialogues.dealer(
                "Essa carta nem existe."
            );

            return;
        }

        Card playerCard =
            playerHand[selected];

        int aliceIndex =
            random.nextInt(3);

        Card aliceCard =
            aliceHand[aliceIndex];

        Dialogues.narration(
            "Você joga " + playerCard + "."
        );

        Dialogues.alice(
            "Minha vez."
        );

        Dialogues.narration(
            "Alice joga " + aliceCard + "."
        );

        pause();

        if (
            playerCard.strength()
            >
            aliceCard.strength()
        ) {

            Dialogues.dealer(
                "Ponto para você."
            );

            playerWins++;

        } else if (
            aliceCard.strength()
            >
            playerCard.strength()
        ) {

            Dialogues.dealer(
                "Alice levou essa."
            );

            aliceWins++;

        } else {

            Dialogues.dealer(
                "Empate."
            );
        }

        if (playerWins > aliceWins) {

            playerScore += handValue;

        } else if (aliceWins > playerWins) {

            aliceScore += handValue;
        }

        advanceTime(8);
    }

    // =========================================================
    // AUMENTAR TRUCO
    // =========================================================

    private void raiseTruco() {

        if (handValue == 1) {

            handValue = 3;

        } else if (handValue == 3) {

            handValue = 6;

        } else if (handValue == 6) {

            handValue = 9;

        } else {

            handValue = 12;
        }

        machine.increaseInstability(10);

        tension += 10;

        Dialogues.dealer(
            "TRUCO!"
        );

        Dialogues.dealer(
            "Agora ficou interessante."
        );

        if (handValue >= 6) {

            Dialogues.dealer(
                "Vocês realmente querem continuar?"
            );
        }

        if (handValue >= 9) {

            Dialogues.alice(
                "Não aumenta mais."
            );

            Dialogues.dealer(
                "Alice pediu educadamente."
            );

            Dialogues.dealer(
                "Que pena."
            );
        }

        advanceTime(2);
    }

    // =========================================================
    // MÃOS
    // =========================================================

    private void resetHand() {

        playerWins = 0;
        aliceWins = 0;
        handValue = 1;
    }

    private void showHands() {

        System.out.println();

        System.out.println(
            "SUAS CARTAS:"
        );

        for (int i = 0; i < 3; i++) {

            System.out.println(
                (i + 1)
                + " - "
                + playerHand[i]
            );
        }

        System.out.println();

        System.out.println(
            "ALICE: [???] [???] [???]"
        );
    }

    // =========================================================
    // RELÓGIO
    // =========================================================

    private void advanceTime(int minutes) {

        minute += minutes;

        while (minute >= 60) {

            minute -= 60;
            hour++;
        }

        if (hour >= END_HOUR) {

            hour = END_HOUR;
            minute = 0;

            state = GameState.ENDING;
        }
    }

    private void clock() {

        System.out.println();
        System.out.println(
            "       ┌─────────────┐"
        );

        System.out.println(
            "       │    "
            + String.format(
                "%02d:%02d",
                hour,
                minute
            )
            + "     │"
        );

        System.out.println(
            "       └─────────────┘"
        );

        System.out.println();
    }

    // =========================================================
    // ESTADO
    // =========================================================

    private void checkGameState() {

        if (hour >= END_HOUR) {

            state = GameState.ENDING;

            return;
        }

        if (playerScore >= 12 ||
            aliceScore >= 12) {

            state = GameState.ENDING;

            return;
        }

        if (machine.getInstability() >= 100) {

            state = GameState.ENDING;
        }
    }

    // =========================================================
    // FINAL
    // =========================================================

    private void finalScene() {

        clear();

        clock();

        Dialogues.dealer(
            "E acabou."
        );

        Dialogues.dealer(
            "Que noite agradável."
        );

        pause();

        if (playerScore > aliceScore) {

            Dialogues.dealer(
                "Vencedor: você."
            );

            Dialogues.alice(
                "Então você conseguiu."
            );

            Dialogues.dealer(
                "Parabéns!"
            );

            Dialogues.dealer(
                "Você ganhou exatamente aquilo que queria."
            );

        } else {

            Dialogues.dealer(
                "Vencedora: Alice."
            );

            Dialogues.alice(
                "Eu ganhei?"
            );

            Dialogues.dealer(
                "Parece que sim."
            );

            Dialogues.dealer(
                "Embora eu não tenha prometido que isso faria diferença."
            );
        }

        pause();

        Dialogues.dealer(
            "Obrigado por jogar."
        );

        Dialogues.dealer(
            "Nos vemos na próxima vida."
        );

        pause();

        scanner.close();
    }

    // =========================================================
    // UTILIDADES
    // =========================================================

    private int readNumber() {

        while (true) {

            try {

                return Integer.parseInt(
                    scanner.nextLine()
                );

            } catch (Exception e) {

                System.out.println(
                    "Digite um número válido."
                );
            }
        }
    }

    private void pause() {

        System.out.println();
        System.out.println(
            "[ ENTER ]"
        );

        scanner.nextLine();
    }

    private void clear() {

        System.out.print(
            "\033[H\033[2J"
        );

        System.out.flush();
    }

    // =========================================================
    // CARTA
    // =========================================================

    public enum Suit {

        PAUS,
        COPAS,
        ESPADAS,
        OUROS
    }

    public enum Rank {

        QUATRO(4),
        CINCO(5),
        SEIS(6),
        SETE(7),
        DAMA(8),
        VALETE(9),
        REI(10),
        AS(11),
        DOIS(12),
        TRES(13);

        private final int value;

        Rank(int value) {

            this.value = value;
        }
    }

    public class Card {

        private final Rank rank;
        private final Suit suit;

        public Card(
            Rank rank,
            Suit suit
        ) {

            this.rank = rank;
            this.suit = suit;
        }

        public int strength() {

            if (isManilha()) {

                return 100 + rank.value;
            }

            return rank.value;
        }

        public boolean isManilha() {

            // Manilha fixa simplificada
            return rank == Rank.TRES;
        }

        @Override
        public String toString() {

            return rank.name()
                + " DE "
                + suit.name();
        }
    }

    // =========================================================
    // FORÇA DA MÃO
    // =========================================================

    public class HandStrength {

        private final String name;
        private final int score;
        private final boolean instantWin;

        public HandStrength(
            String name,
            int score,
            boolean instantWin
        ) {

            this.name = name;
            this.score = score;
            this.instantWin = instantWin;
        }

        public boolean isInstantWin() {

            return instantWin;
        }

        public int getScore() {

            return score;
        }

        public String getName() {

            return name;
        }
    }
}
```
