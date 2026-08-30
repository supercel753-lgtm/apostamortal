package game;

/**
 * ================================================================
 *                         GAME STATE
 * ================================================================
 *
 * Guarda tudo que precisa ser lembrado durante uma partida.
 *
 * Game.java       -> controla a partida
 * GameState.java  -> guarda o estado
 * GameDevice.java -> controla a máquina
 * Dialogues.java  -> guarda os diálogos
 * Dealer.java     -> controla o Dealer
 *
 * ================================================================
 */

public class GameState {

    // ============================================================
    // ESTADOS DA PARTIDA
    // ============================================================

    public enum State {

        INTRO,

        WAITING,

        PLAYER_TURN,

        ALICE_TURN,

        DEALER_TURN,

        TRUCO,

        ROUND_END,

        DIALOGUE,

        EVENT,

        REVELATION,

        FINAL,

        GAME_OVER
    }

    // ============================================================
    // ESTADO ATUAL
    // ============================================================

    private State currentState;

    // ============================================================
    // RODADAS
    // ============================================================

    private int round;

    private int playerWins;

    private int aliceWins;

    private int draws;

    // ============================================================
    // PONTUAÇÃO DO TRUCO
    // ============================================================

    private int playerScore;

    private int aliceScore;

    private int currentBet;

    // ============================================================
    // VEZ
    // ============================================================

    private boolean playerTurn;

    private boolean aliceTurn;

    // ============================================================
    // CARTAS
    // ============================================================

    private int playerCards;

    private int aliceCards;

    private boolean playerHasWinningHand;

    private boolean aliceHasWinningHand;

    // ============================================================
    // HISTÓRIA
    // ============================================================

    private boolean discoveredBrother;

    private boolean discoveredAlice;

    private boolean discoveredRelationship;

    private boolean discoveredMessages;

    private boolean discoveredExpulsion;

    private boolean discoveredBreakup;

    private boolean discoveredAccident;

    private boolean discoveredDealersIdentity;

    private boolean discoveredTruth;

    // ============================================================
    // PROGRESSO NARRATIVO
    // ============================================================

    private int dialogueProgress;

    private int revelationLevel;

    private int suspicionLevel;

    // ============================================================
    // ESCOLHAS
    // ============================================================

    private int choicesMade;

    private int aggressiveChoices;

    private int compassionateChoices;

    private int suspiciousChoices;

    // ============================================================
    // DEALER
    // ============================================================

    private boolean dealerRevealed;

    private boolean dealerManipulating;

    private boolean dealerLied;

    private int dealerInterventions;

    // ============================================================
    // ALICE
    // ============================================================

    private int aliceTrust;

    private int aliceAnger;

    private int aliceFear;

    private boolean aliceKnowsTruth;

    // ============================================================
    // PLAYER
    // ============================================================

    private int playerConfidence;

    private int playerFear;

    private boolean playerKnowsTruth;

    // ============================================================
    // FINAL
    // ============================================================

    private boolean playerEscaped;

    private boolean aliceEscaped;

    private boolean badEnding;

    private String endingType;

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public GameState() {

        reset();
    }

    // ============================================================
    // RESET
    // ============================================================

    public void reset() {

        currentState = State.INTRO;

        round = 0;

        playerWins = 0;

        aliceWins = 0;

        draws = 0;

        playerScore = 0;

        aliceScore = 0;

        currentBet = 1;

        playerTurn = true;

        aliceTurn = false;

        playerCards = 0;

        aliceCards = 0;

        playerHasWinningHand = false;

        aliceHasWinningHand = false;

        discoveredBrother = false;

        discoveredAlice = false;

        discoveredRelationship = false;

        discoveredMessages = false;

        discoveredExpulsion = false;

        discoveredBreakup = false;

        discoveredAccident = false;

        discoveredDealersIdentity = false;

        discoveredTruth = false;

        dialogueProgress = 0;

        revelationLevel = 0;

        suspicionLevel = 0;

        choicesMade = 0;

        aggressiveChoices = 0;

        compassionateChoices = 0;

        suspiciousChoices = 0;

        dealerRevealed = false;

        dealerManipulating = false;

        dealerLied = false;

        dealerInterventions = 0;

        aliceTrust = 0;

        aliceAnger = 0;

        aliceFear = 0;

        aliceKnowsTruth = false;

        playerConfidence = 0;

        playerFear = 0;

        playerKnowsTruth = false;

        playerEscaped = false;

        aliceEscaped = false;

        badEnding = false;

        endingType = "";
    }

    // ============================================================
    // ESTADO
    // ============================================================

    public State getCurrentState() {

        return currentState;
    }

    public void setState(State state) {

        if (state == null) {

            return;
        }

        currentState = state;
    }

    public boolean is(State state) {

        return currentState == state;
    }

    // ============================================================
    // RODADAS
    // ============================================================

    public void nextRound() {

        round++;

        currentBet = 1;

        playerHasWinningHand = false;

        aliceHasWinningHand = false;

        currentState = State.PLAYER_TURN;
    }

    public int getRound() {

        return round;
    }

    public int getPlayerWins() {

        return playerWins;
    }

    public int getAliceWins() {

        return aliceWins;
    }

    public int getDraws() {

        return draws;
    }

    public void playerWinsRound() {

        playerWins++;
    }

    public void aliceWinsRound() {

        aliceWins++;
    }

    public void registerDraw() {

        draws++;
    }

    // ============================================================
    // PONTUAÇÃO
    // ============================================================

    public int getPlayerScore() {

        return playerScore;
    }

    public int getAliceScore() {

        return aliceScore;
    }

    public int getCurrentBet() {

        return currentBet;
    }

    public void setCurrentBet(
        int value
    ) {

        if (value < 1) {

            value = 1;
        }

        currentBet = value;
    }

    public void increaseBet() {

        switch (currentBet) {

            case 1:

                currentBet = 3;

                break;

            case 3:

                currentBet = 6;

                break;

            case 6:

                currentBet = 9;

                break;

            case 9:

                currentBet = 12;

                break;

            default:

                currentBet = 12;

                break;
        }
    }

    public void addPlayerScore(
        int amount
    ) {

        if (amount > 0) {

            playerScore += amount;
        }
    }

    public void addAliceScore(
        int amount
    ) {

        if (amount > 0) {

            aliceScore += amount;
        }
    }

    // ============================================================
    // VEZ
    // ============================================================

    public void setPlayerTurn() {

        playerTurn = true;

        aliceTurn = false;

        currentState = State.PLAYER_TURN;
    }

    public void setAliceTurn() {

        playerTurn = false;

        aliceTurn = true;

        currentState = State.ALICE_TURN;
    }

    public boolean isPlayerTurn() {

        return playerTurn;
    }

    public boolean isAliceTurn() {

        return aliceTurn;
    }

    // ============================================================
    // CARTAS
    // ============================================================

    public void setPlayerCards(
        int cards
    ) {

        playerCards = Math.max(
            0,
            cards
        );
    }

    public void setAliceCards(
        int cards
    ) {

        aliceCards = Math.max(
            0,
            cards
        );
    }

    public int getPlayerCards() {

        return playerCards;
    }

    public int getAliceCards() {

        return aliceCards;
    }

    // ============================================================
    // MÃO VENCEDORA
    // ============================================================

    public void setPlayerWinningHand(
        boolean value
    ) {

        playerHasWinningHand = value;
    }

    public void setAliceWinningHand(
        boolean value
    ) {

        aliceHasWinningHand = value;
    }

    public boolean playerHasWinningHand() {

        return playerHasWinningHand;
    }

    public boolean aliceHasWinningHand() {

        return aliceHasWinningHand;
    }

    // ============================================================
    // DESCOBERTAS
    // ============================================================

    public boolean discoveredBrother() {

        return discoveredBrother;
    }

    public void discoverBrother() {

        discoveredBrother = true;

        increaseRevelation();
    }

    public boolean discoveredAlice() {

        return discoveredAlice;
    }

    public void discoverAlice() {

        discoveredAlice = true;

        increaseRevelation();
    }

    public boolean discoveredRelationship() {

        return discoveredRelationship;
    }

    public void discoverRelationship() {

        discoveredRelationship = true;

        increaseRevelation();
    }

    public boolean discoveredMessages() {

        return discoveredMessages;
    }

    public void discoverMessages() {

        discoveredMessages = true;

        increaseRevelation();
    }

    public boolean discoveredExpulsion() {

        return discoveredExpulsion;
    }

    public void discoverExpulsion() {

        discoveredExpulsion = true;

        increaseRevelation();
    }

    public boolean discoveredBreakup() {

        return discoveredBreakup;
    }

    public void discoverBreakup() {

        discoveredBreakup = true;

        increaseRevelation();
    }

    public boolean discoveredAccident() {

        return discoveredAccident;
    }

    public void discoverAccident() {

        discoveredAccident = true;

        increaseRevelation();
    }

    public boolean discoveredDealersIdentity() {

        return discoveredDealersIdentity;
    }

    public void discoverDealersIdentity() {

        discoveredDealersIdentity = true;

        dealerRevealed = true;

        increaseRevelation();
    }

    public boolean discoveredTruth() {

        return discoveredTruth;
    }

    public void discoverTruth() {

        discoveredTruth = true;

        increaseRevelation();
    }

    // ============================================================
    // PROGRESSO
    // ============================================================

    public int getDialogueProgress() {

        return dialogueProgress;
    }

    public void advanceDialogue() {

        dialogueProgress++;
    }

    public int getRevelationLevel() {

        return revelationLevel;
    }

    public void increaseRevelation() {

        if (revelationLevel < 100) {

            revelationLevel++;
        }
    }

    // ============================================================
    // SUSPEITA
    // ============================================================

    public int getSuspicionLevel() {

        return suspicionLevel;
    }

    public void increaseSuspicion() {

        if (suspicionLevel < 100) {

            suspicionLevel++;
        }
    }

    public void increaseSuspicion(
        int amount
    ) {

        suspicionLevel += amount;

        if (suspicionLevel > 100) {

            suspicionLevel = 100;
        }
    }

    // ============================================================
    // ESCOLHAS
    // ============================================================

    public void registerChoice() {

        choicesMade++;
    }

    public void registerAggressiveChoice() {

        choicesMade++;

        aggressiveChoices++;

        aliceAnger += 3;

        if (aliceAnger > 100) {

            aliceAnger = 100;
        }
    }

    public void registerCompassionateChoice() {

        choicesMade++;

        compassionateChoices++;

        aliceTrust += 3;

        if (aliceTrust > 100) {

            aliceTrust = 100;
        }
    }

    public void registerSuspiciousChoice() {

        choicesMade++;

        suspiciousChoices++;

        increaseSuspicion(4);
    }

    public int getChoicesMade() {

        return choicesMade;
    }

    public int getAggressiveChoices() {

        return aggressiveChoices;
    }

    public int getCompassionateChoices() {

        return compassionateChoices;
    }

    public int getSuspiciousChoices() {

        return suspiciousChoices;
    }

    // ============================================================
    // DEALER
    // ============================================================

    public void dealerIntervened() {

        dealerInterventions++;

        dealerManipulating = true;
    }

    public int getDealerInterventions() {

        return dealerInterventions;
    }

    public boolean isDealerRevealed() {

        return dealerRevealed;
    }

    public void setDealerManipulating(
        boolean value
    ) {

        dealerManipulating = value;
    }

    public boolean isDealerManipulating() {

        return dealerManipulating;
    }

    public void dealerLied() {

        dealerLied = true;

        increaseSuspicion();
    }

    public boolean didDealerLie() {

        return dealerLied;
    }

    // ============================================================
    // ALICE
    // ============================================================

    public int getAliceTrust() {

        return aliceTrust;
    }

    public int getAliceAnger() {

        return aliceAnger;
    }

    public int getAliceFear() {

        return aliceFear;
    }

    public void increaseAliceFear(
        int amount
    ) {

        aliceFear += amount;

        if (aliceFear > 100) {

            aliceFear = 100;
        }
    }

    public void decreaseAliceFear(
        int amount
    ) {

        aliceFear -= amount;

        if (aliceFear < 0) {

            aliceFear = 0;
        }
    }

    public void setAliceKnowsTruth(
        boolean value
    ) {

        aliceKnowsTruth = value;
    }

    public boolean aliceKnowsTruth() {

        return aliceKnowsTruth;
    }

    // ============================================================
    // PLAYER
    // ============================================================

    public int getPlayerConfidence() {

        return playerConfidence;
    }

    public void increasePlayerConfidence(
        int amount
    ) {

        playerConfidence += amount;

        if (playerConfidence > 100) {

            playerConfidence = 100;
        }
    }

    public int getPlayerFear() {

        return playerFear;
    }

    public void increasePlayerFear(
        int amount
    ) {

        playerFear += amount;

        if (playerFear > 100) {

            playerFear = 100;
        }
    }

    public void decreasePlayerFear(
        int amount
    ) {

        playerFear -= amount;

        if (playerFear < 0) {

            playerFear = 0;
        }
    }

    public void setPlayerKnowsTruth(
        boolean value
    ) {

        playerKnowsTruth = value;
    }

    public boolean playerKnowsTruth() {

        return playerKnowsTruth;
    }

    // ============================================================
    // FINAL
    // ============================================================

    public void setPlayerEscaped(
        boolean value
    ) {

        playerEscaped = value;
    }

    public void setAliceEscaped(
        boolean value
    ) {

        aliceEscaped = value;
    }

    public boolean playerEscaped() {

        return playerEscaped;
    }

    public boolean aliceEscaped() {

        return aliceEscaped;
    }

    public void setBadEnding(
        boolean value
    ) {

        badEnding = value;
    }

    public boolean isBadEnding() {

        return badEnding;
    }

    public void setEndingType(
        String type
    ) {

        if (type == null) {

            endingType = "UNKNOWN";

        } else {

            endingType = type;
        }
    }

    public String getEndingType() {

        return endingType;
    }

    // ============================================================
    // RESUMO DA HISTÓRIA
    // ============================================================

    public String getStoryProgress() {

        StringBuilder result =
            new StringBuilder();

        result.append(
            "PROGRESSO DA HISTÓRIA\n"
        );

        result.append(
            "Irmão: "
        );

        result.append(
            discoveredBrother
                ? "DESCOBERTO"
                : "???"
        );

        result.append("\n");

        result.append(
            "Alice: "
        );

        result.append(
            discoveredAlice
                ? "DESCOBERTA"
                : "???"
        );

        result.append("\n");

        result.append(
            "Relacionamento: "
        );

        result.append(
            discoveredRelationship
                ? "DESCOBERTO"
                : "???"
        );

        result.append("\n");

        result.append(
            "Mensagens: "
        );

        result.append(
            discoveredMessages
                ? "DESCOBERTAS"
                : "???"
        );

        result.append("\n");

        result.append(
            "Expulsão: "
        );

        result.append(
            discoveredExpulsion
                ? "DESCOBERTA"
                : "???"
        );

        result.append("\n");

        result.append(
            "Término: "
        );

        result.append(
            discoveredBreakup
                ? "DESCOBERTO"
                : "???"
        );

        result.append("\n");

        result.append(
            "Acidente: "
        );

        result.append(
            discoveredAccident
                ? "DESCOBERTO"
                : "???"
        );

        result.append("\n");

        result.append(
            "Identidade do Dealer: "
        );

        result.append(
            discoveredDealersIdentity
                ? "DESCOBERTA"
                : "???"
        );

        return result.toString();
    }

    // ============================================================
    // RESUMO COMPLETO
    // ============================================================

    public String getStatus() {

        StringBuilder result =
            new StringBuilder();

        result.append(
            "================================\n"
        );

        result.append(
            "          GAME STATE\n"
        );

        result.append(
            "================================\n"
        );

        result.append(
            "Estado: "
        );

        result.append(
            currentState
        );

        result.append("\n");

        result.append(
            "Rodada: "
        );

        result.append(
            round
        );

        result.append("\n");

        result.append(
            "Placar: "
        );

        result.append(
            playerScore
        );

        result.append(
            " x "
        );

        result.append(
            aliceScore
        );

        result.append("\n");

        result.append(
            "Aposta: "
        );

        result.append(
            currentBet
        );

        result.append("\n");

        result.append(
            "Revelação: "
        );

        result.append(
            revelationLevel
        );

        result.append("%\n");

        result.append(
            "Suspeita: "
        );

        result.append(
            suspicionLevel
        );

        result.append("%\n");

        result.append(
            "Confiança Alice: "
        );

        result.append(
            aliceTrust
        );

        result.append("\n");

        result.append(
            "Medo Alice: "
        );

        result.append(
            aliceFear
        );

        result.append("\n");

        result.append(
            "Medo Leo: "
        );

        result.append(
            playerFear
        );

        result.append("\n");

        result.append(
            "================================"
        );

        return result.toString();
    }
}
