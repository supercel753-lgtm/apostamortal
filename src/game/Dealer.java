package game;

import java.util.Random;

/**
 * ================================================================
 *                         DEALER
 * ================================================================
 *
 * Responsável por todas as ações do Dealer.
 *
 * O Dealer pode:
 *
 * - Analisar mãos
 * - Dar dicas verdadeiras
 * - Dar dicas falsas
 * - Esconder informações
 * - Tentar convencer o jogador
 * - Manipular Alice
 * - Trocar cartas
 * - Aumentar ou diminuir a tensão
 * - Fazer comentários durante a partida
 * - Detectar mãos extremamente fortes
 * - Detectar trinca
 * - Detectar três manilhas
 * - Detectar situações perigosas
 * - Criar eventos aleatórios
 * - Interferir na partida
 * - Fingir que não interferiu
 * - Revelar informações sobre o passado
 *
 * O Dealer NÃO controla diretamente o resultado.
 * Ele manipula as condições e tenta influenciar os jogadores.
 *
 * ================================================================
 */

public class Dealer {

    private final Random random;

    // ============================================================
    // ESTADO DO DEALER
    // ============================================================

    private int influence;

    private int annoyance;

    private int lies;

    private int truths;

    private int interventions;

    private int successfulBluffs;

    private int failedBluffs;

    private int revelations;

    private int manipulationLevel;

    // ============================================================
    // ESTADOS
    // ============================================================

    private boolean introduced;

    private boolean identityRevealed;

    private boolean knowsPlayer;

    private boolean knowsAlice;

    private boolean isSerious;

    private boolean isLying;

    private boolean lastActionWasLie;

    private boolean lastActionWasInterference;

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public Dealer() {

        random = new Random();

        influence = 0;

        annoyance = 0;

        lies = 0;

        truths = 0;

        interventions = 0;

        successfulBluffs = 0;

        failedBluffs = 0;

        revelations = 0;

        manipulationLevel = 0;

        introduced = false;

        identityRevealed = false;

        knowsPlayer = true;

        knowsAlice = true;

        isSerious = false;

        isLying = false;

        lastActionWasLie = false;

        lastActionWasInterference = false;
    }

    // ============================================================
    // APRESENTAÇÃO
    // ============================================================

    public String introduce() {

        introduced = true;

        return
            "Ah, finalmente.\n" +
            "Vocês dois chegaram.\n\n" +
            "Eu sou o Dealer.\n" +
            "Vou cuidar da partida.\n\n" +
            "E prometo ser completamente imparcial.\n\n" +
            "...não prometo.";
    }

    // ============================================================
    // PERSONALIDADE
    // ============================================================

    public String annoyingComment() {

        annoyance++;

        int option =
            random.nextInt(8);

        switch (option) {

            case 0:

                return
                    "Vocês poderiam jogar mais rápido?\n" +
                    "Estou morto há algum tempo e ainda tenho pressa.";

            case 1:

                return
                    "Silêncio constrangedor.\n" +
                    "Adoro.";

            case 2:

                return
                    "Não estou torcendo para ninguém.\n" +
                    "Estou torcendo pelo caos.";

            case 3:

                return
                    "Vocês dois são péssimos nisso.\n" +
                    "Eu esperava mais.";

            case 4:

                return
                    "Querem ouvir uma piada?\n" +
                    "Não importa. Vou contar mesmo assim.";

            case 5:

                return
                    "Interessante.\n" +
                    "Você está começando a confiar em mim.\n\n" +
                    "Isso é um erro.";

            case 6:

                return
                    "Eu poderia ajudar.\n" +
                    "Mas seria menos divertido.";

            default:

                return
                    "Continuem.\n" +
                    "Estou me divertindo muito.";
        }
    }

    // ============================================================
    // ANALISAR MÃO
    // ============================================================

    public HandResult analyzeHand(
        Game.Card[] hand
    ) {

        if (
            hand == null ||
            hand.length == 0
        ) {

            return new HandResult(
                0,
                false,
                false,
                false,
                "SEM CARTAS"
            );
        }

        int score = 0;

        int threes = 0;

        int manilhas = 0;

        int highCards = 0;

        for (
            Game.Card card : hand
        ) {

            score += card.strength();

            if (
                card.rank ==
                Game.Rank.TRES
            ) {

                threes++;
            }

            if (
                card.isManilha()
            ) {

                manilhas++;
            }

            if (
                card.strength() >= 11
            ) {

                highCards++;
            }
        }

        boolean trinca =
            threes == hand.length;

        boolean threeManilhas =
            manilhas == hand.length;

        boolean veryStrong =
            trinca ||
            threeManilhas ||
            manilhas >= 2 ||
            highCards >= 2;

        boolean instantWin =
            trinca ||
            threeManilhas;

        String description;

        if (trinca) {

            description =
                "TRINCA";

        } else if (threeManilhas) {

            description =
                "TRÊS MANILHAS";

        } else if (manilhas >= 2) {

            description =
                "DUAS OU MAIS MANILHAS";

        } else if (highCards >= 2) {

            description =
                "MÃO MUITO ALTA";

        } else if (score >= 30) {

            description =
                "MÃO FORTE";

        } else {

            description =
                "MÃO NORMAL";
        }

        return new HandResult(
            score,
            instantWin,
            veryStrong,
            trinca,
            description
        );
    }

    // ============================================================
    // COMPARAR AS DUAS MÃOS
    // ============================================================

    public String compareHands(
        Game.Card[] playerHand,
        Game.Card[] aliceHand
    ) {

        HandResult player =
            analyzeHand(playerHand);

        HandResult alice =
            analyzeHand(aliceHand);

        if (
            player.instantWin &&
            !alice.instantWin
        ) {

            return
                "O Dealer olha para suas cartas.\n\n" +
                "\"Ah.\"\n\n" +
                "\"Isso aqui é praticamente um presente.\"";
        }

        if (
            alice.instantWin &&
            !player.instantWin
        ) {

            return
                "O Dealer olha para as cartas de Alice.\n\n" +
                "\"...Alice.\"\n\n" +
                "\"Você tem noção do que recebeu?\"";
        }

        if (
            player.instantWin &&
            alice.instantWin
        ) {

            return
                "O Dealer fica em silêncio.\n\n" +
                "\"Não.\"\n\n" +
                "\"Isso não deveria ter acontecido.\"";
        }

        if (
            player.veryStrong &&
            alice.veryStrong
        ) {

            return
                "O Dealer começa a rir.\n\n" +
                "\"Os dois estão com mãos excelentes.\"\n\n" +
                "\"Isso vai ser divertido.\"";
        }

        if (player.veryStrong) {

            return
                "O Dealer observa suas cartas.\n\n" +
                "\"Eu não pediria truco se fosse você.\"\n\n" +
                "...provavelmente.";
        }

        if (alice.veryStrong) {

            return
                "O Dealer olha para Alice.\n\n" +
                "\"Não vou dizer nada.\"\n\n" +
                "Ele sorri.";
        }

        return
            "O Dealer observa as cartas.\n\n" +
            "\"Nada de extraordinário.\"";
    }

    // ============================================================
    // DICA VERDADEIRA
    // ============================================================

    public String trueHint(
        Game.Card[] playerHand
    ) {

        truths++;

        influence++;

        HandResult result =
            analyzeHand(playerHand);

        if (result.instantWin) {

            return
                "\"Sua mão é excelente.\"\n\n" +
                "\"Não desperdice isso.\"";
        }

        if (result.veryStrong) {

            return
                "\"Você está em uma posição boa.\"\n\n" +
                "\"Só não seja ganancioso.\"";
        }

        return
            "\"Sua mão não é das melhores.\"\n\n" +
            "\"Pense antes de apostar mais.\"";
    }

    // ============================================================
    // DICA FALSA
    // ============================================================

    public String falseHint(
        Game.Card[] playerHand
    ) {

        lies++;

        influence++;

        isLying = true;

        lastActionWasLie = true;

        HandResult result =
            analyzeHand(playerHand);

        if (result.veryStrong) {

            successfulBluffs++;

            return
                "\"Sinceramente?\"\n\n" +
                "\"Sua mão está horrível.\"\n\n" +
                "\"Eu correria.\"";
        }

        failedBluffs++;

        return
            "\"Você está com uma mão monstruosa.\"\n\n" +
            "\"Pede truco.\"";
    }

    // ============================================================
    // ESCONDER INFORMAÇÃO
    // ============================================================

    public String hideInformation() {

        influence++;

        return
            "O Dealer observa as cartas.\n\n" +
            "Ele sabe alguma coisa.\n\n" +
            "Mas decide não falar.";
    }

    // ============================================================
    // MANIPULAR ALICE
    // ============================================================

    public String manipulateAlice(
        Game.Card[] aliceHand
    ) {

        manipulationLevel++;

        influence++;

        HandResult result =
            analyzeHand(aliceHand);

        if (result.instantWin) {

            return
                "\"Alice.\"\n\n" +
                "\"Confia em mim.\"\n\n" +
                "\"Você não precisa ter medo dessa mão.\"";
        }

        if (result.veryStrong) {

            return
                "\"Alice, talvez você devesse aumentar a aposta.\"\n\n" +
                "\"Só uma sugestão.\"";
        }

        return
            "\"Alice, cuidado.\"\n\n" +
            "\"Ele pode estar blefando.\"";
    }

    // ============================================================
    // MANIPULAR JOGADOR
    // ============================================================

    public String manipulatePlayer(
        Game.Card[] playerHand
    ) {

        manipulationLevel++;

        influence++;

        HandResult result =
            analyzeHand(playerHand);

        if (result.instantWin) {

            return
                "\"Vai em frente.\"\n\n" +
                "\"Você sabe que está seguro.\"";
        }

        if (result.veryStrong) {

            return
                "\"Agora seria uma boa hora para aumentar.\"";
        }

        return
            "\"Talvez seja melhor recuar.\"";
    }

    // ============================================================
    // INTERFERÊNCIA
    // ============================================================

    public String interfere() {

        interventions++;

        influence++;

        lastActionWasInterference = true;

        return
            "O Dealer estala os dedos.\n\n" +
            "\"Pequena alteração nas regras.\"";
    }

    // ============================================================
    // TROCAR CARTA
    // ============================================================

    public Game.Card replaceCard(
        Game.Card[] hand,
        int index
    ) {

        if (
            hand == null ||
            index < 0 ||
            index >= hand.length
        ) {

            return null;
        }

        Game.Card oldCard =
            hand[index];

        Game.Card newCard =
            createRandomCard();

        hand[index] =
            newCard;

        interventions++;

        lastActionWasInterference =
            true;

        return oldCard;
    }

    // ============================================================
    // CRIAR CARTA ALEATÓRIA
    // ============================================================

    private Game.Card createRandomCard() {

        Game.Rank rank =
            Game.Rank.values()[
                random.nextInt(
                    Game.Rank.values().length
                )
            ];

        Game.Suit suit =
            Game.Suit.values()[
                random.nextInt(
                    Game.Suit.values().length
                )
            ];

        return new Game().new Card(
            rank,
            suit
        );
    }

    // ============================================================
    // EVENTO ALEATÓRIO
    // ============================================================

    public DealerAction randomAction() {

        int action =
            random.nextInt(10);

        switch (action) {

            case 0:

                return new DealerAction(
                    "COMENTÁRIO",
                    annoyingComment(),
                    false
                );

            case 1:

                influence++;

                return new DealerAction(
                    "DICA",
                    "Talvez você devesse pensar melhor.",
                    false
                );

            case 2:

                lies++;

                return new DealerAction(
                    "BLEFE",
                    "Eu sei exatamente o que vai acontecer.",
                    false
                );

            case 3:

                interventions++;

                return new DealerAction(
                    "INTERFERÊNCIA",
                    "Uma carta parece ter mudado de lugar.",
                    true
                );

            case 4:

                revelations++;

                return new DealerAction(
                    "MEMÓRIA",
                    "Eu lembro de quando vocês dois ainda eram felizes.",
                    false
                );

            case 5:

                manipulationLevel++;

                return new DealerAction(
                    "MANIPULAÇÃO",
                    "Vocês deveriam desconfiar um do outro.",
                    false
                );

            case 6:

                return new DealerAction(
                    "PROVOCAÇÃO",
                    "Vamos. Me mostrem que vocês têm coragem.",
                    false
                );

            case 7:

                return new DealerAction(
                    "SILÊNCIO",
                    "...",
                    false
                );

            case 8:

                isSerious = true;

                return new DealerAction(
                    "MOMENTO SÉRIO",
                    "Por alguns segundos, o Dealer para de sorrir.",
                    false
                );

            default:

                return new DealerAction(
                    "PIADA",
                    "Eu contaria uma piada sobre a morte, mas já vivi o suficiente dela.",
                    false
                );
        }
    }

    // ============================================================
    // REAÇÃO À TRINCA
    // ============================================================

    public String reactToTrinca(
        boolean isPlayer
    ) {

        influence += 2;

        if (isPlayer) {

            return
                "\"Você está brincando.\"\n\n" +
                "O Dealer olha novamente.\n\n" +
                "\"Isso é uma trinca.\"";
        }

        return
            "O Dealer olha para Alice.\n\n" +
            "\"Ah.\"\n\n" +
            "\"Alice recebeu uma trinca.\"";
    }

    // ============================================================
    // REAÇÃO A TRÊS MANILHAS
    // ============================================================

    public String reactToThreeManilhas(
        boolean isPlayer
    ) {

        influence += 5;

        if (isPlayer) {

            return
                "\"Não.\"\n\n" +
                "\"Você não pode ter três manilhas.\"";
        }

        return
            "\"Alice...\"\n\n" +
            "\"Olha suas cartas.\"";
    }

    // ============================================================
    // REAÇÃO A MÃO RUIM
    // ============================================================

    public String reactToBadHand(
        boolean isPlayer
    ) {

        if (isPlayer) {

            return
                "\"Nossa.\"\n\n" +
                "\"Que mão horrível.\"";
        }

        return
            "\"Alice, eu não faria grandes apostas agora.\"";
    }

    // ============================================================
    // REAÇÃO A TRUCO
    // ============================================================

    public String reactToTruco(
        int value,
        boolean calledByPlayer
    ) {

        influence++;

        if (value >= 12) {

            return
                "\"DOZE?\"\n\n" +
                "\"Vocês realmente perderam completamente o juízo.\"";
        }

        if (value >= 9) {

            return
                "\"Nove.\"\n\n" +
                "\"Agora alguém vai começar a se arrepender.\"";
        }

        if (value >= 6) {

            return
                "\"Seis.\"\n\n" +
                "\"Estamos subindo.\"";
        }

        return
            "\"TRUCO!\"\n\n" +
            "\"Finalmente.\"";
    }

    // ============================================================
    // REVELAÇÃO
    // ============================================================

    public String revealIdentity() {

        identityRevealed = true;

        revelations++;

        return
            "O sorriso do Dealer desaparece.\n\n" +
            "\"Vocês querem saber quem eu sou?\"\n\n" +
            "\"Eu sou o irmão dele.\"\n\n" +
            "\"E fui o namorado dela.\"\n\n" +
            "\"E, aparentemente, morrer não foi suficiente para me fazer ir embora.\"";
    }

    // ============================================================
    // VOLTAR AO PERSONAGEM IRRITANTE
    // ============================================================

    public String returnToAnnoyingMode() {

        isSerious = false;

        return
            "\"Pronto.\"\n\n" +
            "\"Momento emocional encerrado.\"\n\n" +
            "\"Podemos voltar a jogar?\"";
    }

    // ============================================================
    // RESET DA AÇÃO
    // ============================================================

    public void resetActionState() {

        isLying = false;

        lastActionWasLie = false;

        lastActionWasInterference = false;
    }

    // ============================================================
    // GETTERS
    // ============================================================

    public int getInfluence() {

        return influence;
    }

    public int getAnnoyance() {

        return annoyance;
    }

    public int getLies() {

        return lies;
    }

    public int getTruths() {

        return truths;
    }

    public int getInterventions() {

        return interventions;
    }

    public int getSuccessfulBluffs() {

        return successfulBluffs;
    }

    public int getFailedBluffs() {

        return failedBluffs;
    }

    public int getRevelations() {

        return revelations;
    }

    public int getManipulationLevel() {

        return manipulationLevel;
    }

    public boolean isIdentityRevealed() {

        return identityRevealed;
    }

    public boolean wasLastActionLie() {

        return lastActionWasLie;
    }

    public boolean wasLastActionInterference() {

        return lastActionWasInterference;
    }

    // ============================================================
    // CLASSE DE RESULTADO DE MÃO
    // ============================================================

    public static class HandResult {

        public final int score;

        public final boolean instantWin;

        public final boolean veryStrong;

        public final boolean trinca;

        public final String description;

        public HandResult(
            int score,
            boolean instantWin,
            boolean veryStrong,
            boolean trinca,
            String description
        ) {

            this.score = score;

            this.instantWin =
                instantWin;

            this.veryStrong =
                veryStrong;

            this.trinca =
                trinca;

            this.description =
                description;
        }
    }

    // ============================================================
    // AÇÃO DO DEALER
    // ============================================================

    public static class DealerAction {

        public final String type;

        public final String message;

        public final boolean modifiesGame;

        public DealerAction(
            String type,
            String message,
            boolean modifiesGame
        ) {

            this.type = type;

            this.message = message;

            this.modifiesGame =
                modifiesGame;
        }
    }
}
