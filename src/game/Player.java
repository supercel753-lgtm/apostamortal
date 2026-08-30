```java
package game;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================================
 *                           PLAYER
 * ================================================================
 *
 * Representa Leo, o jogador.
 *
 * Responsabilidades:
 *
 * - Vida/estado do jogador
 * - Mão de cartas
 * - Fichas
 * - Medo
 * - Confiança
 * - Resistência à influência do Dealer
 * - Inventário
 * - Histórico de decisões
 * - Estado durante o Truco
 *
 * ================================================================
 */

public class Player {

    // ============================================================
    // IDENTIDADE
    // ============================================================

    private final String name;

    // ============================================================
    // ESTADOS FÍSICOS
    // ============================================================

    private int health;

    private int maxHealth;

    private int fear;

    private int confidence;

    // ============================================================
    // ESTADOS MENTAIS
    // ============================================================

    private int mentalStability;

    private int dealerResistance;

    // ============================================================
    // JOGO
    // ============================================================

    private int chips;

    private int currentBet;

    private boolean turn;

    private boolean wonLastRound;

    private boolean lostLastRound;

    // ============================================================
    // CARTAS
    // ============================================================

    private final List<String> hand;

    // ============================================================
    // INVENTÁRIO
    // ============================================================

    private Item.Inventory inventory;

    // ============================================================
    // HISTÓRICO
    // ============================================================

    private int roundsWon;

    private int roundsLost;

    private int trucoCalls;

    private int bluffs;

    private int compassionateChoices;

    private int cruelChoices;

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public Player() {

        this.name = "Leo";

        this.maxHealth = 100;

        this.health = 100;

        this.fear = 20;

        this.confidence = 50;

        this.mentalStability = 100;

        this.dealerResistance = 0;

        this.chips = 100;

        this.currentBet = 1;

        this.turn = false;

        this.wonLastRound = false;

        this.lostLastRound = false;

        this.hand =
            new ArrayList<>();

        this.inventory =
            Item.createStartingInventory();

        this.roundsWon = 0;

        this.roundsLost = 0;

        this.trucoCalls = 0;

        this.bluffs = 0;

        this.compassionateChoices = 0;

        this.cruelChoices = 0;
    }

    // ============================================================
    // IDENTIDADE
    // ============================================================

    public String getName() {

        return name;
    }

    // ============================================================
    // VIDA
    // ============================================================

    public int getHealth() {

        return health;
    }

    public int getMaxHealth() {

        return maxHealth;
    }

    public void takeDamage(
        int amount
    ) {

        if (amount <= 0) {

            return;
        }

        health -= amount;

        if (health < 0) {

            health = 0;
        }
    }

    public void heal(
        int amount
    ) {

        if (amount <= 0) {

            return;
        }

        health += amount;

        if (health > maxHealth) {

            health = maxHealth;
        }
    }

    public boolean isAlive() {

        return health > 0;
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
    // CONFIANÇA
    // ============================================================

    public int getConfidence() {

        return confidence;
    }

    public void increaseConfidence(
        int amount
    ) {

        confidence += amount;

        if (confidence > 100) {

            confidence = 100;
        }
    }

    public void decreaseConfidence(
        int amount
    ) {

        confidence -= amount;

        if (confidence < 0) {

            confidence = 0;
        }
    }

    // ============================================================
    // ESTABILIDADE MENTAL
    // ============================================================

    public int getMentalStability() {

        return mentalStability;
    }

    public void damageMentalStability(
        int amount
    ) {

        if (amount <= 0) {

            return;
        }

        mentalStability -= amount;

        if (mentalStability < 0) {

            mentalStability = 0;
        }
    }

    public void restoreMentalStability(
        int amount
    ) {

        if (amount <= 0) {

            return;
        }

        mentalStability += amount;

        if (mentalStability > 100) {

            mentalStability = 100;
        }
    }

    public boolean isMentallyStable() {

        return mentalStability >= 50;
    }

    // ============================================================
    // RESISTÊNCIA AO DEALER
    // ============================================================

    public int getDealerResistance() {

        return dealerResistance;
    }

    public void increaseDealerResistance(
        int amount
    ) {

        dealerResistance += amount;

        if (dealerResistance > 100) {

            dealerResistance = 100;
        }
    }

    public void decreaseDealerResistance(
        int amount
    ) {

        dealerResistance -= amount;

        if (dealerResistance < 0) {

            dealerResistance = 0;
        }
    }

    public boolean canResistDealer() {

        return dealerResistance >= 50;
    }

    // ============================================================
    // FICHAS
    // ============================================================

    public int getChips() {

        return chips;
    }

    public void addChips(
        int amount
    ) {

        if (amount > 0) {

            chips += amount;
        }
    }

    public boolean removeChips(
        int amount
    ) {

        if (amount <= 0) {

            return true;
        }

        if (chips < amount) {

            return false;
        }

        chips -= amount;

        return true;
    }

    // ============================================================
    // APOSTA
    // ============================================================

    public int getCurrentBet() {

        return currentBet;
    }

    public void setCurrentBet(
        int bet
    ) {

        if (bet < 1) {

            bet = 1;
        }

        currentBet = bet;
    }

    public void resetBet() {

        currentBet = 1;
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
    // CARTAS
    // ============================================================

    public void addCard(
        String card
    ) {

        if (card == null) {

            return;
        }

        hand.add(card);
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
    // RESULTADO DA RODADA
    // ============================================================

    public boolean wonLastRound() {

        return wonLastRound;
    }

    public boolean lostLastRound() {

        return lostLastRound;
    }

    public void registerWin() {

        roundsWon++;

        wonLastRound = true;

        lostLastRound = false;
    }

    public void registerLoss() {

        roundsLost++;

        wonLastRound = false;

        lostLastRound = true;
    }

    // ============================================================
    // ESTATÍSTICAS
    // ============================================================

    public int getRoundsWon() {

        return roundsWon;
    }

    public int getRoundsLost() {

        return roundsLost;
    }

    public int getTrucoCalls() {

        return trucoCalls;
    }

    public int getBluffs() {

        return bluffs;
    }

    public void registerTruco() {

        trucoCalls++;
    }

    public void registerBluff() {

        bluffs++;
    }

    // ============================================================
    // ESCOLHAS
    // ============================================================

    public void registerCompassionateChoice() {

        compassionateChoices++;

        increaseConfidence(2);

        increaseDealerResistance(1);
    }

    public void registerCruelChoice() {

        cruelChoices++;

        decreaseConfidence(2);

        increaseFear(2);
    }

    public int getCompassionateChoices() {

        return compassionateChoices;
    }

    public int getCruelChoices() {

        return cruelChoices;
    }

    // ============================================================
    // INVENTÁRIO
    // ============================================================

    public Item.Inventory getInventory() {

        return inventory;
    }

    public void setInventory(
        Item.Inventory inventory
    ) {

        if (inventory != null) {

            this.inventory = inventory;
        }
    }

    public boolean useItem(
        String itemId,
        GameState state,
        Girl alice
    ) {

        if (inventory == null) {

            return false;
        }

        String result =
            inventory.use(
                itemId,
                state,
                alice
            );

        System.out.println(result);

        return true;
    }

    // ============================================================
    // ESTADOS ESPECIAIS
    // ============================================================

    public boolean hasHighFear() {

        return fear >= 75;
    }

    public boolean hasLowFear() {

        return fear <= 25;
    }

    public boolean hasHighConfidence() {

        return confidence >= 75;
    }

    public boolean hasLowConfidence() {

        return confidence <= 25;
    }

    public boolean hasHighMentalStability() {

        return mentalStability >= 75;
    }

    public boolean hasLowMentalStability() {

        return mentalStability <= 25;
    }

    // ============================================================
    // RESET DA RODADA
    // ============================================================

    public void resetRound() {

        clearHand();

        resetBet();

        setTurn(false);

        wonLastRound = false;

        lostLastRound = false;
    }

    // ============================================================
    // STATUS
    // ============================================================

    public String getStatus() {

        StringBuilder status =
            new StringBuilder();

        status.append(
            "================================\n"
        );

        status.append(
            "              LEO\n"
        );

        status.append(
            "================================\n"
        );

        status.append(
            "Vida: "
        );

        status.append(
            health
        );

        status.append(
            "/"
        );

        status.append(
            maxHealth
        );

        status.append("\n");

        status.append(
            "Medo: "
        );

        status.append(
            fear
        );

        status.append("\n");

        status.append(
            "Confiança: "
        );

        status.append(
            confidence
        );

        status.append("\n");

        status.append(
            "Estabilidade: "
        );

        status.append(
            mentalStability
        );

        status.append("\n");

        status.append(
            "Resistência: "
        );

        status.append(
            dealerResistance
        );

        status.append("\n");

        status.append(
            "Fichas: "
        );

        status.append(
            chips
        );

        status.append("\n");

        status.append(
            "Vitórias: "
        );

        status.append(
            roundsWon
        );

        status.append("\n");

        status.append(
            "Derrotas: "
        );

        status.append(
            roundsLost
        );

        status.append("\n");

        status.append(
            "================================"
        );

        return status.toString();
    }

    // ============================================================
    // DEBUG
    // ============================================================

    public void printStatus() {

        System.out.println(
            getStatus()
        );
    }
}
```

