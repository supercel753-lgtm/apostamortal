```java
package game;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================================
 *                            GIRL
 * ================================================================
 *
 * Representa Alice.
 *
 * Alice não é apenas uma "barra de confiança".
 * O comportamento dela muda conforme:
 *
 * - confiança em Leo
 * - medo
 * - desconfiança
 * - influência do Dealer
 * - descobertas
 *
 * ================================================================
 */

public class Girl {

    private final String name;

    // ============================================================
    // ESTADO
    // ============================================================

    private int trust;

    private int fear;

    private int suspicion;

    private int anger;

    private int hope;

    // ============================================================
    // RELAÇÃO
    // ============================================================

    private boolean trustsLeo;

    private boolean distrustsLeo;

    private boolean remembersBrother;

    private boolean knowsDealersIdentity;

    // ============================================================
    // PARTIDA
    // ============================================================

    private final List<String> hand;

    private int score;

    private boolean turn;

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public Girl() {

        name = "Alice";

        trust = 20;

        fear = 35;

        suspicion = 10;

        anger = 5;

        hope = 40;

        trustsLeo = false;

        distrustsLeo = false;

        remembersBrother = false;

        knowsDealersIdentity = false;

        hand =
            new ArrayList<>();

        score = 0;

        turn = false;
    }

    // ============================================================
    // IDENTIDADE
    // ============================================================

    public String getName() {

        return name;
    }

    // ============================================================
    // CONFIANÇA
    // ============================================================

    public int getTrust() {

        return trust;
    }

    public void increaseTrust(
        int amount
    ) {

        trust += amount;

        if (trust > 100) {

            trust = 100;
        }

        updateRelationship();
    }

    public void decreaseTrust(
        int amount
    ) {

        trust -= amount;

        if (trust < 0) {

            trust = 0;
        }

        updateRelationship();
    }

    private void updateRelationship() {

        trustsLeo = trust >= 60;

        distrustsLeo = trust <= 20;
    }

    public boolean trustsLeo() {

        return trustsLeo;
    }

    public boolean distrustsLeo() {

        return distrustsLeo;
    }

    // ============================================================
    // MEDO
    // ============================================================

    public int getFear() {

        return fear;
    }

    public void increaseFear(
        int amount
    ) {

        fear += amount;

        if (fear > 100) {

            fear = 100;
        }
    }

    public void decreaseFear(
        int amount
    ) {

        fear -= amount;

        if (fear < 0) {

            fear = 0;
        }
    }

    // ============================================================
    // SUSPEITA
    // ============================================================

    public int getSuspicion() {

        return suspicion;
    }

    public void increaseSuspicion(
        int amount
    ) {

        suspicion += amount;

        if (suspicion > 100) {

            suspicion = 100;
        }
    }

    public void decreaseSuspicion(
        int amount
    ) {

        suspicion -= amount;

        if (suspicion < 0) {

            suspicion = 0;
        }
    }

    // ============================================================
    // RAIVA
    // ============================================================

    public int getAnger() {

        return anger;
    }

    public void increaseAnger(
        int amount
    ) {

        anger += amount;

        if (anger > 100) {

            anger = 100;
        }
    }

    public void decreaseAnger(
        int amount
    ) {

        anger -= amount;

        if (anger < 0) {

            anger = 0;
        }
    }

    // ============================================================
    // ESPERANÇA
    // ============================================================

    public int getHope() {

        return hope;
    }

    public void increaseHope(
        int amount
    ) {

        hope += amount;

        if (hope > 100) {

            hope = 100;
        }
    }

    public void decreaseHope(
        int amount
    ) {

        hope -= amount;

        if (hope < 0) {

            hope = 0;
        }
    }

    // ============================================================
    // MEMÓRIA
    // ============================================================

    public boolean remembersBrother() {

        return remembersBrother;
    }

    public void triggerMemory() {

        remembersBrother = true;

        increaseSuspicion(5);

        increaseHope(5);
    }

    // ============================================================
    // DEALER
    // ============================================================

    public boolean knowsDealersIdentity() {

        return knowsDealersIdentity;
    }

    public void revealDealerIdentity() {

        knowsDealersIdentity = true;

        increaseSuspicion(20);

        increaseFear(10);
    }

    // ============================================================
    // CARTAS
    // ============================================================

    public void addCard(
        String card
    ) {

        if (card != null) {

            hand.add(card);
        }
    }

    public void removeCard(
        String card
    ) {

        hand.remove(card);
    }

    public void clearHand() {

        hand.clear();
    }

    public List<String> getHand() {

        return new ArrayList<>(
            hand
        );
    }

    public int getHandSize() {

        return hand.size();
    }

    public String getCard(
        int index
    ) {

        if (
            index < 0 ||
            index >= hand.size()
        ) {

            return null;
        }

        return hand.get(index);
    }

    // ============================================================
    // PONTUAÇÃO
    // ============================================================

    public int getScore() {

        return score;
    }

    public void addScore(
        int amount
    ) {

        score += amount;
    }

    public void resetScore() {

        score = 0;
    }

    // ============================================================
    // TURNO
    // ============================================================

    public boolean isTurn() {

        return turn;
    }

    public void setTurn(
        boolean turn
    ) {

        this.turn = turn;
    }

    // ============================================================
    // COMPORTAMENTO
    // ============================================================

    public String getMood() {

        if (fear >= 80) {

            return "APAVORADA";
        }

        if (anger >= 75) {

            return "FURIOSA";
        }

        if (trust >= 75) {

            return "CONFIANTE";
        }

        if (suspicion >= 70) {

            return "DESCONFIADA";
        }

        if (hope >= 70) {

            return "ESPERANÇOSA";
        }

        return "TENSIONADA";
    }

    // ============================================================
    // DECISÃO
    // ============================================================

    public boolean isLikelyToTrustLeo() {

        return
            trust >= 60 &&
            suspicion < 60;
    }

    public boolean isLikelyToBelieveDealer() {

        return
            suspicion < 40 &&
            fear > trust;
    }

    public boolean canReachGoodEnding() {

        return
            trust >= 75 &&
            hope >= 60;
    }

    // ============================================================
    // RESET
    // ============================================================

    public void resetRound() {

        clearHand();

        setTurn(false);
    }

    // ============================================================
    // STATUS
    // ============================================================

    public String getStatus() {

        return
            "================================\n" +
            "              ALICE\n" +
            "================================\n" +
            "Confiança: " + trust + "\n" +
            "Medo: " + fear + "\n" +
            "Suspeita: " + suspicion + "\n" +
            "Raiva: " + anger + "\n" +
            "Esperança: " + hope + "\n" +
            "Humor: " + getMood() + "\n" +
            "Pontuação: " + score + "\n" +
            "================================";
    }

    public void printStatus() {

        System.out.println(
            getStatus()
        );
    }
}
```
