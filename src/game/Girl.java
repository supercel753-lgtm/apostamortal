package game;

import java.util.Random;

/**
 * ================================================================
 *                           GIRL
 * ================================================================
 *
 * Representa Alice.
 *
 * Alice não é apenas uma adversária:
 *
 * - possui personalidade
 * - possui confiança
 * - possui medo
 * - possui raiva
 * - pode blefar
 * - pode hesitar
 * - pode provocar Leo
 * - pode cooperar
 * - pode desconfiar do Dealer
 * - possui memória fragmentada
 *
 * O GameState continua sendo a fonte oficial do estado da partida.
 * Esta classe controla principalmente o comportamento da Alice.
 *
 * ================================================================
 */

public class Girl {

    // ============================================================
    // IDENTIDADE
    // ============================================================

    private final String name;

    // ============================================================
    // ALEATORIEDADE
    // ============================================================

    private final Random random;

    // ============================================================
    // PERSONALIDADE
    // ============================================================

    private int patience;

    private int confidence;

    private int suspicion;

    private int stubbornness;

    private int honesty;

    // ============================================================
    // ESTADO EMOCIONAL
    // ============================================================

    private int fear;

    private int anger;

    private int sadness;

    private int trust;

    // ============================================================
    // JOGO
    // ============================================================

    private int roundWins;

    private int roundLosses;

    private int currentScore;

    private int cardsPlayed;

    private int bluffs;

    private int successfulBluffs;

    // ============================================================
    // COMPORTAMENTO
    // ============================================================

    private boolean nervous;

    private boolean angry;

    private boolean afraid;

    private boolean suspicious;

    private boolean cooperative;

    private boolean bluffing;

    private boolean knowsDealer;

    private boolean remembersLeo;

    // ============================================================
    // HISTÓRIA
    // ============================================================

    private boolean remembersBrother;

    private boolean remembersRelationship;

    private boolean remembersBreakup;

    private boolean remembersAccident;

    private boolean knowsWhyTheyAreHere;

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public Girl() {

        name = "Alice";

        random = new Random();

        reset();
    }

    // ============================================================
    // RESET
    // ============================================================

    public void reset() {

        patience = 65;

        confidence = 50;

        suspicion = 10;

        stubbornness = 70;

        honesty = 65;

        fear = 25;

        anger = 10;

        sadness = 40;

        trust = 0;

        roundWins = 0;

        roundLosses = 0;

        currentScore = 0;

        cardsPlayed = 0;

        bluffs = 0;

        successfulBluffs = 0;

        nervous = false;

        angry = false;

        afraid = false;

        suspicious = false;

        cooperative = false;

        bluffing = false;

        knowsDealer = true;

        remembersLeo = false;

        remembersBrother = true;

        remembersRelationship = true;

        remembersBreakup = true;

        remembersAccident = true;

        knowsWhyTheyAreHere = false;
    }

    // ============================================================
    // IDENTIDADE
    // ============================================================

    public String getName() {

        return name;
    }

    // ============================================================
    // COMPORTAMENTO
    // ============================================================

    public void updateBehavior(
        GameState state
    ) {

        if (state == null) {

            return;
        }

        fear =
            state.getAliceFear();

        anger =
            state.getAliceAnger();

        trust =
            state.getAliceTrust();

        suspicion =
            state.getSuspicionLevel();

        nervous =
            fear >= 45;

        afraid =
            fear >= 70;

        angry =
            anger >= 60;

        suspicious =
            suspicion >= 50;

        cooperative =
            trust >= 55;

        /*
         * Quanto mais o jogador descobre,
         * mais Alice percebe que esconder certas
         * coisas não adianta.
         */

        if (
            state.getRevelationLevel() >= 50
        ) {

            remembersLeo = true;
        }

        /*
         * Alice começa a entender por que está
         * naquela mesa.
         */

        if (
            state.getRevelationLevel() >= 75
        ) {

            knowsWhyTheyAreHere = true;
        }
    }

    // ============================================================
    // DECISÃO DE APOSTA
    // ============================================================

    public int chooseBet(
        GameState state
    ) {

        updateBehavior(state);

        /*
         * Alice tende a ser mais cautelosa
         * quando está assustada.
         */

        if (fear >= 80) {

            return 1;
        }

        /*
         * Se estiver muito confiante,
         * aumenta o risco.
         */

        if (confidence >= 80) {

            return 6;
        }

        /*
         * Se estiver com raiva,
         * pode agir impulsivamente.
         */

        if (anger >= 75) {

            return random.nextBoolean()
                ? 6
                : 9;
        }

        /*
         * Se confia em Leo,
         * tenta evitar escalada.
         */

        if (trust >= 70) {

            return 3;
        }

        int roll =
            random.nextInt(100);

        if (roll < 45) {

            return 3;
        }

        if (roll < 75) {

            return 6;
        }

        if (roll < 92) {

            return 9;
        }

        return 12;
    }

    // ============================================================
    // DECISÃO DE BLEFE
    // ============================================================

    public boolean shouldBluff(
        GameState state
    ) {

        updateBehavior(state);

        /*
         * Alice não gosta de blefar quando
         * está completamente apavorada.
         */

        if (fear >= 85) {

            bluffing = false;

            return false;
        }

        int chance = 25;

        chance += stubbornness / 5;

        chance += confidence / 5;

        chance -= fear / 6;

        chance -= suspicion / 10;

        chance =
            Math.max(
                5,
                Math.min(
                    85,
                    chance
                )
            );

        bluffing =
            random.nextInt(100) < chance;

        if (bluffing) {

            bluffs++;
        }

        return bluffing;
    }

    // ============================================================
    // REGISTRAR BLEFE
    // ============================================================

    public void registerBluffResult(
        boolean successful
    ) {

        if (successful) {

            successfulBluffs++;

            confidence += 5;

            if (confidence > 100) {

                confidence = 100;
            }

        } else {

            confidence -= 5;

            if (confidence < 0) {

                confidence = 0;
            }
        }

        bluffing = false;
    }

    // ============================================================
    // DECISÃO DE DESISTIR
    // ============================================================

    public boolean shouldRetreat(
        GameState state
    ) {

        updateBehavior(state);

        /*
         * Alice é teimosa.
         * Mesmo assustada, ela não desiste facilmente.
         */

        int chance = 5;

        chance += fear / 3;

        chance += sadness / 10;

        chance -= stubbornness / 5;

        chance -= confidence / 6;

        chance =
            Math.max(
                0,
                Math.min(
                    80,
                    chance
                )
            );

        return random.nextInt(100) < chance;
    }

    // ============================================================
    // REAÇÃO À DERROTA
    // ============================================================

    public void loseRound(
        GameState state
    ) {

        roundLosses++;

        fear += 6;

        confidence -= 4;

        anger += 2;

        if (fear > 100) {

            fear = 100;
        }

        if (confidence < 0) {

            confidence = 0;
        }

        if (anger > 100) {

            anger = 100;
        }

        updateBehavior(state);
    }

    // ============================================================
    // REAÇÃO À VITÓRIA
    // ============================================================

    public void winRound(
        GameState state
    ) {

        roundWins++;

        confidence += 7;

        fear -= 3;

        if (confidence > 100) {

            confidence = 100;
        }

        if (fear < 0) {

            fear = 0;
        }

        updateBehavior(state);
    }

    // ============================================================
    // JOGAR CARTA
    // ============================================================

    public void playCard() {

        cardsPlayed++;
    }

    // ============================================================
    // REAÇÃO AO DEALER
    // ============================================================

    public boolean confrontDealer(
        GameState state
    ) {

        updateBehavior(state);

        /*
         * Quanto mais Alice percebe as manipulações,
         * maior a chance de enfrentá-lo.
         */

        int chance =
            suspicion / 2;

        chance += anger / 5;

        if (knowsWhyTheyAreHere) {

            chance += 20;
        }

        chance =
            Math.min(
                90,
                chance
            );

        return random.nextInt(100) < chance;
    }

    // ============================================================
    // REAÇÃO ÀS PROVOCAÇÕES
    // ============================================================

    public void provoke() {

        if (fear > 75) {

            return;
        }

        if (random.nextInt(100) < 60) {

            anger += 4;

            if (anger > 100) {

                anger = 100;
            }
        }
    }

    // ============================================================
    // CONFIANÇA
    // ============================================================

    public void gainTrust(
        int amount
    ) {

        trust += amount;

        if (trust > 100) {

            trust = 100;
        }
    }

    public void loseTrust(
        int amount
    ) {

        trust -= amount;

        if (trust < 0) {

            trust = 0;
        }
    }

    // ============================================================
    // MEDO
    // ============================================================

    public void increaseFear(
        int amount
    ) {

        fear += amount;

        if (fear > 100) {

            fear = 100;
        }

        afraid =
            fear >= 70;

        nervous =
            fear >= 45;
    }

    public void decreaseFear(
        int amount
    ) {

        fear -= amount;

        if (fear < 0) {

            fear = 0;
        }

        afraid =
            fear >= 70;

        nervous =
            fear >= 45;
    }

    // ============================================================
    // RAIVA
    // ============================================================

    public void increaseAnger(
        int amount
    ) {

        anger += amount;

        if (anger > 100) {

            anger = 100;
        }

        angry =
            anger >= 60;
    }

    public void decreaseAnger(
        int amount
    ) {

        anger -= amount;

        if (anger < 0) {

            anger = 0;
        }

        angry =
            anger >= 60;
    }

    // ============================================================
    // MEMÓRIA
    // ============================================================

    public void rememberLeo() {

        remembersLeo = true;
    }

    public void rememberBrother() {

        remembersBrother = true;
    }

    public void rememberRelationship() {

        remembersRelationship = true;
    }

    public void rememberBreakup() {

        remembersBreakup = true;
    }

    public void rememberAccident() {

        remembersAccident = true;
    }

    // ============================================================
    // ESTADO DA MEMÓRIA
    // ============================================================

    public boolean remembersLeo() {

        return remembersLeo;
    }

    public boolean remembersBrother() {

        return remembersBrother;
    }

    public boolean remembersRelationship() {

        return remembersRelationship;
    }

    public boolean remembersBreakup() {

        return remembersBreakup;
    }

    public boolean remembersAccident() {

        return remembersAccident;
    }

    public boolean knowsWhyTheyAreHere() {

        return knowsWhyTheyAreHere;
    }

    // ============================================================
    // PERSONALIDADE
    // ============================================================

    public int getPatience() {

        return patience;
    }

    public int getConfidence() {

        return confidence;
    }

    public int getSuspicion() {

        return suspicion;
    }

    public int getStubbornness() {

        return stubbornness;
    }

    public int getHonesty() {

        return honesty;
    }

    // ============================================================
    // EMOÇÕES
    // ============================================================

    public int getFear() {

        return fear;
    }

    public int getAnger() {

        return anger;
    }

    public int getSadness() {

        return sadness;
    }

    public int getTrust() {

        return trust;
    }

    // ============================================================
    // ESTADO EMOCIONAL
    // ============================================================

    public boolean isNervous() {

        return nervous;
    }

    public boolean isAngry() {

        return angry;
    }

    public boolean isAfraid() {

        return afraid;
    }

    public boolean isSuspicious() {

        return suspicious;
    }

    public boolean isCooperative() {

        return cooperative;
    }

    public boolean isBluffing() {

        return bluffing;
    }

    // ============================================================
    // DEALER
    // ============================================================

    public boolean knowsDealer() {

        return knowsDealer;
    }

    public void setKnowsDealer(
        boolean value
    ) {

        knowsDealer = value;
    }

    // ============================================================
    // ESTATÍSTICAS
    // ============================================================

    public int getRoundWins() {

        return roundWins;
    }

    public int getRoundLosses() {

        return roundLosses;
    }

    public int getCurrentScore() {

        return currentScore;
    }

    public int getCardsPlayed() {

        return cardsPlayed;
    }

    public int getBluffs() {

        return bluffs;
    }

    public int getSuccessfulBluffs() {

        return successfulBluffs;
    }

    public void setCurrentScore(
        int score
    ) {

        currentScore =
            Math.max(
                0,
                score
            );
    }

    // ============================================================
    // FALA BASEADA NO ESTADO
    // ============================================================

    public String getMood() {

        if (fear >= 85) {

            return "PÂNICO";

        }

        if (anger >= 80) {

            return "FÚRIA";

        }

        if (sadness >= 75) {

            return "ABALADA";

        }

        if (suspicion >= 70) {

            return "DESCONFIADA";

        }

        if (trust >= 70) {

            return "COOPERATIVA";

        }

        if (confidence >= 80) {

            return "CONFIANTE";
        }

        return "TENSO";
    }

    // ============================================================
    // STATUS
    // ============================================================

    public String getStatus() {

        StringBuilder status =
            new StringBuilder();

        status.append(
            "==============================\n"
        );

        status.append(
            "            ALICE\n"
        );

        status.append(
            "==============================\n"
        );

        status.append(
            "Humor: "
        );

        status.append(
            getMood()
        );

        status.append("\n");

        status.append(
            "Confiança: "
        );

        status.append(
            confidence
        );

        status.append("%\n");

        status.append(
            "Medo: "
        );

        status.append(
            fear
        );

        status.append("%\n");

        status.append(
            "Raiva: "
        );

        status.append(
            anger
        );

        status.append("%\n");

        status.append(
            "Confiança em Leo: "
        );

        status.append(
            trust
        );

        status.append("%\n");

        status.append(
            "Suspeita: "
        );

        status.append(
            suspicion
        );

        status.append("%\n");

        status.append(
            "Vitórias: "
        );

        status.append(
            roundWins
        );

        status.append("\n");

        status.append(
            "Derrotas: "
        );

        status.append(
            roundLosses
        );

        status.append("\n");

        status.append(
            "=============================="
        );

        return status.toString();
    }
}
