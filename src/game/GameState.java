```java
package game;

/**
 * ================================================================
 *                         GAME STATE
 * ================================================================
 *
 * Guarda o estado global da partida.
 *
 * GameState NÃO controla o jogo.
 * Ele apenas guarda as informações que o Game precisa consultar.
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
        DIALOGUE,
        REVELATION,
        FINAL,
        GAME_OVER
    }

    private State currentState;

    // ============================================================
    // TEMPO
    // ============================================================

    private int hour;
    private int minute;
    private int seconds;

    private boolean clockRunning;

    // ============================================================
    // RELACIONAMENTO
    // ============================================================

    private int aliceTrust;

    private int suspicionLevel;

    private int dealerInfluence;

    private int dealerRedemption;

    // ============================================================
    // DESCOBERTAS
    // ============================================================

    private boolean discoveredDealersIdentity;

    private boolean discoveredTruth;

    private boolean discoveredRelationship;

    private boolean discoveredFamilyConflict;

    private boolean discoveredAccident;

    // ============================================================
    // PARTIDA
    // ============================================================

    private int roundNumber;

    private int playerScore;

    private int aliceScore;

    private int tableValue;

    private boolean playerHasWonRound;

    private boolean aliceHasWonRound;

    private boolean instantVictoryDetected;

    // ============================================================
    // FINAIS
    // ============================================================

    private int ending;

    private boolean gameFinished;

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public GameState() {

        currentState = State.INTRO;

        hour = 23;
        minute = 47;
        seconds = 0;

        clockRunning = true;

        aliceTrust = 20;

        suspicionLevel = 0;

        dealerInfluence = 30;

        dealerRedemption = 0;

        discoveredDealersIdentity = false;

        discoveredTruth = false;

        discoveredRelationship = false;

        discoveredFamilyConflict = false;

        discoveredAccident = false;

        roundNumber = 1;

        playerScore = 0;

        aliceScore = 0;

        tableValue = 1;

        playerHasWonRound = false;

        aliceHasWonRound = false;

        instantVictoryDetected = false;

        ending = 0;

        gameFinished = false;
    }

    // ============================================================
    // ESTADO
    // ============================================================

    public State getCurrentState() {

        return currentState;
    }

    public void setCurrentState(
        State state
    ) {

        if (state != null) {

            currentState = state;
        }
    }

    // ============================================================
    // RELÓGIO
    // ============================================================

    public int getHour() {

        return hour;
    }

    public int getMinute() {

        return minute;
    }

    public int getSeconds() {

        return seconds;
    }

    public boolean isClockRunning() {

        return clockRunning;
    }

    public void setClockRunning(
        boolean running
    ) {

        clockRunning = running;
    }

    public void advanceTime(
        int secondsToAdd
    ) {

        if (secondsToAdd <= 0) {

            return;
        }

        seconds += secondsToAdd;

        while (seconds >= 60) {

            seconds -= 60;
            minute++;
        }

        while (minute >= 60) {

            minute -= 60;
            hour++;
        }

        while (hour >= 24) {

            hour -= 24;
        }
    }

    public String getFormattedTime() {

        return String.format(
            "%02d:%02d:%02d",
            hour,
            minute,
            seconds
        );
    }

    // ============================================================
    // ALICE
    // ============================================================

    public int getAliceTrust() {

        return aliceTrust;
    }

    public void increaseAliceTrust(
        int amount
    ) {

        aliceTrust += amount;

        if (aliceTrust > 100) {

            aliceTrust = 100;
        }
    }

    public void decreaseAliceTrust(
        int amount
    ) {

        aliceTrust -= amount;

        if (aliceTrust < 0) {

            aliceTrust = 0;
        }
    }

    // ============================================================
    // SUSPEITA
    // ============================================================

    public int getSuspicionLevel() {

        return suspicionLevel;
    }

    public void increaseSuspicion(
        int amount
    ) {

        suspicionLevel += amount;

        if (suspicionLevel > 100) {

            suspicionLevel = 100;
        }
    }

    public void decreaseSuspicion(
        int amount
    ) {

        suspicionLevel -= amount;

        if (suspicionLevel < 0) {

            suspicionLevel = 0;
        }
    }

    // ============================================================
    // DEALER
    // ============================================================

    public int getDealerInfluence() {

        return dealerInfluence;
    }

    public void increaseDealerInfluence(
        int amount
    ) {

        dealerInfluence += amount;

        if (dealerInfluence > 100) {

            dealerInfluence = 100;
        }
    }

    public void decreaseDealerInfluence(
        int amount
    ) {

        dealerInfluence -= amount;

        if (dealerInfluence < 0) {

            dealerInfluence = 0;
        }
    }

    public int getDealerRedemption() {

        return dealerRedemption;
    }

    public void increaseDealerRedemption(
        int amount
    ) {

        dealerRedemption += amount;

        if (dealerRedemption > 100) {

            dealerRedemption = 100;
        }
    }

    // ============================================================
    // DESCOBERTAS
    // ============================================================

    public boolean discoveredDealersIdentity() {

        return discoveredDealersIdentity;
    }

    public void discoverDealerIdentity() {

        discoveredDealersIdentity = true;

        increaseSuspicion(20);
    }

    public boolean discoveredTruth() {

        return discoveredTruth;
    }

    public void discoverTruth() {

        discoveredTruth = true;

        increaseSuspicion(15);
    }

    public boolean discoveredRelationship() {

        return discoveredRelationship;
    }

    public void discoverRelationship() {

        discoveredRelationship = true;

        increaseAliceTrust(5);
    }

    public boolean discoveredFamilyConflict() {

        return discoveredFamilyConflict;
    }

    public void discoverFamilyConflict() {

        discoveredFamilyConflict = true;
    }

    public boolean discoveredAccident() {

        return discoveredAccident;
    }

    public void discoverAccident() {

        discoveredAccident = true;
    }

    // ============================================================
    // PARTIDA
    // ============================================================

    public int getRoundNumber() {

        return roundNumber;
    }

    public void nextRound() {

        roundNumber++;

        playerHasWonRound = false;

        aliceHasWonRound = false;

        instantVictoryDetected = false;

        tableValue = 1;
    }

    public int getPlayerScore() {

        return playerScore;
    }

    public int getAliceScore() {

        return aliceScore;
    }

    public void addPlayerScore(
        int amount
    ) {

        playerScore += amount;
    }

    public void addAliceScore(
        int amount
    ) {

        aliceScore += amount;
    }

    public int getTableValue() {

        return tableValue;
    }

    public void setTableValue(
        int value
    ) {

        tableValue = Math.max(
            1,
            value
        );
    }

    public boolean playerWonRound() {

        return playerHasWonRound;
    }

    public boolean aliceWonRound() {

        return aliceHasWonRound;
    }

    public void setPlayerWonRound(
        boolean value
    ) {

        playerHasWonRound = value;
    }

    public void setAliceWonRound(
        boolean value
    ) {

        aliceHasWonRound = value;
    }

    // ============================================================
    // VITÓRIA INSTANTÂNEA
    // ============================================================

    public boolean instantVictoryDetected() {

        return instantVictoryDetected;
    }

    public void setInstantVictoryDetected(
        boolean value
    ) {

        instantVictoryDetected = value;
    }

    // ============================================================
    // FINAL
    // ============================================================

    public boolean isGameFinished() {

        return gameFinished;
    }

    public void setGameFinished(
        boolean value
    ) {

        gameFinished = value;
    }

    public int getEnding() {

        return ending;
    }

    public void setEnding(
        int ending
    ) {

        if (ending >= 1 && ending <= 6) {

            this.ending = ending;
        }
    }

    // ============================================================
    // CONDIÇÕES DE FINAL
    // ============================================================

    public boolean canReachGoodEnding() {

        return
            aliceTrust >= 70 &&
            dealerRedemption >= 60 &&
            discoveredTruth;
    }

    public boolean canReachBestEnding() {

        return
            aliceTrust >= 85 &&
            dealerRedemption >= 80 &&
            discoveredTruth &&
            discoveredDealersIdentity;
    }
}
```
